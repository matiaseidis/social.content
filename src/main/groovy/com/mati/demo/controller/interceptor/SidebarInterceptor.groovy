package com.mati.demo.controller.interceptor;

import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mati.demo.model.content.Content
import com.mati.demo.model.tag.Tag
import com.mati.demo.model.user.User

public class SidebarInterceptor extends HandlerInterceptorAdapter{

	def baseModel;
	
	String serverBasePath;
	String userPictureFolder;

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		User user = baseModel.getModel().getLoggedInUser();
		if(user != null && modelAndView != null && SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getName() != null){
			modelAndView.addObject("user", user);
			List<User> followedUsers = new ArrayList<User>(user.getFollowedUsers());
			modelAndView.addObject("followedUsers", followedUsers);
			modelAndView.addObject("followedTags", new ArrayList<Tag>(user.getFollowedTags()));
			
			List<Content> cl = new ArrayList<Content>(baseModel.getModel().getLoggedInUser().getFollowedContent());
			modelAndView.addObject("followedContent", cl);
			modelAndView.addObject("followedBy", new ArrayList<User>(user.getFollowedBy()));
			
			String userPictureURI = "http://"+serverBasePath+userPictureFolder+"/";
			
			String userPictureExt = ".png";
			
			request.setAttribute("userPictureURI", userPictureURI);
			request.setAttribute("userPictureExt", userPictureExt);


			modelAndView.addObject("tags", new ArrayList<Tag>(baseModel.getModel().getTags()));
		}
		super.postHandle(request, response, handler, modelAndView);

	}
	

}
