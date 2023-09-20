package com.chat.socket.action;

import com.chat.socket.model.ChatConfigure;
import com.chat.socket.service.ChatConfigureLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

@Component(
        immediate = true,
        property = {"javax.portlet.name=com_socket_portlet_SocketPortlet", "mvc.command.name=/"},
        service = {MVCRenderCommand.class}
)
public class ViewMVCRenderCommand implements MVCRenderCommand  {
    private static Log _log = LogFactoryUtil.getLog(ViewMVCRenderCommand.class.getName());

    public ViewMVCRenderCommand() {
    }

    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
        List<ChatConfigure> chatConfigures = ChatConfigureLocalServiceUtil.FindConfigureByGroupId(themeDisplay.getScopeGroupId());
        String[] roleIdSiteConfiguration = new String[0];
        boolean userHaveRoleForChat = false;
        if (chatConfigures.size() > 0 && Validator.isNotNull(((ChatConfigure)chatConfigures.get(0)).getChatRoleId())) {
            roleIdSiteConfiguration = ((ChatConfigure)chatConfigures.get(0)).getChatRoleId().split(",");
        }

        for(int i = 0; i < roleIdSiteConfiguration.length; ++i) {
            if (RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), Long.parseLong(roleIdSiteConfiguration[i])) && GroupLocalServiceUtil.hasUserGroup(themeDisplay.getUserId(), themeDisplay.getSiteGroupId())) {
                userHaveRoleForChat = true;
            }

            if (GroupLocalServiceUtil.hasUserGroup(themeDisplay.getUserId(), themeDisplay.getSiteGroupId())) {
                if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(themeDisplay.getUserId(), themeDisplay.getSiteGroupId(), Long.parseLong(roleIdSiteConfiguration[i]))) {
                    userHaveRoleForChat = true;
                } else {
                    try {
                        Role role = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), "Site Member");
                        if (role.getRoleId() == Long.parseLong(roleIdSiteConfiguration[i])) {
                            userHaveRoleForChat = true;
                        }
                    } catch (PortalException var9) {
                        _log.error(var9.getMessage());
                    }
                }
            }
        }

        if (chatConfigures.size() > 0) {
            ChatConfigure chatConfigure = (ChatConfigure)chatConfigures.get(0);
            renderRequest.setAttribute("isEnable", chatConfigure.getEnable());
            renderRequest.setAttribute("requestFeature", chatConfigure.getRequestFeature());
            renderRequest.setAttribute("headerColor", chatConfigure.getHeaderColor());
            renderRequest.setAttribute("chatRightColor", chatConfigure.getChatRightColor());
            renderRequest.setAttribute("chatLeftColor", chatConfigure.getChatLeftColor());
            renderRequest.setAttribute("headerTextColor", chatConfigure.getHeaderTextColor());
            renderRequest.setAttribute("contentTextColor", chatConfigure.getContentTextColor());
            renderRequest.setAttribute("backColor", chatConfigure.getBackColor());
            renderRequest.setAttribute("fontFamily", chatConfigure.getFontFamily());
        } else {
            renderRequest.setAttribute("requestFeature", false);
            renderRequest.setAttribute("isEnable", false);
        }

        if (userHaveRoleForChat) {
            String endPoint = "/o/chat/{userId}";
            endPoint = endPoint.replace("{userId}", "" + themeDisplay.getUserId());
            renderRequest.setAttribute("socketChatEndPointPath", endPoint);
        }

        renderRequest.setAttribute("userHaveRole", userHaveRoleForChat);
        return "/view.jsp";
    }
}
