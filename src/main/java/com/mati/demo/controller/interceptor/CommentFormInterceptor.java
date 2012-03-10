package com.mati.demo.controller.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mati.demo.model.content.Comment;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

public class CommentFormInterceptor extends HandlerInterceptorAdapter{

	@Resource(name="base.model") private BaseModel baseModel;

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		User user = baseModel.getModel().getLoggedInUser();
		if(user != null){
			modelAndView.addObject("comment", new Comment());
		}
		super.postHandle(request, response, handler, modelAndView);

	}
}