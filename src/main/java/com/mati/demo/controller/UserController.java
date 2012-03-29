package com.mati.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.user.User;
import com.mati.demo.model.validator.Validator;
import com.mati.demo.model.validator.user.UserProfileUpdateValidator;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.user.UpdateProfile;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{

	
	@Autowired @Setter @Getter private BaseModel baseModel;
	
	@RequestMapping(value="{username}/videos", method=RequestMethod.GET)
	public ModelAndView videos(@PathVariable String username){
		ModelAndView mav = new ModelAndView();
		User user = getBaseModel().getModel().loadUserByUsername(username);
		List<Video> videos = null;
		if(user != null){
			videos = user.getVideos();
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
			content = user.getContent();
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
		m.addObject("audios", baseModel.getModel().getLoggedInUser().getAudios());
		m.addObject("posts", baseModel.getModel().getLoggedInUser().getPosts());
		m.addObject("events", baseModel.getModel().getLoggedInUser().getEvents());
		
		return m;
	}
	
	@RequestMapping("profile/edit")
	public ModelAndView editProfile(ModelAndView m){
		User u = baseModel.getModel().getLoggedInUser();

		if(u == null){
			//TODO handle
		}		
		m.addObject("profileUser", u);
		m.addObject(ACTION, UPDATE);
		return m;
	}
	
	@RequestMapping(value="profile/update", method=RequestMethod.POST)
	public ModelAndView updateProfile(/*@ModelAttribute User user,*/@RequestParam String email, @RequestParam String info,  ModelAndView m, HttpSession session){
		User u = baseModel.getModel().getLoggedInUser();
		
		/*
		 * TODO ver de cambiar esto
		 */
		u.setEmail(email);
		u.setInfo(info);

		Validator v = new UserProfileUpdateValidator(u);

		if(v.validate()){
			baseModel.getPrevayler().execute(new UpdateProfile(u.getUserName(), email, info));
			session.setAttribute(MESSAGE, "Su perfil se actualizo");
			m.setViewName("redirect:/profile");
		} else {
			m.addObject(ERRORS, v.getErrors());
			m.addObject("profileUser", u);
			m.addObject(ACTION, UPDATE);
			m.setViewName("user/profile/edit");
		}
		
		return m;
	}

	@Override
	protected Object createEntity() {
		return new User();
	}


}
	