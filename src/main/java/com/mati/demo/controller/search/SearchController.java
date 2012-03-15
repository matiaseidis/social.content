package com.mati.demo.controller.search;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;



@Controller
@RequestMapping("/search")
public class SearchController {
		
	@Autowired @Setter @Getter private BaseModel baseModel;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ModelAndView search(ModelAndView m){ return m;}
	
	@RequestMapping(value="content", method = RequestMethod.POST)
	public ModelAndView searchContent(ModelAndView m, @RequestParam String pattern, HttpSession session){ 
		
		List<Content> content = null;
		
		if(StringUtils.isNotEmpty(pattern)){
			content = baseModel.getModel().searchContent(pattern); 
		}
		session.setAttribute("contentSearchResult", content);
		m.setViewName("/search");
		return m;
		
	}
	
	@RequestMapping(value="users", method = RequestMethod.POST)
	public ModelAndView searchUsers(ModelAndView m, @RequestParam String pattern, HttpSession session){ 
		
		List<User> users = null;
		
		if(StringUtils.isNotEmpty(pattern)){
			users = baseModel.getModel().searchUsers(pattern); 
		}
		session.setAttribute("usersSearchResult", users);
		m.setViewName("/search");
		return m;
		
	}
	
	@RequestMapping(value="tags", method = RequestMethod.POST)
	public ModelAndView searchTags(ModelAndView m, @RequestParam String pattern, HttpSession session){ 
		
		List<Tag> tags = null;
		
		if(StringUtils.isNotEmpty(pattern)){
			tags = baseModel.getModel().searchTags(pattern); 
		}
		session.setAttribute("tagsSearchResult", tags);
		m.setViewName("/search");
		return m;
		
	}



}
