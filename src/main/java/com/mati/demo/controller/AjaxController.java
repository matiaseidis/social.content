package com.mati.demo.controller;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.Content;
import com.mati.demo.prevalence.BaseModel;

@Controller
@RequestMapping("ajax")
public class AjaxController {
	
	@Autowired @Setter @Getter private BaseModel baseModel;

	
//	@RequestMapping(value="paginatedContent/{page}/{total}", method=RequestMethod.GET)
//	public @ResponseBody List<Content> show(@PathVariable int page, @PathVariable int total){
//		/*
//		 * TODO parameterize this
//		 * limit max value returned
//		 */
//		total = (total <= 5) ? total : 5;
//		
//		List<Content> result = getBaseModel().getModel().getLoggedInUser().getFollowedContent(total, page);
//		return result;
//	}
	
	@RequestMapping(value="paginatedContent/{page}/{total}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable int page, @PathVariable int total, ModelAndView m){
		/*
		 * TODO parameterize this
		 * limit max value returned
		 */
		total = (total <= 5) ? total : 5;
		
		List<Content> result = getBaseModel().getModel().getLoggedInUser().getFollowedContent(total, page);
		m.addObject("paginatedContent", result);
		m.addObject("page", page);
		m.addObject("total", total);
		m.setViewName("paginated/content");
		return m;
	}

}
