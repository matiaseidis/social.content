package com.mati.demo.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

public class LoggedInUserInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired @Setter @Getter private BaseModel baseModel;
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView m) throws Exception {
	
		User user = baseModel.getModel().getLoggedInUser();
		if(user != null && m != null && SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getName() != null){
			m.addObject("user", user);
		}
		super.postHandle(request, response, handler, m);
	}

}
