package com.chat.socket.action;

import com.chat.common.WebsocketService;
import com.chat.common.WebsocketServiceImpl;
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

@Component(
        immediate = true,
        property = {"javax.portlet.name=com_socket_portlet_SocketPortlet", "mvc.command.name=/userTyping"},
        service = {MVCResourceCommand.class}
)
public class UserTypingMVCResourceCommand extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(UserTypingMVCResourceCommand.class);

    public UserTypingMVCResourceCommand() {
    }

    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
        long userId = ParamUtil.getLong(resourceRequest, "userId");
        WebsocketService websocketService = new WebsocketServiceImpl();
        websocketService.typingMessage(UserLocalServiceUtil.getUser(userId), themeDisplay.getUser());

        try {
            resourceResponse.getWriter().println("");
        } catch (IOException var11) {
            _log.error(var11.getMessage());
        } finally {
            resourceResponse.getWriter().flush();
            resourceResponse.getWriter().close();
        }

    }
}
