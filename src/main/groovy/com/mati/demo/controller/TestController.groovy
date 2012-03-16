package com.mati.demo.controller

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.prevalence.BaseModel;

@Controller
class TestController {
	
	@Autowired BaseModel baseModel
	
	@RequestMapping("/test")
	def ModelAndView profile(ModelAndView m) {
		println baseModel
		m
	}


}
