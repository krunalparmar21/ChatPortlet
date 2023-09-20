package com.chat.socket;


import com.chat.util.NotificationUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;

import javax.websocket.*;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@Component(
	     immediate = true,
	     property = {"org.osgi.http.websocket.endpoint.path=/o/chat/{userId}"},
	     service = Endpoint.class
	 )
public class ChatWebSocket extends Endpoint {
    public static Hashtable<Long, Session> connections = new Hashtable();
    private Session session;
    private static final Log log = LogFactoryUtil.getLog(ChatWebSocket.class);

    public ChatWebSocket() {
    }

    @Override
    public void onOpen(final Session session, EndpointConfig config) {

        log.info("IN WebSocket Connection ");

        this.session = session;

        Map<String, String> map = session.getPathParameters();
        connections.put(Long.parseLong((String)map.get("userId")), this.session);

        log.info("IN WebSocket Connection ");

        setConnections(connections);
        NotificationUtil.joinDisconnectBroadcast();
        session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String text) {
                try {
                    log.info("IN ON MESSAGE ");
                    RemoteEndpoint.Basic remoteEndpoint = session.getBasicRemote();
                    remoteEndpoint.sendText(text);
                } catch (IOException var3) {
                    throw new RuntimeException(var3);
                }
            }
        });
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        super.onClose(session, closeReason);
        Map<String, String> map = session.getPathParameters();
        connections.remove(Long.parseLong((String)map.get("userId")));

        setConnections(connections);
        NotificationUtil.joinDisconnectBroadcast();
    }

    @Override
    public void onError(Session session, Throwable thr) {
        super.onError(session, thr);
        log.info("onError = " + thr.getMessage());
    }
    public static Hashtable<Long, Session> getConnections() {
        return connections;
    }
    public static void setConnections(Hashtable<Long, Session> connections) {
        ChatWebSocket.connections = connections;
    }


}
