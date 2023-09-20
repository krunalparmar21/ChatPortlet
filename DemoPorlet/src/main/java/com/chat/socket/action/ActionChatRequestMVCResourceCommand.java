package com.chat.socket.action;

import com.chat.common.WebsocketService;
import com.chat.common.WebsocketServiceImpl;
import com.chat.socket.model.ChatRequest;
import com.chat.socket.service.ChatRequestLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import org.osgi.service.component.annotations.Component;


import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;


@Component(
        immediate = true,
        property = {"javax.portlet.name=com_socket_portlet_SocketPortlet", "mvc.command.name=/actionChatRequest"},
        service = {MVCResourceCommand.class}
)
public class ActionChatRequestMVCResourceCommand extends BaseMVCResourceCommand {


    private static Log _log = LogFactoryUtil.getLog(ActionChatRequestMVCResourceCommand.class);

    public ActionChatRequestMVCResourceCommand() {
    }

    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        final ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
        final long userId = ParamUtil.getLong((PortletRequest) resourceRequest, "userId");
        final boolean action = ParamUtil.getBoolean((PortletRequest)resourceRequest, "actionStatus");
        final DynamicQuery query = ChatRequestLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("fromUserId", userId));
        query.add(RestrictionsFactoryUtil.eq("toUserId", themeDisplay.getUserId()));
        final List<ChatRequest> chatReuests = ChatRequestLocalServiceUtil.dynamicQuery(query);
        final JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        if (chatReuests.size() > 0) {
            final WebsocketService websocketService = new WebsocketServiceImpl();
            final ChatRequest chatReuest = chatReuests.get(0);
            if (action) {
                chatReuest.setIsAccepted(true);
                websocketService.requestAction(UserLocalServiceUtil.getUser(userId), themeDisplay.getUser(), true);
                jsonObject.put("actionStatus", "true");
            }
            else {
                chatReuest.setIsRejected(true);
                websocketService.requestAction(UserLocalServiceUtil.getUser(userId), themeDisplay.getUser(), false);
                jsonObject.put("actionStatus", "false");
            }
            chatReuest.setModifiedDate(new Date());
            ChatRequestLocalServiceUtil.updateChatRequest(chatReuest);
        }
        try {
            resourceResponse.getWriter().println(jsonObject.toJSONString());
        }
        catch (IOException var15) {
            _log.error((Object)var15.getMessage());
        }
        finally {
            resourceResponse.getWriter().flush();
            resourceResponse.getWriter().close();
        }
    }
}
