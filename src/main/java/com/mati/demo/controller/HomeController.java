package com.mati.demo.controller;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

@Controller

public class HomeController {
	
	@Autowired @Setter @Getter private BaseModel baseModel;	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public /*@ResponseBody*/ ModelAndView hello(ModelAndView m){
		m.setViewName("home");
		return m;
	} 	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public /*@ResponseBody*/ ModelAndView login(ModelAndView m){
		m.setViewName("/login_stand_alone");
		
		System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
		
		return m;
	}
	

}
