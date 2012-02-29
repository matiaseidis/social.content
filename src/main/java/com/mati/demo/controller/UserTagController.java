package com.mati.demo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

@Controller
@RequestMapping("user/tags/")
public class UserTagController {

		@Resource
		private BaseModel modelProvider;
		
		@RequestMapping(value="list", method=RequestMethod.GET)
		public ModelAndView list(){
			ModelAndView mav = new ModelAndView();
			mav.addObject("tags", modelProvider.getModel().getLoggedInUser().getTags());
			return mav;
		}
		
		@RequestMapping(value="add", method=RequestMethod.GET)
		public ModelAndView add(){
			return null;
		}
		
		@RequestMapping(value="create", method=RequestMethod.POST)
		public ModelAndView save(@ModelAttribute Tag tag){

			User user = modelProvider.getModel().getLoggedInUser();

			/*
			 * TODO tag validation
			 */

			modelProvider.addTagToUser(tag);
			return new ModelAndView("redirect:list", "tags", user.getTags());
		}

		
}
