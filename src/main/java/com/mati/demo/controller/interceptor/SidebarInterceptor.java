package com.mati.demo.controller.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

public class SidebarInterceptor extends HandlerInterceptorAdapter{

	@Resource(name="base.model") private BaseModel baseModel;

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		User user = baseModel.getModel().getLoggedInUser();
		if(user != null){
			modelAndView.addObject("user", user);
			List<User> followedUsers = new ArrayList<User>(user.getFollowedUsers());
			modelAndView.addObject("followedUsers", followedUsers);
			modelAndView.addObject("followedTags", new ArrayList<Tag>(user.getFollowedTags()));
			modelAndView.addObject("followedBy", new ArrayList<User>(user.getFollowedBy()));
//			System.out.println("tags");
//			System.out.println(baseModel.getModel().getTags());
//			for(Tag tag : baseModel.getModel().getTags()){
//				System.out.println(tag.getTagName());
//			}
//			System.out.println("users: "+user.getFollowedUsers().size());
//			for(User u : user.getFollowedUsers()){
//				System.out.println(u.getUserName());
//			}


			modelAndView.addObject("tags", new ArrayList<Tag>(baseModel.getModel().getTags()));
			System.out.println("asdasd");
		}
		super.postHandle(request, response, handler, modelAndView);

	}
	

}
