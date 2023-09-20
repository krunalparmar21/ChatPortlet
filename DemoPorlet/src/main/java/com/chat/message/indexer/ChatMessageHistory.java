package com.chat.message.indexer;


import com.chat.socket.model.ChatMessage;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.*;

import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.kernel.search.generic.QueryTermImpl;

import java.text.DateFormat;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.util.Validator;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import com.liferay.portal.kernel.util.PortalUtil;

import java.util.ArrayList;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import java.util.List;
import javax.portlet.ResourceRequest;
import com.liferay.portal.kernel.log.Log;


public class ChatMessageHistory {

    private static Log _log = LogFactoryUtil.getLog(ChatMessageHistory.class);

    public ChatMessageHistory() {
    }

    public static List<ChatMessageObject> getHistory(ResourceRequest resourceRequest, Long toUserId, Long fromUserId, int count) {

        BooleanQuery booleanQuery = new BooleanQueryImpl();
        BooleanQuery from = new BooleanQueryImpl();
        BooleanQuery to = new BooleanQueryImpl();
        List<ChatMessageObject> chatMessageObjects = new ArrayList();

        try {
            addExactRequiredTerm(booleanQuery, "entryClassName", ChatMessage.class.getName());
            addExactRequiredNotTerm(booleanQuery, "deleted", "true");
            addRequiredTerm(from, "fromUser", String.valueOf(fromUserId));
            addRequiredTerm(from, "fromUser", String.valueOf(toUserId));
            addRequiredTerm(to, "toUser", String.valueOf(fromUserId));
            addRequiredTerm(to, "toUser", String.valueOf(toUserId));
            booleanQuery.add((Query) from, BooleanClauseOccur.MUST);
            booleanQuery.add((Query)to, BooleanClauseOccur.MUST);
            final HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest((PortletRequest) resourceRequest));
            final SearchContext searchContext = SearchContextFactory.getInstance(httpReq);
            final Sort[] sorts = { SortFactoryUtil.create("sentDate_sortable", 3, true), null };
            searchContext.setSorts(sorts);
            searchContext.setStart(0);
            searchContext.setEnd(count);
            final SearchEngine searchEngine = SearchEngineHelperUtil.getSearchEngine();
            final IndexSearcher indexSearcher = searchEngine.getIndexSearcher();
            final Hits hits = indexSearcher.search(searchContext, (Query)booleanQuery);
            ChatMessageObject chatMessageObject = null;
            final DateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
            for (final Document doc : hits.getDocs()) {
                chatMessageObject = new ChatMessageObject();
                chatMessageObject.setId(Long.parseLong(doc.get("entryClassPK")));
                chatMessageObject.setGroupId(doc.get("groupId"));
                chatMessageObject.setCompanyId(doc.get("companyId"));
                chatMessageObject.setFromUser(doc.get("fromUser"));
                chatMessageObject.setToUser(doc.get("toUser"));
                final Calendar sentDateCalendar = Calendar.getInstance();
                sentDateCalendar.setTimeInMillis(Long.parseLong(doc.get("sentDate_sortable")));
                chatMessageObject.setSentDate(simpleDateFormat.format(sentDateCalendar.getTime()));
                if (Validator.isNotNull(doc.get("readDate_sortable"))) {
                    final Calendar readDateCalendar = Calendar.getInstance();
                    readDateCalendar.setTimeInMillis(Long.parseLong(doc.get("readDate_sortable")));
                    chatMessageObject.setReadDate(simpleDateFormat.format(readDateCalendar.getTime()));
                }
                chatMessageObject.setMessage(doc.get("message"));
                chatMessageObject.setReaded(Boolean.parseBoolean(doc.get("readed")));
                chatMessageObject.setDeleted(Boolean.parseBoolean(doc.get("deleted")));
                chatMessageObject.setFileName(doc.get("fileName"));
                chatMessageObject.setFileId(Long.parseLong(doc.get("fileId")));
                chatMessageObjects.add(chatMessageObject);
            }
        } catch (Exception var22) {
            _log.error(var22.getMessage());
        }

        return chatMessageObjects;
    }

    private static void addExactRequiredTerm(BooleanQuery query, String field, String value) throws ParseException {
        query.addTerm(field, value, true);
        TermQueryImpl termQuery = new TermQueryImpl(new QueryTermImpl(field, value));
        query.add(termQuery, BooleanClauseOccur.MUST);
        _log.info("Query"+query);
    }

    private static void addExactRequiredNotTerm(BooleanQuery query, String field, String value) throws ParseException {
        TermQueryImpl termQuery = new TermQueryImpl(new QueryTermImpl(field, value));
        query.add(termQuery, BooleanClauseOccur.MUST_NOT);
        _log.info("Query 2 "+query);
    }

    private static void addRequiredTerm(BooleanQuery query, String field, String value) throws ParseException {
        query.addTerm(field, value, true);
        TermQueryImpl termQuery = new TermQueryImpl(new QueryTermImpl(field, value));
        query.add(termQuery, BooleanClauseOccur.SHOULD);
        _log.info("Query 3"+query);
    }
}
