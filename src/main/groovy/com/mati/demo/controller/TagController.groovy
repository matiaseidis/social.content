package com.mati.demo.controller;

import org.apache.commons.lang.StringUtils
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.Content
import com.mati.demo.model.tag.Tag;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.AddTag
import com.mati.demo.prevalence.transaction.content.RemoveTag

@Controller
@RequestMapping("tag/")
public class TagController {

	
	def baseModel
		
		@RequestMapping(value="list", method=RequestMethod.GET)
		public ModelAndView list(){
			ModelAndView mav = new ModelAndView();
			mav.addObject("tags", baseModel.getModel().getLoggedInUser().getTags());
			return mav;
		}
		
		@RequestMapping(value="add/{id}", method=RequestMethod.POST)
		public ModelAndView add(@ModelAttribute Tag tag, @PathVariable int id, ModelAndView m){
			Content c = baseModel.getModel().loadContentById(id);
			if(c == null || tag == null || StringUtils.isEmpty(tag.getTagName())){
				//TODO handle
			} else{
				baseModel.getPrevayler().execute(new AddTag(tag, id));
				//	TODO log
			}
			
			m.setViewName("redirect:/content/"+c.getContentType()+"/show/"+id);
			return m;
		}
		
		@RequestMapping(value="remove/{id}", method=RequestMethod.POST)
		public ModelAndView remove(@ModelAttribute Tag tag, @PathVariable int id, ModelAndView m){
			Content c = baseModel.getModel().loadContentById(id);
			if(c == null || tag == null){
				//TODO handle
			}
			baseModel.getPrevayler().execute(new RemoveTag(tag, id));
			//TODO log
			
			m.setViewName("redirect:/content/"+c.getContentType()+"/show/"+id);
			return m;
		}
		
}