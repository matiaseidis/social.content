package com.mati.demo.controller.content;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.PrevalenceModelProvider;
import com.mati.demo.prevalence.transaction.CreatePost;



@Controller
@RequestMapping("content/")
public class PostController {
	
	@Resource
	private PrevalenceModelProvider modelProvider;

	@RequestMapping(value="{contentType}/add", method=RequestMethod.GET)
	public ModelAndView add(@PathVariable String contentType){
		return null;
	}
	
	
	@RequestMapping(value="{contentType}/create", method=RequestMethod.POST)
	public ModelAndView save(
			@PathVariable String contentType, HttpSession session, @ModelAttribute Post command){
		
		User user = modelProvider.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		modelProvider.getPrevayler().execute(new CreatePost(user, command));
		
		return new ModelAndView("redirect:list", "posts", user.getPosts());
	}
	
	@RequestMapping(value="{contentType}/list", method=RequestMethod.GET)
	public ModelAndView list(
			@PathVariable String contentType, HttpSession session){
		
		User user = modelProvider.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		return new ModelAndView("content/post/list", "posts", user.getPosts());
	}

}
