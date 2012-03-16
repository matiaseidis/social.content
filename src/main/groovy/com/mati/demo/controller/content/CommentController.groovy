package com.mati.demo.controller.content;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.Comment;
import com.mati.demo.model.content.Content
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.comment.CreateComment
	
@Controller
@RequestMapping("comment/")
public class CommentController {

	def baseModel
	
	@RequestMapping(value="add/{contentType}/{id}", method=RequestMethod.POST)
	public ModelAndView add(Comment comment, @PathVariable int id, @PathVariable String contentType, ModelAndView m){
		Content content = baseModel.getModel().loadContentById(id);
		
		if(content == null || contentType == null){
			//TODO handle
		}
		
		baseModel.getPrevayler().execute(new CreateComment(comment, id));
		
		m.setViewName("redirect:/content/"+contentType+"/show/"+id);
		return m;
	}
			
}
