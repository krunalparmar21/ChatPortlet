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
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component(
        immediate = true,
        property = {"javax.portlet.name=com_socket_portlet_SocketPortlet", "mvc.command.name=/chatMarkAsRead"},
        service = {MVCResourceCommand.class}
)
public class SetMarkAsReadMVCResourceCommand extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(SetMarkAsReadMVCResourceCommand.class);

    public SetMarkAsReadMVCResourceCommand() {
    }

    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        final Long fromUserId = ParamUtil.getLong((PortletRequest)resourceRequest, "userId");
        final DynamicQuery query = ChatMessageLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("fromUser", (Object)fromUserId));
        query.add(RestrictionsFactoryUtil.eq("readed", (Object)false));
        final List<ChatMessage> chatMessages = ChatMessageLocalServiceUtil.dynamicQuery(query);
        for (final ChatMessage chatMessage : chatMessages) {
            chatMessage.setReaded(true);
            chatMessage.setReadDate(new Date());
            ChatMessageLocalServiceUtil.updateChatMessage(chatMessage);
            MessageIndexer.reindex(ChatMessage.class.getName(), chatMessage);
        }
        try {
            resourceResponse.getWriter().println("");
        }
        catch (IOException var7) {
            SetMarkAsReadMVCResourceCommand._log.error((Object)var7.getMessage());
        }
        finally {
            resourceResponse.getWriter().flush();
            resourceResponse.getWriter().close();
        }

    }
}
