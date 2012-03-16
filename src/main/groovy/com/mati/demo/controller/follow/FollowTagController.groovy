package com.mati.demo.controller.follow;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.tag.Tag
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.tag.StartFollowingTag
import com.mati.demo.prevalence.transaction.tag.StopFollowingTag

@Controller
@RequestMapping("tag/")
public class FollowTagController {

	def baseModel

	@RequestMapping(value="follow/{tagName}", method=RequestMethod.POST)
	public ModelAndView follow(@PathVariable String tagName, ModelAndView m, HttpSession session){
		
		Tag tag = getBaseModel().getModel().loadTagByTagName(tagName);
		
		String message = null;
		try{
//			session.setAttribute("errors", null);
			if(tag != null){
				getBaseModel().getPrevayler().execute(new StartFollowingTag(tag.getTagName()));
			} else{
				/*
				 * TODO handle
				 */
//				return null;
				message = "Bad tag";
			}
		}catch(RuntimeException e){
			message = e.getMessage();
		}	
		session.setAttribute("errors", message);
		m.setViewName("redirect:/profile");
		return m;
	}

	@RequestMapping(value="unfollow/{tagName}", method=RequestMethod.POST)
	public ModelAndView unfollow(@PathVariable String tagName, ModelAndView m, HttpSession session){
		Tag tag = getBaseModel().getModel().loadTagByTagName(tagName);
		String message = null;
		try{
//			session.setAttribute("errors", null);
			if(tag != null){
				getBaseModel().getPrevayler().execute(new StopFollowingTag(tag.getTagName()));
			} else{
				/*
				 * TODO handle
				 */
//				return null;
				message = "Bad tag";
			}
		}catch(RuntimeException e){
			message = e.getMessage();
		}	
		session.setAttribute("errors", message);
		m.setViewName("redirect:/profile");
		return m;
	}
}
