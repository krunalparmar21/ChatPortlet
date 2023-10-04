package com.chat.configuration.action;
import com.liferay.object.model.ObjectAction;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectDefinitionTable;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalServiceUtil;
import com.liferay.object.service.ObjectEntryLocalServiceUtil;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.chat.socket.model.ChatConfigure;
import com.chat.socket.service.ChatConfigureLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;


import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;


import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {"javax.portlet.name=com_chat_configuration_ChatConfgirationPortlet", "mvc.command.name=/getSiteData"},
		service = {MVCResourceCommand.class}
)
public class GetSiteDataMVCResourceCommand extends BaseMVCResourceCommand {

	private static final String OBJECT_NAME = "C_ChatConfigure";

	public static final String COMPANY_ID = "companyCId";
	private static Log _log = LogFactoryUtil.getLog(GetSiteDataMVCResourceCommand.class);

	public GetSiteDataMVCResourceCommand() {
	}

	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");

		long groupId = ParamUtil.getLong(resourceRequest, "groupId");

		final List<ChatConfigure> chatConfigures = (List<ChatConfigure>) ChatConfigureLocalServiceUtil.FindConfigureByGroupId(groupId);
		final List<Role> roles = (List<Role>) RoleLocalServiceUtil.getRoles(themeDisplay.getCompanyId(), new int[]{1, 2});

//			DSLQuery dslQuery = DSLQueryFactoryUtil.select().
//					from(ObjectDefinitionTable.INSTANCE).where(ObjectDefinitionTable.INSTANCE.name.eq(OBJECT_NAME));
//
//			List<ObjectDefinition> objectDefinitions = ObjectDefinitionLocalServiceUtil.dslQuery(dslQuery);
//			ObjectEntry currEntry = null;
//			List<ObjectEntry> entries = ObjectEntryLocalServiceUtil.getObjectEntries(0,objectDefinitions.get(0).getObjectDefinitionId(),-1,-1);
//
//			for(ObjectEntry entry: entries) {
//				if(entry.getValues().get("groupCId").equals(themeDisplay.getCompanyId())) {
//					currEntry = entry;
//				}
//			}

		DSLQuery dslQuery = DSLQueryFactoryUtil.select().
				from(ObjectDefinitionTable.INSTANCE).where(ObjectDefinitionTable.INSTANCE.name.eq(OBJECT_NAME));

		List<ObjectDefinition> objectDefinitions = ObjectDefinitionLocalServiceUtil.dslQuery(dslQuery);

		List<ObjectEntry> objectEntryList = ObjectEntryLocalServiceUtil.getObjectEntries(0, objectDefinitions.get(0).getObjectDefinitionId(), -1, -1);
		for (ObjectEntry entry : objectEntryList) {

			Map<String, Serializable> entryValues = entry.getValues();
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			for (Map.Entry<String, Serializable> entry1 : entryValues.entrySet()) {
				String key = entry1.getKey();
				Serializable value = entry1.getValue();
				jsonObject.put(key, value);
			}
			// Now, you can use the key and value as needed.
			System.out.println("JSON :" + jsonObject);
//					System.out.println("Key: " + key);
//					System.out.println("Value: " + value);
			try {
				resourceResponse.getWriter().println(jsonObject.toJSONString());
			} catch (IOException var13) {
				_log.error(var13.getMessage());
			} finally {
				resourceResponse.getWriter().flush();
				resourceResponse.getWriter().close();
			}

		}

	}

//	        if (entries.size() > 0) {
//	            ChatConfigure chatConfigure = (ChatConfigure)chatConfigures.get(0);
//	            jsonObject.put("requestFeature", chatConfigure.getRequestFeature());
//	            jsonObject.put("headerColor", chatConfigure.getHeaderColor());
//	            jsonObject.put("chatRightColor", chatConfigure.getChatRightColor());
//	            jsonObject.put("chatLeftColor", chatConfigure.getChatLeftColor());
//	            jsonObject.put("headerTextColor", chatConfigure.getHeaderTextColor());
//	            jsonObject.put("contentTextColor", chatConfigure.getContentTextColor());
//	            jsonObject.put("backColor", chatConfigure.getBackColor());
//	            jsonObject.put("groupId", groupId);
//	            jsonObject.put("roles", roles);
//	            jsonObject.put("selectedRole", chatConfigure.getChatRoleId());
//	            jsonObject.put("fontFamily", chatConfigure.getFontFamily());
//	        } else {
//	            jsonObject.put("requestFeature", false);
//	            jsonObject.put("isEnable", false);
//	            jsonObject.put("headerColor", "");
//	            jsonObject.put("chatRightColor", "");
//	            jsonObject.put("chatLeftColor", "");
//	            jsonObject.put("headerTextColor", "");
//	            jsonObject.put("contentTextColor", "");
//	            jsonObject.put("backColor", "");
//	            jsonObject.put("groupId", groupId);
//	            jsonObject.put("roles", roles);
//	            jsonObject.put("selectedRole", "");
//	            jsonObject.put("fontFamily", "");
//	        }



}
