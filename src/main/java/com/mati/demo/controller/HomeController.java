package com.mati.demo.controller;

import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.prevalence.BaseModel;

@Controller

public class HomeController {
	
	@Setter @Getter private BaseModel baseModel;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public /*@ResponseBody*/ ModelAndView hello(){
		ModelAndView m = new ModelAndView("home");
		
		m.addObject("followedUsers", baseModel.getModel().getLoggedInUser().getFollowedUsers());
		m.addObject("followedTags", baseModel.getModel().getLoggedInUser().getFollowedTags());
		
		m.addObject("users", baseModel.getModel().getUsers());
		
		return m;
	} 	

}
