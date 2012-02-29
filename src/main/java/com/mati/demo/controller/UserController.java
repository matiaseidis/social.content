package com.mati.demo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
@RequestMapping("user/")
public class UserController {

	@Resource
	private BaseModel modelProvider;
	
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView list(){
		return new ModelAndView("user/list", "users", modelProvider.getModel().getUsers());
	}
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView add(){
		return null;
	}
	
//	@RequestMapping(value="/", method=RequestMethod.GET)
//	public ModelAndView index(){
//		return new ModelAndView("redirect:list", "users", modelProvider.getModel().getUsers());
//	}
	
	@RequestMapping(value="create", method=RequestMethod.POST)
	public ModelAndView save(@ModelAttribute User user, final HttpSession httpSession){

		boolean available = (modelProvider.getModel().loadUserByUsername(user.getUserName()) == null);
		
		if(available){
			
			if(CollectionUtils.isEmpty(user.getRoles())){
				user.getRoles().add(ROLE_USER);
				if(user.getUserName().equals("admin")){
					user.getRoles().add(ROLE_ADMIN);
				}
			}
			
			modelProvider.getPrevayler().execute(new CreateUser(user));
			
		}
		
		return new ModelAndView("redirect:list", "users", modelProvider.getModel().getUsers());
	}

}
	