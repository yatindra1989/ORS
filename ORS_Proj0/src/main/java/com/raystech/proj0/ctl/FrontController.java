package com.raystech.proj0.ctl;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Front controller will check authentication. If user is not logged-in then
 * forward control to login page.
 * 
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */
public class FrontController extends HandlerInterceptorAdapter {
	/**
	 * i18n Message source
	 */
	@Autowired
	MessageSource messageSource;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			/*
			 * String msg = messageSource.getMessage("message.sessionMsg",
			 * null,Locale locale);
			 */

			String msg = "Your session has been expired. Please re-login to continue";
			request.setAttribute("error", msg);

			RequestDispatcher rd = request.getRequestDispatcher("/Login");
			rd.forward(request, response);
			// response.sendRedirect("/Login");
			return false;
		}
		return true;
	}
}
