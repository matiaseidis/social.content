package com.mati.demo.controller;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.relationships.Relation;
import com.mati.demo.prevalence.BaseModel;


@Controller
@RequestMapping("ajax/relation")
public class RelationsController {
	
	@Autowired @Setter @Getter private BaseModel baseModel;

	@RequestMapping(value="add", method=RequestMethod.POST)
	public ModelAndView addRelation(@ModelAttribute Relation relation, @PathVariable int id, ModelAndView m){
	
		Content c = getBaseModel().getModel().loadContentById(id);
		if(c == null){
			//TODO handle
		}
		
		c.addRelation(relation);
		
		return m;
	}
		
	
}
