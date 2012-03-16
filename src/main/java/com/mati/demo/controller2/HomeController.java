package com.mati.demo.controller2;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

@Controller
public class HomeController {

	@Autowired @Setter @Getter private BaseModel baseModel;	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public /*@ResponseBody*/ ModelAndView hello(ModelAndView m){

		User user = baseModel.getModel().getLoggedInUser();
		if(user != null){


			List<Content> followedVideos = new ArrayList<Content>(user.getFollowedContent(Video.class));
			m.addObject("followedVideos", followedVideos);

			List<Content> followedPosts = new ArrayList<Content>(user.getFollowedContent(Post.class));
			m.addObject("followedContent", followedPosts);
		}

		m.setViewName("home");
		return m;
	} 	

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public /*@ResponseBody*/ ModelAndView login(ModelAndView m){
		m.setViewName("/login_stand_alone");
		return m;
	}


}
