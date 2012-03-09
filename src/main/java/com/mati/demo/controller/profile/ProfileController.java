package com.mati.demo.controller.profile;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

@Controller
public class ProfileController {

	@Resource(name="base.model") private BaseModel baseModel;

	@RequestMapping("/profile")
	public ModelAndView profile(ModelAndView m){
		User user = baseModel.getModel().getLoggedInUser();

		m.addObject("user", user);
		m.addObject("followedUsers", new ArrayList<User>(user.getFollowedUsers()));
		m.addObject("followedTags", new ArrayList<Tag>(user.getFollowedTags()));
		m.addObject("followedBy", new ArrayList<User>(user.getFollowedBy()));
		System.out.println("tags");
		for(Tag tag : baseModel.getModel().getTags()){
			System.out.println(tag.getTagName());
		}
		System.out.println("users: "+user.getFollowedUsers().size());
		for(User u : user.getFollowedUsers()){
			System.out.println(u.getUserName());
		}
		
		
		
		m.addObject("tags", new ArrayList<Tag>(baseModel.getModel().getTags()));
		m.addObject("users", baseModel.getModel().getUsers());

		return m;
	}


	
}
