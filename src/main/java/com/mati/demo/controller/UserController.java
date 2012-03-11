package com.mati.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.prevayler.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

@Controller
@RequestMapping("user/")
public class UserController extends BaseController{

	
	@Autowired @Setter @Getter private BaseModel baseModel;
	
//	@Override
//	protected Transaction deleteTransaction(int nodeId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	protected Collection listEntities() {
//		Comparator userNameComparator = new Comparator<User>(){
//
//			public int compare(User o1, User o2) {
//				return o1.getUserName().compareTo(o2.getUserName());
//			}
//		};
//		List<User> users = new ArrayList<User>(getBaseModel().getModel().getUsers());
//		Collections.sort(users, userNameComparator);
//		return users;
//	}
	
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
	
	@RequestMapping(value="content/add", method=RequestMethod.GET)
	public ModelAndView addContent(ModelAndView m){
		return m;
	}
	
//	public ModelAndView add(){
//	public static final String ROLE_USER = "ROLE_USER";
//	
//	@RequestMapping(value="/list", method=RequestMethod.GET)
//	public ModelAndView list(){
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("users", getBaseModel().getModel().getUsers());
//		return mav;
//	}
//	
//	@RequestMapping(value="/edit", method=RequestMethod.GET)
//	public ModelAndView edit(){
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("user", getBaseModel().getModel().getLoggedInUser());
//		return mav;
//	}
//	
//	@RequestMapping(value="/add", method=RequestMethod.GET)
//	public ModelAndView add(){
//		return null;
//	}
//	
//	@RequestMapping(value="", method=RequestMethod.GET)
//	public ModelAndView index(){
//		return new ModelAndView("redirect:/user/list", "users", getBaseModel().getModel().getUsers());
//	}
//	
//	@RequestMapping(value="/create", method=RequestMethod.POST)
//	public ModelAndView create(@ModelAttribute User user){
//
//		boolean available = (baseModel.getModel().loadUserByUsername(user.getUserName()) == null);
//		
//		if(available){
//			if(CollectionUtils.isEmpty(user.getRoles())){
//				user.getRoles().add(ROLE_USER);
//			}
//			
//			baseModel.getPrevayler().execute(new CreateUser(user));
//		}
//		return new ModelAndView("redirect:/user/list", "users", getBaseModel().getModel().getUsers());
//	}
//	
//	@RequestMapping(value="/update", method=RequestMethod.POST)
//	public ModelAndView update(@ModelAttribute User user){
//		
//		/*
//		 * TODO update user
//		 */
//		return new ModelAndView("redirect:/user/list", "users", getBaseModel().getModel().getUsers());
//	}
	


//		//	@RequestMapping(value="/toto", method=RequestMethod.GET)
//		List<User> users = new ArrayList<User>();
//		for(int i = 0; i<10000; i++){
//			User user = new User();
//			user.setUserName("user"+i);
//			user.setPassword("user"+i);
//			users.add(user);
//		}
//		
//		for (User u : users){
//			for(int i = 0; i<6; i++){
//				Post post = new Post();
//				post.setTitle("title post "+ u.getUserName() + i);
//				post.setBody("title body "+ u.getUserName() + i);
//				
//				u.addPost(post);
//				getBaseModel().getPrevayler().execute(new CreateUser(u));
//			}
//		}
//		
//		return null;
//	}


}
	