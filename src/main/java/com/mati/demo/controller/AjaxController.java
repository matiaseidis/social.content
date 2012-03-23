package com.mati.demo.controller;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.Comment;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.comment.CreateComment;

@Controller
@RequestMapping("ajax")
public class AjaxController {
	
	@Autowired @Setter @Getter private BaseModel baseModel;
	
	int fixedTotal = 3; // max items to show in paginated view 

	
	@RequestMapping(value="comment/{id}", method=RequestMethod.POST)
	public ModelAndView add(@ModelAttribute Comment comment, @PathVariable int id, ModelAndView m){
		Content content = baseModel.getModel().loadContentById(id);
		
		if(content == null){
			//TODO handle
		}
		
		baseModel.getPrevayler().execute(new CreateComment(comment, id, baseModel.getModel().getLoggedInUser().getUserName()));
		m.addObject("content", content);
		m.setViewName("content/comments-box");
		return m;
	}
	
	@RequestMapping(value="followedContent/{page}/{total}", method=RequestMethod.GET)
	public ModelAndView followedContent(@PathVariable int page, @PathVariable int total, ModelAndView m){
		/*
		 * TODO parameterize this
		 * limit max value returned
		 */
		total = (total <= fixedTotal) ? total : fixedTotal;
		
		
		int totalFollowedContent = getBaseModel().getModel().getLoggedInUser().getFollowedContent().size();
		int prevPage = (page > 0) ? page -1 : -1;
		int nextPage = ((page * total) + total) < totalFollowedContent ? page + 1 : 0;
		List<Content> result = getBaseModel().getModel().getLoggedInUser().getFollowedContent(total, page);
		m.addObject("title", "Contenido de gente que seguis");
		m.addObject("paginatedContent", result);
		m.addObject("prevPage", prevPage);
		m.addObject("nextPage", nextPage);
		m.addObject("updatedTagId", "followedContent");
		m.addObject("page", page);
		m.addObject("total", total);
		m.setViewName("paginated/content");
		return m;
	}
	
	@RequestMapping(value="followedUsers/{page}/{total}", method=RequestMethod.GET)
	public ModelAndView followedUsers(@PathVariable int page, @PathVariable int total, ModelAndView m){
		/*
		 * TODO parameterize this
		 * limit max value returned
		 */
		total = (total <= fixedTotal) ? total : fixedTotal;
		
		
		int totalFollowedUsers = getBaseModel().getModel().getLoggedInUser().getFollowedUsers().size();
		int prevPage = (page > 0) ? page -1 : -1;
		int nextPage = ((page * total) + total) < totalFollowedUsers ? page + 1 : 0;
		List<User> result = getBaseModel().getModel().getLoggedInUser().getFollowedUsers(total, page);
		m.addObject("title", "Usuarios que seguis");
		m.addObject("paginatedUsers", result);
		m.addObject("prevPage", prevPage);
		m.addObject("nextPage", nextPage);
		m.addObject("updatedTagId", "followedUsers");
		m.addObject("page", page);
		m.addObject("total", total);
		m.setViewName("paginated/users");
		return m;
	}
	
	@RequestMapping(value="followedBy/{page}/{total}", method=RequestMethod.GET)
	public ModelAndView followedBy(@PathVariable int page, @PathVariable int total, ModelAndView m){
		/*
		 * TODO parameterize this
		 * limit max value returned
		 */
		total = (total <= fixedTotal) ? total : fixedTotal;
		
		
		int totalFollowedBy = getBaseModel().getModel().getLoggedInUser().getFollowedBy().size();
		int prevPage = (page > 0) ? page -1 : -1;
		int nextPage = ((page * total) + total) < totalFollowedBy ? page + 1 : 0;
		List<User> result = getBaseModel().getModel().getLoggedInUser().getFollowedBy(total, page);
		m.addObject("title", "Usuarios que te siguen");
		m.addObject("paginatedUsers", result);
		m.addObject("prevPage", prevPage);
		m.addObject("nextPage", nextPage);
		m.addObject("updatedTagId", "followedBy");
		m.addObject("page", page);
		m.addObject("total", total);
		m.setViewName("paginated/users");
		return m;
	}

}
