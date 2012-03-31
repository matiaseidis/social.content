package com.mati.demo.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CleanSessionInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean post = request.getMethod().equalsIgnoreCase("post");

		if(post){
			request.getSession().setAttribute("count", 1);
		} else {
			if(request.getSession().getAttribute("count") != null){
				int count = Integer.valueOf(request.getSession().getAttribute("count").toString());   
				if(count == 1){
					//el anterior request fue un post
					request.getSession().setAttribute("count", 0);
				} else {
					// count == 0
					//el anterior request fue un get
					if(request.getSession() != null){
						request.getSession().setAttribute("errors", null);
						request.getSession().setAttribute("message", null);
					}
				}
			}


		}

		return super.preHandle(request, response, handler);
	}

}
