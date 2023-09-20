package com.chat.common;

import com.liferay.portal.kernel.model.User;

public interface WebsocketService {
    void sendChatMessage(User var1, User var2, Long var3);

    void typingMessage(User var1, User var2);

    void sendRequest(User var1, User var2);

    void requestAction(User var1, User var2, boolean var3);

    void clearStorage(Long var1);
}
