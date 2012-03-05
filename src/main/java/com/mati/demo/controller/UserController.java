package com.mati.demo.controller;

import java.util.Collection;

import javax.annotation.Resource;

import lombok.Getter;
import lombok.Setter;

import org.prevayler.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mati.demo.prevalence.BaseModel;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{

	
	@Setter @Getter private BaseModel baseModel;
	
	@Override
	protected Transaction deleteTransaction(int nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Collection listEntities() {
		return getBaseModel().getModel().getUsers();
	}

	@Override
	protected Object getEntity(int nodeId) {
		return getBaseModel().getModel().getLoggedInUser();
	}

//	public static final String ROLE_USER = "ROLE_USER";
//	
//	@RequestMapping(value="/list", method=RequestMethod.GET)
//	public ModelAndView list(){
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("users", getBaseModel().getModel().getUsers());
//		return mav;
//	}
//	
//	@RequestMapping(value="/edit", method=RequestMethod.GET)
//	public ModelAndView edit(){
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("user", getBaseModel().getModel().getLoggedInUser());
//		return mav;
//	}
//	
//	@RequestMapping(value="/add", method=RequestMethod.GET)
//	public ModelAndView add(){
//		return null;
//	}
//	
//	@RequestMapping(value="", method=RequestMethod.GET)
//	public ModelAndView index(){
//		return new ModelAndView("redirect:/user/list", "users", getBaseModel().getModel().getUsers());
//	}
//	
//	@RequestMapping(value="/create", method=RequestMethod.POST)
//	public ModelAndView create(@ModelAttribute User user){
//
//		boolean available = (baseModel.getModel().loadUserByUsername(user.getUserName()) == null);
//		
//		if(available){
//			if(CollectionUtils.isEmpty(user.getRoles())){
//				user.getRoles().add(ROLE_USER);
//			}
//			
//			baseModel.getPrevayler().execute(new CreateUser(user));
//		}
//		return new ModelAndView("redirect:/user/list", "users", getBaseModel().getModel().getUsers());
//	}
//	
//	@RequestMapping(value="/update", method=RequestMethod.POST)
//	public ModelAndView update(@ModelAttribute User user){
//		
//		/*
//		 * TODO update user
//		 */
//		return new ModelAndView("redirect:/user/list", "users", getBaseModel().getModel().getUsers());
//	}

}
	