package com.chat.socket.action;


import com.chat.common.WebsocketService;
import com.chat.common.WebsocketServiceImpl;
import com.chat.message.indexer.MessageIndexer;
import com.chat.socket.model.ChatMessage;
import com.chat.socket.service.ChatMessageLocalServiceUtil;
import com.chat.util.ChatServiceUtil;
import com.chat.util.FileUploadUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
import java.util.Map;

@Component(
        immediate = true,
        property = {"javax.portlet.name=com_socket_portlet_SocketPortlet", "mvc.command.name=/saveMessage"},
        service = {MVCResourceCommand.class}
)
public class SaveMessageMVCResourceCommand extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(SaveMessageMVCResourceCommand.class);

    public SaveMessageMVCResourceCommand() {
    }

    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
        String textMessage = ParamUtil.getString(resourceRequest, "text");
        Long toUserId = ParamUtil.getLong(resourceRequest, "toUser");
        Map<String, Object> fileMap = FileUploadUtil.fileUpload(themeDisplay, resourceRequest);
        Long messageId = CounterLocalServiceUtil.increment();
        ChatMessage chatMessage = ChatMessageLocalServiceUtil.createChatMessage(messageId);
        chatMessage.setGroupId(themeDisplay.getUser().getGroupId());
        chatMessage.setToUser(toUserId);
        chatMessage.setFromUser(themeDisplay.getUserId());
        chatMessage.setMessage(textMessage);
        chatMessage.setSentDate(new Date());
        if (fileMap.containsKey("fileName")) {
            chatMessage.setFileName(fileMap.get("fileName").toString());
            chatMessage.setFileId(Long.parseLong(fileMap.get("fileId").toString()));
        }

        ChatMessageLocalServiceUtil.addChatMessage(chatMessage);
        MessageIndexer.reindex(ChatMessage.class.getName(), chatMessage);
        WebsocketService websocketService = new WebsocketServiceImpl();
        websocketService.sendChatMessage(UserLocalServiceUtil.getUser(toUserId), themeDisplay.getUser(), chatMessage.getId());
        List<ChatMessage> cMessage = ChatServiceUtil.getRecentMessageByFromUser(themeDisplay.getUserId());
        JSONObject json = JSONFactoryUtil.createJSONObject();
        json.put("toUser", toUserId);
        json.put("fromUser", themeDisplay.getUserId());
        json.put("chat", cMessage);

        try {
            resourceResponse.getWriter().println(json.toJSONString());
        } catch (IOException var16) {
            _log.error(var16.getMessage());
        } finally {
            resourceResponse.getWriter().flush();
            resourceResponse.getWriter().close();
        }

    }
}
