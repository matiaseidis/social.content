package com.mati.demo.controller

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import groovy.lang.GroovyClassLoader
import com.mati.demo.prevalence.BaseModel

@Controller
class ProfileController {
	
	BaseModel baseModel
	
	@RequestMapping("/profile")
	def profile(ModelAndView m) {
		def user = baseModel.getModel().getLoggedInUser()
		m.addObject("user", user)
	}


}
