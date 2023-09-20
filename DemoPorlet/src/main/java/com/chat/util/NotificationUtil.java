package com.chat.util;


import org.json.simple.JSONArray;
import java.util.Hashtable;
import java.io.IOException;
import javax.websocket.Session;

import com.chat.socket.ChatWebSocket;
import org.json.simple.JSONObject;


public class NotificationUtil {

    public static void joinDisconnectBroadcast() {
        final Hashtable<Long, Session> connections = ChatWebSocket.getConnections();
        for (final Long userId : connections.keySet()) {
            try {
                final JSONArray onlineUserIds = getOnlineUserIdOfSockets();
                onlineUserIds.remove((Object)userId);
                final Session session = connections.get(userId);
                final JSONObject obj = new JSONObject();
                obj.put((Object)"sendFrom", (Object)userId);
                obj.put((Object)"onlineUserIds", (Object)onlineUserIds);
                if (!session.isOpen()) {
                    continue;
                }
                session.getBasicRemote().sendText(obj.toJSONString());
            }
            catch (IOException ignore) {
                ignore.printStackTrace();
            }
        }
    }

    private static JSONArray getOnlineUserIdOfSockets() {
        final JSONArray userIds = new JSONArray();
        final Hashtable<Long, Session> connections = ChatWebSocket.getConnections();
        for (final Long userId : connections.keySet()) {
            if (!userIds.contains((Object)userId)) {
                userIds.add((Object)userId);
            }
        }
        return userIds;
    }
}
