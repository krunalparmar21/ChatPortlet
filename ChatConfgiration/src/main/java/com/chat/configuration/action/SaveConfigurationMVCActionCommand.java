package com.chat.configuration.action;

import com.chat.socket.ChatWebSocket;
import com.chat.socket.model.ChatConfigure;
import com.chat.socket.service.ChatConfigureLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectDefinitionTable;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalServiceUtil;
import com.liferay.object.service.ObjectEntryLocalServiceUtil;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

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

    private static final String OBJECT_NAME = "C_ChatConfigure";
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

//            if (chatConfigures.size() == 0) {
//                long id = CounterLocalServiceUtil.increment();
//                ChatConfigure chatConfigure = ChatConfigureLocalServiceUtil.createChatConfigure(id);
//                chatConfigure.setGroupId(siteId);
//                chatConfigure.setEnable(isEnable);
//                chatConfigure.setRequestFeature(chatRequestFeature);
//                chatConfigure.setHeaderColor(headerColor);
//                chatConfigure.setChatRightColor(rightColor);
//                chatConfigure.setChatLeftColor(leftColor);
//                chatConfigure.setHeaderTextColor(headerTextColor);
//                chatConfigure.setContentTextColor(contentTextColor);
//                chatConfigure.setBackColor(backColor);
//                chatConfigure.setCreateDate(new Date());
//                chatConfigure.setChatRoleId(String.join(",", selectItems));
//                chatConfigure.setFontFamily(textFontFamily);
//                chatConfigure.setUserId(themeDisplay.getUserId());
//                chatConfigure.setUserName(themeDisplay.getUser().getFullName());
//                ChatConfigureLocalServiceUtil.addChatConfigure(chatConfigure);
//            } else {
//                ChatConfigure chatConfigure = (ChatConfigure)chatConfigures.get(0);
//                chatConfigure.setRequestFeature(chatRequestFeature);
//                chatConfigure.setEnable(isEnable);
//                chatConfigure.setHeaderColor(headerColor);
//                chatConfigure.setChatRightColor(rightColor);
//                chatConfigure.setChatLeftColor(leftColor);
//                chatConfigure.setHeaderTextColor(headerTextColor);
//                chatConfigure.setContentTextColor(contentTextColor);
//                chatConfigure.setBackColor(backColor);
//                chatConfigure.setModifiedDate(new Date());
//                chatConfigure.setChatRoleId(String.join(",", selectItems));
//                chatConfigure.setFontFamily(textFontFamily);
//                chatConfigure.setUserId(themeDisplay.getUserId());
//                chatConfigure.setUserName(themeDisplay.getUser().getFullName());
//                ChatConfigureLocalServiceUtil.updateChatConfigure(chatConfigure);
//            }

//            long id = CounterLocalServiceUtil.increment();
//            ChatConfigure chatConfigure = ChatConfigureLocalServiceUtil.createChatConfigure(id);
//            chatConfigure.setGroupId(siteId);
//            chatConfigure.setEnable(isEnable);
//            chatConfigure.setRequestFeature(chatRequestFeature);
//            chatConfigure.setHeaderColor(headerColor);
//            chatConfigure.setChatRightColor(rightColor);
//            chatConfigure.setChatLeftColor(leftColor);
//            chatConfigure.setHeaderTextColor(headerTextColor);
//            chatConfigure.setContentTextColor(contentTextColor);
//            chatConfigure.setBackColor(backColor);
//            chatConfigure.setCreateDate(new Date());
//            chatConfigure.setChatRoleId(String.join(",", selectItems));
//            chatConfigure.setFontFamily(textFontFamily);
//            chatConfigure.setUserId(themeDisplay.getUserId());
//            chatConfigure.setUserName(themeDisplay.getUser().getFullName());



            System.out.printf("Lof for Objetc Data added in custom field");
            DSLQuery dslQuery = DSLQueryFactoryUtil.select().
                    from(ObjectDefinitionTable.INSTANCE).where(ObjectDefinitionTable.INSTANCE.name.eq(OBJECT_NAME));

            List<ObjectDefinition> objectDefinitions = ObjectDefinitionLocalServiceUtil.dslQuery(dslQuery);


            ObjectEntry currEntry = null;
            List<ObjectEntry> entries = ObjectEntryLocalServiceUtil.getObjectEntries(0,objectDefinitions.get(0).getObjectDefinitionId(),-1,-1);

            for(ObjectEntry entry: entries) {
                if(entry.getValues().get("groupCId").equals(siteId)) {
                    currEntry = entry;
                }
            }

            if(currEntry == null){
                Map<String, Serializable> map = new HashMap<>();
                map.put("groupCId", siteId);
                map.put("enable", isEnable);
                map.put("headerColor",headerColor );
                map.put("chatRightColor",rightColor);
                map.put("chatLeftColor", leftColor);
                map.put("headerTextColor",headerTextColor) ;
                map.put("contentTextColor",contentTextColor);
                map.put("backColor",backColor );
                map.put("chatRoleId",String.join(",", selectItems) );
                map.put("requestFeature",chatRequestFeature);
                map.put("createDate",new Date());
                map.put("userUId",themeDisplay.getUserId());
                map.put("fontFamily",textFontFamily);
                map.put("companyCId",themeDisplay.getCompanyId());
                map.put("chatUserName",themeDisplay.getUser().getFullName());

                ServiceContext serviceContext = new ServiceContext();
                serviceContext.setScopeGroupId(0);
                serviceContext.setCompanyId(20096);
                serviceContext.setUserId(themeDisplay.getUserId());

                ObjectEntryLocalServiceUtil.addObjectEntry(themeDisplay.getUserId(), 0, objectDefinitions.get(0).getObjectDefinitionId(), map, serviceContext);
            }else{
                Map<String, Serializable> map = new HashMap<>();
                map.put("groupCId", siteId);
                map.put("enable", isEnable);
                map.put("headerColor",headerColor );
                map.put("chatRightColor",rightColor);
                map.put("chatLeftColor", leftColor);
                map.put("headerTextColor",headerTextColor) ;
                map.put("contentTextColor",contentTextColor);
                map.put("backColor",backColor );
                map.put("chatRoleId",String.join(",", selectItems) );
                map.put("requestFeature",chatRequestFeature);
                map.put("createDate",new Date());
                map.put("userUId",themeDisplay.getUserId());
                map.put("fontFamily",textFontFamily);
                map.put("companyCId",themeDisplay.getCompanyId());
                map.put("chatUserName",themeDisplay.getUser().getFullName());

                ServiceContext serviceContext = new ServiceContext();
                serviceContext.setScopeGroupId(0);
                serviceContext.setCompanyId(20096);
                serviceContext.setUserId(themeDisplay.getUserId());

                ObjectEntryLocalServiceUtil.updateObjectEntry(themeDisplay.getUserId(), currEntry.getObjectEntryId(),  map, serviceContext);

            }


//            ChatConfigureLocalServiceUtil.addChatConfigure(chatConfigure);
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
