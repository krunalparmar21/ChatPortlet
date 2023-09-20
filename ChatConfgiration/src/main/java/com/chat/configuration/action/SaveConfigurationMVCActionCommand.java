package com.chat.configuration.action;

import com.chat.socket.ChatWebSocket;
import com.chat.socket.model.ChatConfigure;
import com.chat.socket.service.ChatConfigureLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.websocket.Session;

import org.osgi.service.component.annotations.Component;

@Component(
	    immediate = true,
	    property = {"javax.portlet.name=com_chat_configuration_ChatConfgirationPortlet", "mvc.command.name=/saveConfiguration"},
	    service = {MVCActionCommand.class}
	)
public class SaveConfigurationMVCActionCommand extends BaseMVCActionCommand{

    private static Log _log = LogFactoryUtil.getLog(SaveConfigurationMVCActionCommand.class);

    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");

        _log.info("Company ID :"+themeDisplay.getCompanyId());

        boolean chatRequestFeature = ParamUtil.getBoolean(actionRequest, "chat-request");
        boolean isEnable = ParamUtil.getBoolean(actionRequest, "enable");
        String headerColor = ParamUtil.getString(actionRequest, "header-color");
        String rightColor = ParamUtil.getString(actionRequest, "chat-right-color");
        String leftColor = ParamUtil.getString(actionRequest, "chat-left-color");
        String headerTextColor = ParamUtil.getString(actionRequest, "header-text-color");
        String contentTextColor = ParamUtil.getString(actionRequest, "content-text-color");
        String backColor = ParamUtil.getString(actionRequest, "back-ground-color");
        String textFontFamily = ParamUtil.getString(actionRequest, "text-font-family");
        String[] selectItems = ParamUtil.getStringValues(actionRequest, "selectItems");
        long siteId = ParamUtil.getLong(actionRequest, "site");


        _log.info("SiteID"+siteId);
        if (Validator.isNull(siteId)) {
            SessionErrors.add(actionRequest, "not-allow-group");
        } else {
            List<ChatConfigure> chatConfigures = ChatConfigureLocalServiceUtil.FindConfigureByGroupId(siteId);
            if (chatConfigures.size() == 0) {
                long id = CounterLocalServiceUtil.increment();
                ChatConfigure chatConfigure = ChatConfigureLocalServiceUtil.createChatConfigure(id);
                chatConfigure.setGroupId(siteId);
                chatConfigure.setEnable(isEnable);
                chatConfigure.setRequestFeature(chatRequestFeature);
                chatConfigure.setHeaderColor(headerColor);
                chatConfigure.setChatRightColor(rightColor);
                chatConfigure.setChatLeftColor(leftColor);
                chatConfigure.setHeaderTextColor(headerTextColor);
                chatConfigure.setContentTextColor(contentTextColor);
                chatConfigure.setBackColor(backColor);
                chatConfigure.setCreateDate(new Date());
                chatConfigure.setChatRoleId(String.join(",", selectItems));
                chatConfigure.setFontFamily(textFontFamily);
                chatConfigure.setUserId(themeDisplay.getUserId());
                chatConfigure.setUserName(themeDisplay.getUser().getFullName());
                ChatConfigureLocalServiceUtil.addChatConfigure(chatConfigure);
            } else {
                ChatConfigure chatConfigure = (ChatConfigure)chatConfigures.get(0);
                chatConfigure.setRequestFeature(chatRequestFeature);
                chatConfigure.setEnable(isEnable);
                chatConfigure.setHeaderColor(headerColor);
                chatConfigure.setChatRightColor(rightColor);
                chatConfigure.setChatLeftColor(leftColor);
                chatConfigure.setHeaderTextColor(headerTextColor);
                chatConfigure.setContentTextColor(contentTextColor);
                chatConfigure.setBackColor(backColor);
                chatConfigure.setModifiedDate(new Date());
                chatConfigure.setChatRoleId(String.join(",", selectItems));
                chatConfigure.setFontFamily(textFontFamily);
                chatConfigure.setUserId(themeDisplay.getUserId());
                chatConfigure.setUserName(themeDisplay.getUser().getFullName());
                ChatConfigureLocalServiceUtil.updateChatConfigure(chatConfigure);
            }

            Hashtable<Long, Session> connection = ChatWebSocket.getConnections();
            Iterator var18 = connection.keySet().iterator();


            while(var18.hasNext()) {
                Long userId = (Long)var18.next();
                try {
                    JSONObject obj = JSONFactoryUtil.createJSONObject();
                    obj.put("clearStorage", "");
                    Session session = (Session)connection.get(userId);
                    if (session.isOpen()) {
                        session.getBasicRemote().sendText(obj.toJSONString());
                    }
                } catch (IOException var22) {
                    var22.printStackTrace();
                }
            }
        }

    }
}
