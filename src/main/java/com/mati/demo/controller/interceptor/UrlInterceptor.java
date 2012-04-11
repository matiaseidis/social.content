package com.mati.demo.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UrlInterceptor extends HandlerInterceptorAdapter{

	public Logger logger = Logger.getLogger(getClass());
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if(!request.getRequestURI().contains("ajax")){
			logger.info(request.getRequestURI());

			HttpSession session = request.getSession();
			
			RequestCache requestCache = new HttpSessionRequestCache();
			
			requestCache.saveRequest(request, response);
			
			session.setAttribute("requestCache", requestCache);
		}
		
		super.postHandle(request, response, handler, modelAndView);
	}
}
