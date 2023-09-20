package com.chat.fragement;

import com.chat.common.WebsocketService;
import com.chat.common.WebsocketServiceImpl;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;

import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"key=logout.events.post"
		},
		service = LifecycleAction.class
	)
public class PostLogoutAction implements LifecycleAction {

	private static Log _log = LogFactoryUtil.getLog(PostLogoutAction.class);

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {


		final HttpServletRequest req = lifecycleEvent.getRequest();
		final WebsocketService service = new WebsocketServiceImpl();
		final String userId = req.getRemoteUser();
		service.clearStorage(Long.parseLong(userId));
		req.setAttribute("userId", (Object)userId);
		_log.info("Post LogOut Action" + lifecycleEvent);
	}

}
