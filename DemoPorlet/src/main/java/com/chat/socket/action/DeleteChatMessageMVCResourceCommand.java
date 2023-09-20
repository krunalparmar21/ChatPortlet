package com.chat.socket.action;

import com.chat.message.indexer.MessageIndexer;
import com.chat.socket.model.ChatMessage;
import com.chat.socket.service.ChatMessageLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

@Component(
        immediate = true,
        property = {"javax.portlet.name=com_socket_portlet_SocketPortlet", "mvc.command.name=/deleteMessage"},
        service = {MVCResourceCommand.class}
)
public class DeleteChatMessageMVCResourceCommand extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(DeleteChatMessageMVCResourceCommand.class);

    public DeleteChatMessageMVCResourceCommand() {
    }

    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        long messageId = ParamUtil.getLong(resourceRequest, "id");
        ChatMessage chatMessage = ChatMessageLocalServiceUtil.getChatMessage(messageId);
        chatMessage.setDeleted(true);
        ChatMessageLocalServiceUtil.updateChatMessage(chatMessage);
        MessageIndexer.reindex(ChatMessage.class.getName(), chatMessage);

        try {
            resourceResponse.getWriter().println("");
        } catch (IOException var10) {
            _log.error(var10.getMessage());
        } finally {
            resourceResponse.getWriter().flush();
            resourceResponse.getWriter().close();
        }

    }
}
