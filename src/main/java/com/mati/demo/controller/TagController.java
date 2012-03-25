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
		m.addObject("tag", tag);
		
		List<Content> taggedContent = baseModel.getModel().getTaggedContent(tag);
		m.addObject("taggedContent", taggedContent);
		
		List<User> taggedUsers = baseModel.getModel().getTaggedUsers(tag);
		m.addObject("taggedUsers", taggedUsers);
		
		List<User> followedBy = baseModel.getModel().getFollowedBy(tag);
		m.addObject("tagFollowedBy", followedBy);
		
		
		
		m.setViewName("tag/show");
		return m;
	}
	
//		@RequestMapping(value="list", method=RequestMethod.GET)
//		public ModelAndView list(){
//			ModelAndView mav = new ModelAndView();
//			mav.addObject("tags", baseModel.getModel().getLoggedInUser().getTags());
//			return mav;
//		}
//		
//		@RequestMapping(value="add/{id}", method=RequestMethod.POST)
//		public ModelAndView add(@ModelAttribute Tag tag, @PathVariable int id, ModelAndView m){
//			Content c = baseModel.getModel().loadContentById(id);
//			if(c == null || tag == null || StringUtils.isEmpty(tag.getTagName())){
//				//TODO handle
//			} else{
//				baseModel.getPrevayler().execute(new AddTag(tag, id));
//				//	TODO log
//			}
//			
//			m.setViewName("redirect:/content/"+c.getContentType()+"/show/"+id);
//			return m;
//		}
		
//		@RequestMapping(value="remove/{id}", method=RequestMethod.POST)
//		public ModelAndView remove(@ModelAttribute Tag tag, @PathVariable int id, ModelAndView m){
//			Content c = baseModel.getModel().loadContentById(id);
//			if(c == null || tag == null){
//				//TODO handle
//			}
//			baseModel.getPrevayler().execute(new RemoveTag(tag, id));
//			//TODO log
//			
//			m.setViewName("redirect:/content/"+c.getContentType()+"/show/"+id);
//			return m;
//		}
		
}