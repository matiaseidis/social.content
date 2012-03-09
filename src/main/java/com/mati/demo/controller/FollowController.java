package com.mati.demo.controller;

import javax.servlet.http.HttpSession;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.user.StartFollowingUser;
import com.mati.demo.prevalence.transaction.user.StopFollowingUser;

@Controller
@RequestMapping("user/")
public class FollowController {

	@Autowired @Setter @Getter private BaseModel baseModel;

	@RequestMapping(value="follow/{username}", method=RequestMethod.POST)
	public ModelAndView follow(@PathVariable String username, ModelAndView m, HttpSession session){
		User user = getBaseModel().getModel().loadUserByUsername(username);

		try{
			session.setAttribute("error", null);
			if(user != null){
				getBaseModel().getPrevayler().execute(new StartFollowingUser(user.getUserName()));
			} else{
				/*
				 * TODO handle
				 */
//				return null;
				session.setAttribute("error", "Bad user");
			}
		}catch(RuntimeException e){
			session.setAttribute("error", e.getMessage());
		}	
		m.setViewName("redirect:/profile");
		return m;
	}

	@RequestMapping(value="unfollow/{username}", method=RequestMethod.POST)
	public ModelAndView unfollow(@PathVariable String username, ModelAndView m, HttpSession session){
		User user = getBaseModel().getModel().loadUserByUsername(username);
		try{
			session.setAttribute("error", null);
			if(user != null){
				getBaseModel().getPrevayler().execute(new StopFollowingUser(user.getUserName()));
			} else{
				/*
				 * TODO handle
				 */
//				return null;
				session.setAttribute("error", "Bad user");
			}
		}catch(RuntimeException e){
			session.setAttribute("error", e.getMessage());
		}	
		m.setViewName("redirect:/profile");
		return m;
	}
}
