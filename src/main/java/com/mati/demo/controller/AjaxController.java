package com.mati.demo.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.Comment;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.content.type.Event;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.comment.CreateComment;
import com.mati.demo.prevalence.transaction.user.StartFollowingUser;
import com.mati.demo.prevalence.transaction.user.StopFollowingUser;

@Controller
@RequestMapping("ajax")
public class AjaxController {
	
	@Autowired @Setter @Getter private BaseModel baseModel;
	
	int shortFixedTotal = 3; // max items to show in paginated view
	int longFixedTotal = 20; // max items to show in paginated view
	int imgSize = 27;

	
	@RequestMapping(value="comment/{id}", method=RequestMethod.POST)
	public ModelAndView add(@ModelAttribute Comment comment, @PathVariable int id, ModelAndView m){
		Content content = baseModel.getModel().loadContentById(id);
		
		if(content == null){
			//TODO handle
		}
		
		baseModel.getPrevayler().execute(new CreateComment(comment, id, baseModel.getModel().getLoggedInUser().getUserName()));
		m.addObject("content", content);
		m.addObject("imgSize", imgSize);
		m.setViewName("content/comments-box");
		return m;
	}
	
	
	@RequestMapping(value="followedEvents/{page}/{total}", method=RequestMethod.GET)
	public ModelAndView followedEvents(@PathVariable Integer page, @PathVariable Integer total, ModelAndView m){
		/*
		 * TODO parameterize this
		 * limit max value returned
		 */
		total = (total <= shortFixedTotal) ? total : shortFixedTotal;
		
		int totalFollowedEvents = getBaseModel().getModel().getLoggedInUser().getFollowedEvents().size();
		int prevPage = (page > 0) ? page -1 : -1;
		int nextPage = ((page * total) + total) < totalFollowedEvents ? page + 1 : 0;
		List<Event> result = getBaseModel().getModel().getLoggedInUser().getFollowedEvents(total, page);
		
		setPagination(m, "Proximos eventos de gente que seguis", result, prevPage, nextPage, "followedEvents", page, total, totalFollowedEvents, imgSize);

		m.setViewName("paginated/events");
		return m;
	}
	
	
	@RequestMapping(value="followedContent/{page}/{total}", method=RequestMethod.GET)
	public ModelAndView followedContent(@PathVariable int page, @PathVariable int total, ModelAndView m){
		/*
		 * TODO parameterize this
		 * limit max value returned
		 */
		total = (total <= shortFixedTotal) ? total : shortFixedTotal;
		
		
		int totalFollowedContent = getBaseModel().getModel().getLoggedInUser().getFollowedContent().size();
		int prevPage = (page > 0) ? page -1 : -1;
		int nextPage = ((page * total) + total) < totalFollowedContent ? page + 1 : 0;
		List<Content> result = getBaseModel().getModel().getLoggedInUser().getFollowedContent(total, page);
		
		setPagination(m, "Contenido de gente que seguis", result, prevPage, nextPage, "followedContent", page, total, totalFollowedContent, imgSize);

		m.setViewName("paginated/content");
		return m;
	}
	
	@RequestMapping(value="allContent/{page}/{total}", method=RequestMethod.GET)
	public ModelAndView allContent(@PathVariable Integer page, @PathVariable Integer total, ModelAndView m){

		total = (total <= longFixedTotal) ? total : longFixedTotal;
		
		
		int totalContent = getBaseModel().getModel().getContent().size();
		int prevPage = (page > 0) ? page -1 : -1;
		int nextPage = ((page * total) + total) < totalContent ? page + 1 : 0;
		List<Content> result = getBaseModel().getModel().getContent(total, page);
		
		setPagination(m, "Todo el contenido", result, prevPage, nextPage, "allContent", page, total, totalContent, imgSize);

		m.setViewName("paginated/content");
		return m;
	}
	
	@RequestMapping(value="followedUsers/{page}/{total}", method=RequestMethod.GET)
	public ModelAndView followedUsers(@PathVariable Integer page, @PathVariable Integer total, ModelAndView m){
		/*
		 * TODO parameterize this
		 * limit max value returned
		 */
		total = (total <= shortFixedTotal) ? total : shortFixedTotal;
		
		
		int totalFollowedUsers = getBaseModel().getModel().getLoggedInUser().getFollowedUsers().size();
		int prevPage = (page > 0) ? page -1 : -1;
		int nextPage = ((page * total) + total) < totalFollowedUsers ? page + 1 : 0;
		List<User> result = getBaseModel().getModel().getLoggedInUser().getFollowedUsers(total, page);
		
		setPagination(m, "Usuarios que seguis", result, prevPage, nextPage, "followedUsers", page, total, totalFollowedUsers, imgSize);
		
		m.setViewName("paginated/users");
		return m;
	}
	
	@RequestMapping(value="followedBy/{page}/{total}", method=RequestMethod.GET)
	public ModelAndView followedBy(@PathVariable Integer page, @PathVariable Integer total, ModelAndView m){
		/*
		 * TODO parameterize this
		 * limit max value returned
		 */
		total = (total <= shortFixedTotal) ? total : shortFixedTotal;
		
		
		int totalFollowedBy = getBaseModel().getModel().getLoggedInUser().getFollowedBy().size();
		int prevPage = (page > 0) ? page -1 : -1;
		int nextPage = ((page * total) + total) < totalFollowedBy ? page + 1 : 0;
		List<User> result = getBaseModel().getModel().getLoggedInUser().getFollowedBy(total, page);
		
		setPagination(m, "Usuarios que te siguen", result, prevPage, nextPage, "followedBy", page, total, totalFollowedBy, imgSize);
		
		m.setViewName("paginated/users");
		return m;
	}
	
	@RequestMapping(value="follow/user/{userName}", method=RequestMethod.POST)
	public ModelAndView followUSer(@PathVariable String userName, @RequestParam String refresh, @RequestParam int page, @RequestParam int total, ModelAndView m){
		
		User user = getBaseModel().getModel().getLoggedInUser();
		
		if(!user.isFollowing(getBaseModel().getModel().loadUserByUsername(userName))){
			getBaseModel().getPrevayler().execute(new StartFollowingUser(userName, user.getUserName()));
		}
		return processForward(refresh, page, total, m);
	}
	
	@RequestMapping(value="unfollow/user/{userName}", method=RequestMethod.POST)
	public ModelAndView unfollowUSer(@PathVariable String userName, @RequestParam String refresh, @RequestParam int page, @RequestParam int total, ModelAndView m){
		
		User user = getBaseModel().getModel().getLoggedInUser();
		
		if(user.isFollowing(getBaseModel().getModel().loadUserByUsername(userName))){
			getBaseModel().getPrevayler().execute(new StopFollowingUser(userName, user.getUserName()));
		}
		
		return processForward(refresh, page, total, m);
	}
	
	private ModelAndView processForward(String methodName, int page, int total, ModelAndView m){
		ModelAndView result = new ModelAndView(); 
		Method method = null;
		Object[] o = new Object[]{page, total, m};
		try {
			  method = this.getClass().getMethod(methodName, Integer.class, Integer.class, ModelAndView.class);
			} catch (SecurityException e) {
			  // ...
			} catch (NoSuchMethodException e) {
			  // ...
			}
			try {
				result = (ModelAndView)method.invoke(this, o);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return result;
	}
	
	
	private ModelAndView setPagination(ModelAndView m, String title, List list, int prevPage, int nextPage, String updatedTagId, int page, int total, int fullTotal, int imgSize){
		m.addObject("title", title);
		m.addObject("followedList", list);
		m.addObject("prevPage", prevPage);
		m.addObject("nextPage", nextPage);
		m.addObject("updatedTagId", updatedTagId);
		m.addObject("page", page);
		m.addObject("total", total);
		m.addObject("fullTotal", fullTotal);
		m.addObject("imgSize", imgSize);
		return m;
	}
}
