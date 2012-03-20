package com.mati.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

@Controller
public class HomeController {

	@Autowired private BaseModel baseModel;	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public /*@ResponseBody*/ ModelAndView hello(ModelAndView m){

		User user = baseModel.getModel().getLoggedInUser();
		if(user != null){

			List<Video> followedVideos = user.getFollowedVideos();
			m.addObject("followedVideos", followedVideos);

			List<Post> followedPosts = user.getFollowedPosts();
			m.addObject("followedContent", followedPosts);
		}

		List<Video> videos = new ArrayList<Video>();
		for(User u : baseModel.getModel().getUsers()){
			
			videos.addAll(u.getVideos());
		}
		
		for(Video video : videos){
			
				video.setVideoRef(video.getUrl());
//				content.setThumbnailUri(youTubeThumbnailUri(content));
			
		}
		
		m.addObject("lastVideos", videos);
		m.setViewName("home");
		return m;
	} 	

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public /*@ResponseBody*/ ModelAndView login(ModelAndView m){
		m.setViewName("/login_stand_alone");
		return m;
	}


}
