package com.chat.socket.action;

import com.chat.message.indexer.ChatMessageObject;
import com.chat.util.ChatServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Component(
        immediate = true,
        property = {"javax.portlet.name=com_socket_portlet_SocketPortlet", "mvc.command.name=/getMessages"},
        service = {MVCResourceCommand.class}
)
public class GetMessagesMVCResourceCommand extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(GetMessagesMVCResourceCommand.class);

    public GetMessagesMVCResourceCommand() {
    }

    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
        Long userId = ParamUtil.getLong(resourceRequest, "userId");
        int totalRecords = ParamUtil.getInteger(resourceRequest, "totalRecord");
        int count = ChatServiceUtil.getUserChatCount(userId, themeDisplay.getUserId());
        count -= totalRecords;
        List<ChatMessageObject> chatHistory = ChatServiceUtil.getChat(resourceRequest, userId, themeDisplay.getUserId(), totalRecords);
        Comparator<ChatMessageObject> compareById = (o1, o2) -> {
            return Long.compare(o1.getId(), o2.getId());
        };
        chatHistory.sort(compareById);
        JSONObject json = JSONFactoryUtil.createJSONObject();
        json.put("toUser", userId);
        json.put("fromUser", themeDisplay.getUserId());
        json.put("chat", chatHistory);
        json.put("count", count < 0 ? 0 : count);
        json.put("totalRecords", totalRecords);

        try {
            resourceResponse.getWriter().println(json.toJSONString());
        } catch (IOException var14) {
            _log.error(var14.getMessage());
        } finally {
            resourceResponse.getWriter().flush();
            resourceResponse.getWriter().close();
        }

    }
}
