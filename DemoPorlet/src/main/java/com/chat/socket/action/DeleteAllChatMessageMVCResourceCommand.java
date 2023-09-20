package com.chat.socket.action;

import com.chat.message.indexer.MessageIndexer;
import com.chat.socket.model.ChatMessage;
import com.chat.socket.service.ChatMessageLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


@Component(
        immediate = true,
        property = {"javax.portlet.name=com_socket_portlet_SocketPortlet", "mvc.command.name=/deleteAllMessages"},
        service = {MVCResourceCommand.class}
)
public class DeleteAllChatMessageMVCResourceCommand extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(DeleteAllChatMessageMVCResourceCommand.class);

    public DeleteAllChatMessageMVCResourceCommand() {
    }

    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        final ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
        final long userId = ParamUtil.getLong((PortletRequest) resourceRequest, "userId");
        final DynamicQuery query = ChatMessageLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("fromUser", (Object)themeDisplay.getUserId()));
        query.add(RestrictionsFactoryUtil.eq("toUser", (Object)userId));
        final List<ChatMessage> chatMessages = ChatMessageLocalServiceUtil.dynamicQuery(query);
        for (final ChatMessage chatMessage : chatMessages) {
            chatMessage.setDeleted(true);
            ChatMessageLocalServiceUtil.updateChatMessage(chatMessage);
            MessageIndexer.reindex(ChatMessage.class.getName(), chatMessage);
        }
        try {
            resourceResponse.getWriter().println("");
        }
        catch (IOException var9) {
            DeleteAllChatMessageMVCResourceCommand._log.error((Object)var9.getMessage());
        }
        finally {
            resourceResponse.getWriter().flush();
            resourceResponse.getWriter().close();
        }

    }
}
