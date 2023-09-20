package com.chat.util;

import com.chat.message.indexer.ChatMessageHistory;
import com.chat.message.indexer.ChatMessageObject;
import com.chat.socket.model.ChatMessage;
import com.chat.socket.service.ChatMessageLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.*;

import javax.portlet.ResourceRequest;
import java.util.List;

public class ChatServiceUtil {

    public ChatServiceUtil() {
    }

    public static List<ChatMessage> getUserUnreadMessages(Long toUserId, Long fromUserId) {
        DynamicQuery query = ChatMessageLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("fromUser", fromUserId));
        query.add(RestrictionsFactoryUtil.eq("toUser", toUserId));
        query.add(RestrictionsFactoryUtil.eq("readed", false));
        final List<ChatMessage> unreadMessage = ChatMessageLocalServiceUtil.dynamicQuery(query);
        //final List<ChatMessage> unreadMessage = ChatMessageLocalServiceUtil.dynamicQuery(query);

        return unreadMessage;
    }

    public static List<ChatMessage> getRecentMessageByFromUser(Long fromUserId) {
        DynamicQuery query = ChatMessageLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("fromUser", fromUserId));
        query.addOrder(OrderFactoryUtil.desc("id"));
        query.setLimit(0, 1);
        final List<ChatMessage> chatMessages = ChatMessageLocalServiceUtil.dynamicQuery(query);
        return chatMessages;
    }

    public static List<ChatMessageObject> getChat(ResourceRequest resourceRequest, Long toUserId, Long fromUserId, int count) {
        return ChatMessageHistory.getHistory(resourceRequest, toUserId, fromUserId, count);
    }

    public static int getUserChatCount(Long toUserId, Long fromUserId) {
        Disjunction fromUser = RestrictionsFactoryUtil.disjunction();
        fromUser.add(RestrictionsFactoryUtil.eq("fromUser", fromUserId));
        fromUser.add(RestrictionsFactoryUtil.eq("fromUser", toUserId));
        Disjunction toUser = RestrictionsFactoryUtil.disjunction();
        toUser.add(RestrictionsFactoryUtil.eq("toUser", fromUserId));
        toUser.add(RestrictionsFactoryUtil.eq("toUser", toUserId));
        DynamicQuery query = ChatMessageLocalServiceUtil.dynamicQuery();
        query.add((Criterion) fromUser);
        query.add((Criterion) toUser);
        final List<ChatMessage> chatMessages = ChatMessageLocalServiceUtil.dynamicQuery(query);
        return chatMessages.size();
    }

    public static int getUnreadMessageCount(Long fromUserId, Long toUserId) {
        DynamicQuery query = ChatMessageLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("fromUser", fromUserId));
        query.add(RestrictionsFactoryUtil.eq("toUser", toUserId));
        query.add(RestrictionsFactoryUtil.eq("readed", false));
        query.add(RestrictionsFactoryUtil.eq("deleted", false));
        List<ChatMessage> chatMessages = ChatMessageLocalServiceUtil.dynamicQuery(query);
        return chatMessages.size();
    }
}
