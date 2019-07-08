package com.cskaoyan.config;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by little Stone
 * Date 2019/6/3 Time 15:30
 */
@Component
public class MallShiroSessionManager extends DefaultWebSessionManager {

	public static final String LOGIN_TOKEN_KEY = "X-Litemall-Admin-Token";
	private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";
	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		String id = servletRequest.getHeader(LOGIN_TOKEN_KEY);

		if (!StringUtils.isEmpty(id)){
			//request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,REFERENCED_SESSION_ID_SOURCE);
			//request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,id);
			//request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
			return id;
		}


		return super.getSessionId(request, response);
	}


}
