package com.mati.demo.controller.follow;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mati.demo.prevalence.BaseModel;

@Controller
@RequestMapping("tag")
public class FollowTagController {

	@Autowired @Setter @Getter private BaseModel baseModel;

//	@RequestMapping(value="follow/{tagName}", method=RequestMethod.POST)
//	public ModelAndView follow(@PathVariable String tagName, ModelAndView m, HttpSession session){
//		
//		Tag tag = getBaseModel().getModel().loadTagByTagName(tagName);
//		
//		String message = null;
//		try{
//			if(tag != null){
//				getBaseModel().getPrevayler().execute(new StartFollowingTag(tag.getTagName()));
//			} else{
//				/*
//				 * TODO handle
//				 */
//				message = "Bad tag";
//			}
//		}catch(RuntimeException e){
//			message = e.getMessage();
//		}	
//		session.setAttribute("errors", message);
//		m.setViewName("redirect:/profile");
//		return m;
//	}

//	@RequestMapping(value="unfollow/{tagName}", method=RequestMethod.POST)
//	public ModelAndView unfollow(@PathVariable String tagName, ModelAndView m, HttpSession session){
//		Tag tag = getBaseModel().getModel().loadTagByTagName(tagName);
//		String message = null;
//		try{
//			if(tag != null){
//				getBaseModel().getPrevayler().execute(new StopFollowingTag(tag.getTagName()));
//			} else{
//				/*
//				 * TODO handle
//				 */
//				message = "Bad tag";
//			}
//		}catch(RuntimeException e){
//			message = e.getMessage();
//		}	
//		session.setAttribute("errors", message);
//		m.setViewName("redirect:/profile");
//		return m;
//	}
}
