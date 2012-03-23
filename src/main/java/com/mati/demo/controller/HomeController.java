package com.mati.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.authentication.AuthenticationProviderImpl;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.content.type.Audio;
import com.mati.demo.model.content.type.Event;
import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.CreateContent;
import com.mati.demo.prevalence.transaction.user.CreateUser;
import com.mati.demo.prevalence.transaction.user.StartFollowingUser;

@Controller
public class HomeController {

	public Logger logger = Logger.getLogger(getClass());

	/*
	 * only for mock content and users
	 */
	@Resource(name="authenticationProvider") private AuthenticationProviderImpl authenticationProvider;

	@Autowired private BaseModel baseModel;	

	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView m, HttpSession session){

		User user = baseModel.getModel().getLoggedInUser();
		if(user != null){

			List<Video> followedVideos = user.getFollowedVideos();
			m.addObject("followedVideos", followedVideos);

			List<Post> followedPosts = user.getFollowedPosts();
			m.addObject("followedContent", followedPosts);
		} else if(baseModel.loadUserByUsername("admin") == null){

			mockContent(session);
		}		


		List<Video> videos = new ArrayList<Video>();
		for(User u : baseModel.getModel().getUsers()){
			videos.addAll(u.getVideos());
		}

		for(Video video : videos){
			video.setVideoRef(video.getUrl());
		}

		m.addObject("lastVideos", videos);
		m.setViewName("home");
		return m;
	} 	

	private void mockContent(HttpSession session) {


		final String ROLE_USER = "ROLE_USER";
		final String ROLE_ADMIN = "ROLE_ADMIN";
		final String ADMIN_NAME = "admin";
		final String ADMIN_PASS = "admin";

		/*
		 * create admin user
		 */
		User admin = new User();
		admin.setUserName(ADMIN_NAME);
		admin.setPassword(ADMIN_PASS);
		//			admin.getRoles().add(ROLE_USER);
		admin.getRoles().add(ROLE_ADMIN);

		baseModel.getPrevayler().execute(new CreateUser(admin));
		//			userAccountController.register(admin, null, null);

		String userName = "user-";
		for(int i = 0; i < 5; i++){
			User u = new User();
			u.setUserName(userName+i);
			u.setPassword(userName+i);
			//				u.getRoles().add(ROLE_USER);

			baseModel.getPrevayler().execute(new CreateUser(u));
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(u.getUserName(), u.getPassword());
			try {
				Authentication auth = authenticationProvider.authenticate(token);
				SecurityContextHolder.getContext().setAuthentication(auth);

				baseModel.getPrevayler().execute(new StartFollowingUser(u.getUserName(), admin.getUserName()));

				baseModel.getPrevayler().execute(new CreateContent<Video>(video(i, u), u.getUserName(), null));
				baseModel.getPrevayler().execute(new CreateContent<Audio>(audio(i, u), u.getUserName(), null));
				baseModel.getPrevayler().execute(new CreateContent<Post>(post(i, u), u.getUserName(), null));
				baseModel.getPrevayler().execute(new CreateContent<Event>(event(i, u), u.getUserName(), null));

//				session.invalidate();
				SecurityContextHolder.getContext().setAuthentication(null);
			}catch(AuthenticationException ae){
				//TODO handle this
				
				System.out.println(ae);
			}
		}

	}

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public /*@ResponseBody*/ ModelAndView login(ModelAndView m){
		m.setViewName("/login_stand_alone");
		return m;
	}


	/******** content mocking ******/

	private Video video(int i, User user){
		Video content = new Video();
		content.setTitle("Un video - "+i);
		content.setAuthor(user);
		content.setId(content.hashCode());
		content.setUrl("http://youtu.be/_Ej4H3rSUIw");
		content.addTag(baseModel.getModel().getTagRepository(), tag(i));
		return content;
	}

	private Tag tag(int i) {
		Tag tag = new Tag();
		tag.setTagName("tag mock " + i);
		return tag;
	}

	private Audio audio(int i, User user){
		Audio content = new Audio();
		content.setTitle("Un audio - "+i);
		content.setAuthor(user);
		content.setId(content.hashCode());
		content.addTag(baseModel.getModel().getTagRepository(), tag(i));
		return content;
	}

	private Post post(int i, User user){
		Post content = new Post();
		content.setTitle("Un post - "+i);
		content.setAuthor(user);
		content.setId(content.hashCode());
		content.setBody("body body body body body body body ");
		content.addTag(baseModel.getModel().getTagRepository(), tag(i));
		return content;
	}

	private Event event(int i, User user){
		Event content = new Event();
		content.setTitle("Un event - "+i);
		content.setAuthor(user);
		content.setId(content.hashCode());
		content.setStart(new Date());
		content.addTag(baseModel.getModel().getTagRepository(), tag(i));
		return content;
	}


}
