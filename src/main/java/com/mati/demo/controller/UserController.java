package com.mati.demo.controller;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

@Controller
@RequestMapping("user/")
public class UserController extends BaseController{

	
	@Autowired @Setter @Getter private BaseModel baseModel;
	
	@RequestMapping(value="{username}/videos", method=RequestMethod.GET)
	public ModelAndView videos(@PathVariable String username){
		ModelAndView mav = new ModelAndView();
		User user = getBaseModel().getModel().loadUserByUsername(username);
		List<Video> videos = null;
		if(user != null){
			videos = new ArrayList<Video>(user.getContent(Video.class));
		}
		
		mav.addObject("videos", videos);
		mav.addObject(getEntityName(), user);
		mav.setViewName("/"+getEntityName()+"/content/videos");
		return mav;
	}
	
	@RequestMapping(value="{username}/content", method=RequestMethod.GET)
	public ModelAndView content(@PathVariable String username){
		ModelAndView mav = new ModelAndView();
		User user = getBaseModel().getModel().loadUserByUsername(username);
		List<Content> content = null;
		if(user != null){
			content = new ArrayList<Content>(user.getContent());
		}
		
 		mav.addObject("content", content);
		mav.addObject(getEntityName(), user);
		mav.setViewName("/"+getEntityName()+"/content");
		return mav;
	}
	
	@RequestMapping(value="content/add", method=RequestMethod.GET)
	public ModelAndView addContent(ModelAndView m){
		return m;
	}

	@RequestMapping(value="content", method=RequestMethod.GET)
	public ModelAndView content(ModelAndView m){
		
		m.addObject("videos", baseModel.getModel().getLoggedInUser().getVideos());
		m.addObject("posts", baseModel.getModel().getLoggedInUser().getPosts());
		
		return m;
	}
	
	@RequestMapping("profile/edit")
	public ModelAndView editProfile(ModelAndView m){
		User u = baseModel.getModel().getLoggedInUser();

		if(u == null){
			//TODO handle
		}		
		m.addObject("profileUser", u);
		return m;
	}


}
	