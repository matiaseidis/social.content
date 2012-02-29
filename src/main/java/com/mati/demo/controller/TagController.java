package com.mati.demo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.prevalence.BaseModel;

@Controller
@RequestMapping("tag/")
public class TagController {

		@Resource
		private BaseModel modelProvider;
		
		@RequestMapping(value="list", method=RequestMethod.GET)
		public ModelAndView list(){
			ModelAndView mav = new ModelAndView();
			mav.addObject("tags", modelProvider.getModel().getLoggedInUser().getTags());
			return mav;
		}
		
}