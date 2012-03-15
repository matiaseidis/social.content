package com.mati.demo.controller.profile;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

@Controller
public class ProfileController {

	@Resource(name="base.model") private BaseModel baseModel;

	@RequestMapping("/profile")
	public ModelAndView profile(ModelAndView m){
		User u = baseModel.getModel().getLoggedInUser();

		if(u == null){
			//TODO handle
		}		
		m.addObject("own", true);
		m.addObject("profileUser", u);

		return m;
	}

	@RequestMapping("/profile/{userName}")
	public ModelAndView profile(@PathVariable String userName, ModelAndView m){
		User u = baseModel.getModel().loadUserByUsername(userName);

		if(u == null){
			//TODO handle
		}		

		m.addObject("profileUser", u);
		m.addObject("followed", baseModel.getModel().getLoggedInUser().isFollowing(u));

		m.setViewName("profile");
		return m;
	}



}
