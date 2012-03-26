package com.mati.demo.controller.follow;

import javax.servlet.http.HttpSession;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.tag.StartFollowingTag;
import com.mati.demo.prevalence.transaction.tag.StopFollowingTag;

@Controller
@RequestMapping("tag")
public class FollowTagController {

	@Autowired @Setter @Getter private BaseModel baseModel;

	@RequestMapping(value="follow/{tagName}", method=RequestMethod.POST)
	public ModelAndView follow(@PathVariable String tagName, ModelAndView m, HttpSession session){

		Tag tag = getBaseModel().getModel().loadTagByTagName(tagName);

		User loggedInUser = baseModel.getModel().getLoggedInUser();

		if(tag != null){
			getBaseModel().getPrevayler().execute(new StartFollowingTag(tag.getTagName(), loggedInUser.getUserName()));
		} else{
			session.setAttribute("message", "Bad tag");
		}
		m.setViewName("redirect:/profile");
		return m;
	}

	@RequestMapping(value="unfollow/{tagName}", method=RequestMethod.POST)
	public ModelAndView unfollow(@PathVariable String tagName, ModelAndView m, HttpSession session){

		Tag tag = getBaseModel().getModel().loadTagByTagName(tagName);

		User loggedInUser = baseModel.getModel().getLoggedInUser();

		if(tag != null){
			getBaseModel().getPrevayler().execute(new StopFollowingTag(tag.getTagName(), loggedInUser.getUserName()));
		} else{
			session.setAttribute("errors", "Bad tag");
		}
		m.setViewName("redirect:/profile");
		return m;
	}
}
