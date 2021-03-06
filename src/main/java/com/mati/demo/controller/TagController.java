package com.mati.demo.controller;

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
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

@Controller
@RequestMapping("tag")
public class TagController {

	
	@Autowired @Setter @Getter private BaseModel baseModel;

	@RequestMapping(value="show/{tagName}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable String tagName, ModelAndView m){

		Tag tag = baseModel.getModel().loadTagByTagName(tagName);
		m.setViewName("tag/show");
		if(tag == null){
			m.addObject("message", "Esa etiqueta ("+tagName+") no existe");
			return m;
		}
		
		m.addObject("tag", tag);
		
		List<Content> taggedContent = baseModel.getModel().getTaggedContent(tag);
		m.addObject("taggedContent", taggedContent);
		
		List<User> taggedUsers = baseModel.getModel().getTaggedUsers(tag);
		m.addObject("taggedUsers", taggedUsers);
		
		List<User> followedBy = baseModel.getModel().getFollowedBy(tag);
		m.addObject("tagFollowedBy", followedBy);
		
		return m;
	}
}