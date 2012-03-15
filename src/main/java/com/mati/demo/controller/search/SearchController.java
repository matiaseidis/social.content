package com.mati.demo.controller.search;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.Content;
import com.mati.demo.prevalence.BaseModel;



@Controller
@RequestMapping("/search")
public class SearchController {
		
	@Autowired @Setter @Getter private BaseModel baseModel;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ModelAndView search(ModelAndView m){ return m;}
	
	@RequestMapping(value="content", method = RequestMethod.GET)
	public ModelAndView search(ModelAndView m, @RequestParam String pattern){ 
		
		List<Content> content = null;
		
		if(StringUtils.isNotEmpty(pattern)){
			content = baseModel.getModel().searchContent(pattern); 
		}
		m.addObject("searchResult", content);
		m.setViewName("/search");
		return m;
		
	}

}
