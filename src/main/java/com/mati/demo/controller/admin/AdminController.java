package com.mati.demo.controller.admin;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.prevalence.BaseModel;


@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
	
	@Autowired @Setter @Getter private BaseModel baseModel;	

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public ModelAndView admin(ModelAndView m){
		
		/*
		 * TODO this is just for devel
		 */
		m.addObject("users", baseModel.getModel().getUsers());
		return m;
	
	}
	
	@RequestMapping(value="/admin/content", method = RequestMethod.GET)
	public ModelAndView content(ModelAndView m){
		
		/*
		 * TODO this is just for devel
		 */
//		m.addObject("content", baseModel.getModel().getContent());
		return m;
	
	}
	
	
	

}
