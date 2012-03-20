package com.mati.demo.controller.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

public class SidebarInterceptor extends HandlerInterceptorAdapter{

	@Autowired @Setter @Getter private BaseModel baseModel;	
	
	@Getter @Setter String serverBasePath;
	@Getter @Setter String userPictureFolder;

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
			
			modelAndView.addObject("tags", new ArrayList<Tag>(baseModel.getModel().getTags()));
		}

		String userPictureURI = "http://"+serverBasePath+userPictureFolder+"/";
		String userPictureExt = ".png";
		
		request.setAttribute("userPictureURI", userPictureURI);
		request.setAttribute("userPictureExt", userPictureExt);

		super.postHandle(request, response, handler, modelAndView);

	}
	

}
