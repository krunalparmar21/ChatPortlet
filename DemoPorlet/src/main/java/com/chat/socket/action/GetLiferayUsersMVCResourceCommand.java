package com.chat.socket.action;
import com.chat.util.ChatServiceUtil;
import com.google.gson.Gson;
import com.chat.socket.model.ChatConfigure;
import com.chat.socket.model.ChatMessage;
import com.chat.socket.model.ChatRequest;
import com.chat.socket.service.ChatConfigureLocalServiceUtil;
import com.chat.socket.service.ChatMessageLocalServiceUtil;
import com.chat.socket.service.ChatRequestLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

@Component(
        immediate = true,
        property = {"javax.portlet.name=com_socket_portlet_SocketPortlet", "mvc.command.name=/getUsers"},
        service = {MVCResourceCommand.class}
)
public class GetLiferayUsersMVCResourceCommand extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(GetLiferayUsersMVCResourceCommand.class);

    public GetLiferayUsersMVCResourceCommand() {
    }


    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
        List<ChatConfigure> chatConfigures = ChatConfigureLocalServiceUtil.FindConfigureByGroupId(themeDisplay.getScopeGroupId());
        List<String> roleIds = new ArrayList();
        if (chatConfigures.size() > 0) {
            String siteRoleIdsForAccess = (chatConfigures.get(0)).getChatRoleId();
            roleIds.addAll(Arrays.asList(siteRoleIdsForAccess.split("\\s*,\\s*")));
        }

        long[] longRoleIds = new long[roleIds.size()];

        for(int i = 0; i < roleIds.size(); ++i) {
            longRoleIds[i] = Long.parseLong((String)roleIds.get(i));
        }

        Set<User> users = new HashSet();
        List<User> groupUsers = new ArrayList();
        groupUsers.addAll(UserLocalServiceUtil.getGroupUsers(themeDisplay.getSiteGroupId()));
        Iterator var9 = groupUsers.iterator();

        while(var9.hasNext()) {
            User user = (User)var9.next();
            if (userHasRoleForAccess(user, themeDisplay.getSiteGroupId(), themeDisplay.getCompanyId(), longRoleIds)) {
                users.add(user);
            }
        }

        var9 = roleIds.iterator();

        while(var9.hasNext()) {
            String roleId = (String)var9.next();
            List<User> roleUsers = UserLocalServiceUtil.getRoleUsers(Long.parseLong(roleId));
            Iterator var12 = roleUsers.iterator();

            while(var12.hasNext()) {
                User user = (User)var12.next();
                if (GroupLocalServiceUtil.hasUserGroup(user.getUserId(), themeDisplay.getSiteGroupId())) {
                    users.add(user);
                }
            }
        }

        Predicate<User> condition = (userx) -> {
            return String.valueOf(userx.getUserId()).equalsIgnoreCase(String.valueOf(themeDisplay.getUserId()));
        };
        users.removeIf(condition);
        Set<ChatRequest> requestedUser = new HashSet();
        DynamicQuery query = ChatRequestLocalServiceUtil.dynamicQuery();
        Disjunction fromUser = RestrictionsFactoryUtil.disjunction();
        fromUser.add(RestrictionsFactoryUtil.eq("fromUserId", themeDisplay.getUserId()));
        fromUser.add(RestrictionsFactoryUtil.eq("toUserId", themeDisplay.getUserId()));
        query.add(fromUser);
        query.add(RestrictionsFactoryUtil.ne("isRejected", true));
        List<ChatRequest> chatReuests = ChatRequestLocalServiceUtil.dynamicQuery(query);
        requestedUser.addAll(chatReuests);


        DynamicQuery recentUserDynamicQuery = ChatMessageLocalServiceUtil.dynamicQuery();
        Disjunction fromUserForUser = RestrictionsFactoryUtil.disjunction();
        fromUserForUser.add(RestrictionsFactoryUtil.eq("fromUser", themeDisplay.getUserId()));
        fromUserForUser.add(RestrictionsFactoryUtil.eq("toUser", themeDisplay.getUserId()));
        recentUserDynamicQuery.addOrder(OrderFactoryUtil.desc("sentDate"));
        recentUserDynamicQuery.add(fromUserForUser);
        List<ChatMessage> recentChats = ChatMessageLocalServiceUtil.dynamicQuery(recentUserDynamicQuery);
        List<Long> recentUserIds = new ArrayList();
        Iterator var18 = recentChats.iterator();

        while(var18.hasNext()) {
            ChatMessage recentChat = (ChatMessage)var18.next();
            if (!recentUserIds.contains(recentChat.getFromUser())) {
                recentUserIds.add(recentChat.getFromUser());
            }

            if (!recentUserIds.contains(recentChat.getToUser())) {
                recentUserIds.add(recentChat.getToUser());
            }
        }

        List<User> recentUsersSet = new ArrayList();
        Iterator var37 = recentUserIds.iterator();

        while(var37.hasNext()) {
            Long userId = (Long)var37.next();
            User user = UserLocalServiceUtil.getUser(userId);
            if (GroupLocalServiceUtil.hasUserGroup(userId, themeDisplay.getSiteGroupId())) {
                if (userHasRoleForAccess(user, themeDisplay.getSiteGroupId(), themeDisplay.getCompanyId(), longRoleIds)) {
                    recentUsersSet.add(user);
                } else if (userHasRegularRoleForAccess(userId, longRoleIds)) {
                    recentUsersSet.add(user);
                }
            }
        }

        recentUsersSet.removeIf(condition);
        users.removeAll(recentUsersSet);
        recentUsersSet.addAll(users);
        Map<Long, String> usersPortrait = new HashMap();
        Map<Long, Integer> userUnreadMessage = new HashMap();
        Iterator var40 = recentUsersSet.iterator();

        while(var40.hasNext()) {
            User user = (User)var40.next();
            usersPortrait.put(user.getUserId(), user.getPortraitURL(themeDisplay));
            userUnreadMessage.put(user.getUserId(), ChatServiceUtil.getUnreadMessageCount(user.getUserId(), themeDisplay.getUserId()));
        }

        JSONObject obj = JSONFactoryUtil.createJSONObject();
        obj.put("users", recentUsersSet);
        obj.put("userPortrait", usersPortrait);
        obj.put("requestedUser", requestedUser);
        obj.put("userUnreadMessage", userUnreadMessage);

        try {
            resourceResponse.getWriter().println(new Gson().toJson(obj));
        } catch (IOException var26) {
            _log.error(var26.getMessage());
        } finally {
            resourceResponse.getWriter().flush();
            resourceResponse.getWriter().close();
        }

    }

    private static boolean userHasRoleForAccess(User user, long siteId, long companyId, long[] roleIds) {
        for(int i = 0; i < roleIds.length; ++i) {
            if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), siteId, roleIds[i], true)) {
                return true;
            }

            try {
                Role role = RoleLocalServiceUtil.getRole(companyId, "Site Member");
                if (role.getRoleId() == roleIds[i]) {
                    return true;
                }
            } catch (PortalException var8) {
                _log.error(var8.getMessage());
            }
        }

        return false;
    }

    public static boolean userHasRegularRoleForAccess(long userId, long[] roleIds) {
        for(int i = 0; i < roleIds.length; ++i) {
            if (UserLocalServiceUtil.hasRoleUser(roleIds[i], userId)) {
                return true;
            }
        }

        return false;
    }
}
