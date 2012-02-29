package com.mati.demo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.CreateUser;

@Controller
@RequestMapping("user")
public class UserController {

	@Resource
	private BaseModel baseModel;
	
	public static final String ROLE_USER = "ROLE_USER";
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", baseModel.getModel().getUsers());
		return mav;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView edit(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", baseModel.getModel().getLoggedInUser());
		return mav;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView add(){
		return null;
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView("redirect:/user/list", "users", baseModel.getModel().getUsers());
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView create(@ModelAttribute User user){

		boolean available = (baseModel.getModel().loadUserByUsername(user.getUserName()) == null);
		
		if(available){
			if(CollectionUtils.isEmpty(user.getRoles())){
				user.getRoles().add(ROLE_USER);
			}
			
			baseModel.getPrevayler().execute(new CreateUser(user));
		}
		return new ModelAndView("redirect:/user/list", "users", baseModel.getModel().getUsers());
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ModelAndView update(@ModelAttribute User user){
		
		/*
		 * TODO update user
		 */
		return new ModelAndView("redirect:/user/list", "users", baseModel.getModel().getUsers());
	}

}
	