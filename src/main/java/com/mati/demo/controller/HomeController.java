package com.mati.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.util.ContentMocker;

@Controller
public class HomeController {

	public Logger logger = Logger.getLogger(getClass());

	@Autowired private BaseModel baseModel;	

	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView m, HttpSession session){

		User user = baseModel.getModel().getLoggedInUser();
		
		/*
		 * Mock users and content for DEV
		 */
		if(user == null && baseModel.loadUserByUsername("admin") == null){
			new ContentMocker(baseModel).mock(session);
		}		

		List<Content> bestRated = baseModel.getModel().bestRated();
		List<Content> mostVisited = baseModel.getModel().mostVisited();
		List<Content> mostRecent = baseModel.getModel().mostRecent();
		
		m.addObject("bestRated",bestRated);
		m.addObject("mostVisited",mostVisited);
		m.addObject("mostRecent",mostRecent);
		
/*
 * TODO hay que pensar el home
 */
//		List<Video> videos = new ArrayList<Video>();
//		for(User u : baseModel.getModel().getUsers()){
//			videos.addAll(u.getVideos());
//		}
//		for(Video video : videos){
//			video.setMediaFileRef(video.getUrl());
//		}
//		m.addObject("lastVideos", videos.subList(0, 10));
//		
		
		m.setViewName("home");
		return m;
	} 	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView m){
		m.setViewName("/login_stand_alone");
		return m;
	}

}
