package com.mati.demo.controller;

import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

@Controller

public class HomeController {
	
	@Setter @Getter private BaseModel baseModel;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public /*@ResponseBody*/ ModelAndView hello(){
		ModelAndView m = new ModelAndView("home");
		User user = baseModel.getModel().getLoggedInUser();

		if(user != null){
			m.addObject("followedUsers", user.getFollowedUsers());
			m.addObject("followedTags", user.getFollowedTags());
			m.addObject("loggedIn", true);	
		}else{
			m.addObject("loggedIn", false);
		}
		m.addObject("users", baseModel.getModel().getUsers());
		return m;
	} 	

}
