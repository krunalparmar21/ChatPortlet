package com.chat.socket.action;

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

@Component(
        immediate = true,
        property = {"javax.portlet.name=com_socket_portlet_SocketPortlet", "mvc.command.name=/getUnreadMessageCount"},
        service = {MVCResourceCommand.class}
)
public class GetUnreadMessageCountMVCResourceCommand extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(GetUnreadMessageCountMVCResourceCommand.class);

    public GetUnreadMessageCountMVCResourceCommand() {
    }

    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
        Long fromUserId = ParamUtil.getLong(resourceRequest, "fromUserId");
        int unreadMessageCount = ChatServiceUtil.getUnreadMessageCount(fromUserId, themeDisplay.getUserId());
        JSONObject json = JSONFactoryUtil.createJSONObject();
        json.put("fromUserId", fromUserId);
        json.put("unreadCount", unreadMessageCount);

        try {
            resourceResponse.getWriter().println(json.toJSONString());
        } catch (IOException var11) {
            _log.error(var11.getMessage());
        } finally {
            resourceResponse.getWriter().flush();
            resourceResponse.getWriter().close();
        }

    }
}
