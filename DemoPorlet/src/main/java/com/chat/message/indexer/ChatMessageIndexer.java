package com.chat.message.indexer;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;;
import com.liferay.portal.kernel.search.*;

import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Summary;

import com.chat.socket.model.ChatMessage;
import com.chat.socket.service.ChatMessageLocalServiceUtil;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.engine.SearchEngineInformation;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.*;


@Component(
        immediate = true,
        service = {Indexer.class}
)
public class ChatMessageIndexer extends BaseIndexer<ChatMessage> {


    @Reference
    private SearchEngineInformation searchEngineInformation;


    private SearchEngine searchEngine;

    private static final Log _log = LogFactoryUtil.getLog(ChatMessageIndexer.class);
    public static final String CLASS_NAMES = ChatMessage.class.getName();
    public static final String PORTLET_ID = "com_socket_portlet_SocketPortlet";

    @Reference
    IndexWriterHelper indexWriterHelper;

    public ChatMessageIndexer() {
        this.setDefaultSelectedFieldNames(new String[]{"companyId", "entryClassPK", "id", "groupId", "fromUser", "toUser", "sentDate", "readDate", "message", "readed", "deleted", "fileName", "fileId"});
        this.setPermissionAware(false);
        this.setFilterSearch(false);
    }

    public String getClassName() {
        return ChatMessageIndexer.CLASS_NAMES;
    }

    protected void doDelete(ChatMessage object) throws SearchException {
        Document document = new DocumentImpl();
        document.addUID("com_socket_portlet_SocketPortlet", object.getId());
    try{
        IndexWriterHelperUtil.deleteDocument( object.getCompanyId(), document.getUID(), true);
    }catch (SearchException e){
        throw e;
    }

    }
    protected Document doGetDocument(ChatMessage object) throws Exception {
        Document document = this.getBaseModelDocument(CLASS_NAMES, object);
        document.addUID("com_socket_portlet_SocketPortlet", object.getId());
        document.addKeyword("id", object.getId());
        document.addKeyword("companyId", object.getCompanyId());
        document.addKeyword("groupId", object.getGroupId());
        document.addKeyword("fromUser", object.getFromUser());
        document.addKeyword("toUser", object.getToUser());
        document.addDate("sentDate", object.getSentDate());
        document.addDate("readDate", object.getReadDate());
        document.addText("message", object.getMessage());
        document.addKeyword("readed", object.getReaded());
        document.addKeyword("deleted", object.getDeleted());
        document.addText("fileName", object.getFileName());
        document.addKeyword("fileId", object.getFileId());
        return document;
    }

    protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest, PortletResponse portletResponse) throws Exception {
        Summary summary = this.createSummary(document);
        summary.setMaxContentLength(200);
        return summary;
    }

    protected void doReindex(String className, long classPK) throws Exception {
        _log.info("Reindexing StoreInformation id " + classPK);
        ChatMessage chatMessage = ChatMessageLocalServiceUtil.getChatMessage(classPK);
        this.doReindex(chatMessage);
    }

    protected void doReindex(String[] ids) throws Exception {
        final String[] var2 = ids;
        for (int var3 = ids.length, var4 = 0; var4 < var3; ++var4) {
            final String companyId = var2[var4];
            final long companyIdIndex = GetterUtil.getLong(companyId);
            ChatMessageIndexer._log.info((Object)("Reindex all Store objects " + companyIdIndex));
            final List<ChatMessage> chatMessages = (List<ChatMessage>)ChatMessageLocalServiceUtil.getChatMessages(-1, -1);
            if (chatMessages.isEmpty()) {
                return;
            }
            final Collection<Document> documents = new ArrayList<Document>();
            for (final ChatMessage chatMessage : chatMessages) {
                final Document doc = this.getDocument(ChatMessageLocalServiceUtil.getChatMessage(chatMessage.getId()));
                documents.add(doc);
            }
            this.indexWriterHelper.updateDocuments(companyIdIndex, (Collection)documents, true);
        }

    }

    protected void doReindex(ChatMessage object) throws Exception {
        Document document = this.getDocument(object);
        this.indexWriterHelper.updateDocument( object.getCompanyId(), document);
    }
}
