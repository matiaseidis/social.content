package com.mati.demo.controller.profile;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

@Controller
public class ProfileController {

	@Resource(name="base.model") private BaseModel baseModel;

	@RequestMapping("/profile")
	public ModelAndView profile(ModelAndView m){
		User user = baseModel.getModel().getLoggedInUser();

		m.addObject("user", user);
		m.addObject("followedUsers", user.getFollowedUsers());
		m.addObject("followedTags", user.getFollowedTags());
		m.addObject("followedBy", user.getFollowedBy());
		m.addObject("users", baseModel.getModel().getUsers());

		return m;
	}


}
