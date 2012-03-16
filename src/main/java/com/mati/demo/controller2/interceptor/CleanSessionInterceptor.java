package com.mati.demo.controller2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CleanSessionInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		boolean post = request.getMethod().equalsIgnoreCase("post");

		if(post){
			request.getSession().setAttribute("count", 1);
		} else {
			if(request.getSession().getAttribute("count") != null){
				int count = Integer.valueOf(request.getSession().getAttribute("count").toString());   
				if(count == 1){
					//vengo de un post
					request.getSession().setAttribute("count", 0);
				} else {
					// count == 0
					// vengo de un get
					if(request.getSession() != null){
						request.getSession().setAttribute("errors", null);
						System.out.println("errors nulled");
					}
				}
			}


		}

		return super.preHandle(request, response, handler);
	}

}
