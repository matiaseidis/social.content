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
import com.mati.demo.model.content.type.Event;
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
			ModelAndView m) throws Exception {
		
		User user = baseModel.getModel().getLoggedInUser();
		if(user != null && m != null && SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getName() != null){
			m.addObject("user", user);
			List<User> followedUsers = new ArrayList<User>(user.getFollowedUsers());
			m.addObject("followedUsers", followedUsers);
			m.addObject("followedTags", new ArrayList<Tag>(user.getFollowedTags()));
			
//			List<Content> cl = new ArrayList<Content>(baseModel.getModel().getLoggedInUser().getFollowedContent());
//			modelAndView.addObject("followedContent", cl);
			/*
			 * test followed paged content
			 */
			List<Content> followedContent = user.getFollowedContent(3,1);
			m.addObject("followedContent", followedContent);

			m.addObject("followedBy", new ArrayList<User>(user.getFollowedBy()));
			
			m.addObject("tags", new ArrayList<Tag>(baseModel.getModel().getTags()));
		}

		String userPictureURI = "http://"+serverBasePath+userPictureFolder+"/";
		String userPictureExt = ".png";
		
		request.setAttribute("userPictureURI", userPictureURI);
		request.setAttribute("userPictureExt", userPictureExt);

		super.postHandle(request, response, handler, m);

	}
	

}
