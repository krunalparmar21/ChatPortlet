package com.chat.socket.action;

import com.chat.common.WebsocketService;
import com.chat.common.WebsocketServiceImpl;
import com.chat.socket.model.ChatMessage;
import com.chat.socket.model.ChatRequest;
import com.chat.socket.service.ChatMessageLocalServiceUtil;
import com.chat.socket.service.ChatRequestLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component(
        immediate = true,
        property = {"javax.portlet.name=com_socket_portlet_SocketPortlet", "mvc.command.name=/sendChatRequest"},
        service = {MVCResourceCommand.class}
)
public class SendChatRequestMVCResourceCommand extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(SendChatRequestMVCResourceCommand.class);

    public SendChatRequestMVCResourceCommand() {
    }

    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
        long userId = ParamUtil.getLong(resourceRequest, "userId");
        DynamicQuery query = ChatRequestLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("toUserId", userId));
        query.add(RestrictionsFactoryUtil.eq("fromUserId", themeDisplay.getUserId()));
        List<ChatRequest> chatRequests = ChatRequestLocalServiceUtil.dynamicQuery(query);
        if (chatRequests.size() > 0) {
            ChatRequest chatRequest = (ChatRequest)chatRequests.get(0);
            chatRequest.setIsAccepted(false);
            chatRequest.setIsRejected(false);
            chatRequest.setModifiedDate(new Date());
            ChatRequestLocalServiceUtil.updateChatRequest(chatRequest);
            Long chatId = CounterLocalServiceUtil.increment();
            ChatMessage chatMessage = ChatMessageLocalServiceUtil.createChatMessage(chatId);
            chatMessage.setGroupId(themeDisplay.getUser().getGroupId());
            chatMessage.setToUser(userId);
            chatMessage.setFromUser(themeDisplay.getUserId());
            chatMessage.setMessage("REQUEST");
            chatMessage.setDeleted(true);
            chatMessage.setSentDate(new Date());
            ChatMessageLocalServiceUtil.addChatMessage(chatMessage);
        } else {
            long chatRequestId = CounterLocalServiceUtil.increment();
            ChatRequest chatRequest = ChatRequestLocalServiceUtil.createChatRequest(chatRequestId);
            chatRequest.setFromUserId(themeDisplay.getUserId());
            chatRequest.setToUserId(userId);
            chatRequest.setIsAccepted(false);
            chatRequest.setIsRejected(false);
            chatRequest.setCreateDate(new Date());
            ChatRequestLocalServiceUtil.addChatRequest(chatRequest);
            Long chatId = CounterLocalServiceUtil.increment();
            ChatMessage chatMessage = ChatMessageLocalServiceUtil.createChatMessage(chatId);
            chatMessage.setGroupId(themeDisplay.getUser().getGroupId());
            chatMessage.setToUser(userId);
            chatMessage.setFromUser(themeDisplay.getUserId());
            chatMessage.setMessage("REQUEST");
            chatMessage.setDeleted(true);
            chatMessage.setSentDate(new Date());
            ChatMessageLocalServiceUtil.addChatMessage(chatMessage);
        }

        WebsocketService websocketService = new WebsocketServiceImpl();
        websocketService.sendRequest(UserLocalServiceUtil.getUser(userId), themeDisplay.getUser());

        try {
            resourceResponse.getWriter().println("");
        } catch (IOException var16) {
            _log.error(var16.getMessage());
        } finally {
            resourceResponse.getWriter().flush();
            resourceResponse.getWriter().close();
        }

    }
}
