package com.chat.common;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.chat.message.indexer.MessageIndexer;
import com.chat.socket.ChatWebSocket;
import com.chat.socket.model.ChatMessage;
import com.chat.socket.service.ChatMessageLocalServiceUtil;
import com.chat.util.ChatServiceUtil;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.websocket.Session;

public class WebsocketServiceImpl implements WebsocketService{

    private static Log log = LogFactoryUtil.getLog(WebsocketServiceImpl.class);

    public WebsocketServiceImpl() {
    }

    public void sendChatMessage(User toUser, User fromUser, Long messageId) {
        try {
            Hashtable<Long, Session> connection = ChatWebSocket.getConnections();
            if (connection.containsKey(toUser.getUserId())) {

                log.info("User ID for SEND CHAT MESSAGE"+toUser.getUserId());
                Session session = connection.get(toUser.getUserId());

                log.info("Session Send Chat MEssage "+toUser.getFullName());

                List<ChatMessage> chatMessages = ChatServiceUtil.getRecentMessageByFromUser(fromUser.getUserId());
                JSONObject obj = JSONFactoryUtil.createJSONObject();
                obj.put("toUser", fromUser.getUserId());
                obj.put("fromUser", toUser.getUserId());
                obj.put("fromUserName", fromUser.getFullName());
                obj.put("chat", chatMessages);
                if (session.isOpen()) {
                    ChatMessage chatMessage = ChatMessageLocalServiceUtil.getChatMessage(messageId);
                    chatMessage.setReaded(true);
                    chatMessage.setReadDate(new Date());
                    ChatMessageLocalServiceUtil.updateChatMessage(chatMessage);
                    MessageIndexer.reindex(ChatMessage.class.getName(), chatMessage);
                    session.getBasicRemote().sendText(obj.toJSONString());
                }
            }
        } catch (Exception var9) {
            log.error(var9.getMessage());
        }

    }

    public void typingMessage(User toUser, User fromUser) {
        try {
            Hashtable<Long, Session> connection = ChatWebSocket.getConnections();
            synchronized(connection) {
                if (connection.containsKey(toUser.getUserId())) {
                    Session session = (Session)connection.get(toUser.getUserId());
                    Message message = new Message();
                    message.setFrom(fromUser.getFullName());
                    String chatJson = "{ \"writing\": \"writing\" ,\"fromUserId\": " + fromUser.getUserId() + " }";
                    message.setContent(chatJson);
                    if (session.isOpen()) {
                        session.getBasicRemote().sendText(chatJson);
                    }
                }
            }
        } catch (Exception var10) {
            log.error(var10.getMessage());
        }

    }

    public void sendRequest(User toUser, User fromUser) {
        try {
            Hashtable<Long, Session> connection = ChatWebSocket.getConnections();
            if (connection.containsKey(toUser.getUserId())) {
                Session session = (Session)connection.get(toUser.getUserId());
                JSONObject obj = JSONFactoryUtil.createJSONObject();
                obj.put("sendRequest", true);
                obj.put("from", fromUser.getFullName());
                if (session.isOpen()) {
                    session.getBasicRemote().sendText(obj.toJSONString());
                }
            }
        } catch (Exception var6) {
            log.error(var6.getMessage());
        }

    }

    public void requestAction(User toUser, User fromUser, boolean status) {
        try {
            Hashtable<Long, Session> connection = ChatWebSocket.getConnections();
            if (connection.containsKey(toUser.getUserId())) {
                String action = "";
                if (status) {
                    action = "accept";
                } else {
                    action = "reject";
                }

                Session session = (Session)connection.get(toUser.getUserId());
                JSONObject obj = JSONFactoryUtil.createJSONObject();
                obj.put("requestAction", action);
                obj.put("from", fromUser.getFullName());
                if (session.isOpen()) {
                    session.getBasicRemote().sendText(obj.toJSONString());
                }
            }
        } catch (Exception var8) {
            log.error(var8.getMessage());
        }

    }

    public void clearStorage(Long userId) {
        try {
            log.info("Inside ClearStorage METHOD");
            Hashtable<Long, Session> connection = ChatWebSocket.getConnections();
            if (connection.containsKey(userId)) {
                Session session = (Session)connection.get(userId);
                log.info("Inside Connection Clear Storage : " +session);
                JSONObject obj = JSONFactoryUtil.createJSONObject();
                obj.put("clearStorage", true);
                if (session.isOpen()) {
                    session.getBasicRemote().sendText(obj.toJSONString());
                }
            }
        } catch (Exception var5) {
            log.error(var5.getMessage());
        }

    }
}
