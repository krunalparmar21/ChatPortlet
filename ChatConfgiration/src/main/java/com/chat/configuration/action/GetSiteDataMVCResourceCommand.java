package com.chat.configuration.action;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.chat.socket.model.ChatConfigure;
import com.chat.socket.service.ChatConfigureLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;


import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;


import org.osgi.service.component.annotations.Component;

@Component(
	    immediate = true,
	    property = {"javax.portlet.name=com_chat_configuration_ChatConfgirationPortlet", "mvc.command.name=/getSiteData"},
	    service = {MVCResourceCommand.class}
	)
public class GetSiteDataMVCResourceCommand extends BaseMVCResourceCommand {

	private static Log _log = LogFactoryUtil.getLog(GetSiteDataMVCResourceCommand.class);

	    public GetSiteDataMVCResourceCommand() {
	    }

	    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

	        ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
			//_log.info("Company Id themeDisplay " + themeDisplay.getCompanyId());
	        long groupId = ParamUtil.getLong(resourceRequest, "groupId");

//			final List<Role> myroles = RoleLocalServiceUtil.getRoles(themeDisplay.getCompanyId());
//			myroles.forEach(role -> {
//				_log.info("Role Name = " + role.getName());
//            });

//			long groupId = 20119;
//
//	        System.out.println("GroupID"+groupId);
//
			final List<ChatConfigure> chatConfigures = (List<ChatConfigure>)ChatConfigureLocalServiceUtil.FindConfigureByGroupId(groupId);

			final List<Role> roles = (List<Role>)RoleLocalServiceUtil.getRoles(themeDisplay.getCompanyId(), new int[] { 1, 2 });
			//long companyId = 20096;
			//final  List<Role> roles = RoleLocalServiceUtil.getRoles(companyId);

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
	        if (chatConfigures.size() > 0) {
	            ChatConfigure chatConfigure = (ChatConfigure)chatConfigures.get(0);
	            jsonObject.put("isEnable", chatConfigure.getEnable());
	            jsonObject.put("requestFeature", chatConfigure.getRequestFeature());
	            jsonObject.put("headerColor", chatConfigure.getHeaderColor());
	            jsonObject.put("chatRightColor", chatConfigure.getChatRightColor());
	            jsonObject.put("chatLeftColor", chatConfigure.getChatLeftColor());
	            jsonObject.put("headerTextColor", chatConfigure.getHeaderTextColor());
	            jsonObject.put("contentTextColor", chatConfigure.getContentTextColor());
	            jsonObject.put("backColor", chatConfigure.getBackColor());
	            jsonObject.put("groupId", groupId);
	            jsonObject.put("roles", roles);
	            jsonObject.put("selectedRole", chatConfigure.getChatRoleId());
	            jsonObject.put("fontFamily", chatConfigure.getFontFamily());
	        } else {
	            jsonObject.put("requestFeature", false);
	            jsonObject.put("isEnable", false);
	            jsonObject.put("headerColor", "");
	            jsonObject.put("chatRightColor", "");
	            jsonObject.put("chatLeftColor", "");
	            jsonObject.put("headerTextColor", "");
	            jsonObject.put("contentTextColor", "");
	            jsonObject.put("backColor", "");
	            jsonObject.put("groupId", groupId);
	            jsonObject.put("roles", roles);
	            jsonObject.put("selectedRole", "");
	            jsonObject.put("fontFamily", "");
	        }

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
