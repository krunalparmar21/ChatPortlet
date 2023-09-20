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
			"key=login.events.post"
		},
		service = LifecycleAction.class
	)
public class PostLoginAction implements LifecycleAction {

	private static Log _log = LogFactoryUtil.getLog(PostLoginAction.class);

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {


		final HttpServletRequest req = lifecycleEvent.getRequest();
		final String userId = req.getRemoteUser();
		final WebsocketService service = new WebsocketServiceImpl();
		service.clearStorage(Long.parseLong(userId));
		_log.info("PostLogin Action Call" + userId);
	}


}
