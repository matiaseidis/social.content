package com.mati.demo.controller;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.controller.dto.AutoCompleteItem;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.relationships.Relation;
import com.mati.demo.model.relationships.RelationType;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.AddRelation;
import com.mati.demo.prevalence.transaction.content.RemoveRelation;


@Controller
@RequestMapping("ajax")
public class RelationsController {
	
	@Autowired @Setter @Getter private BaseModel baseModel;

	@RequestMapping(value="relationsBox", method=RequestMethod.GET)
	public ModelAndView autoBox(ModelAndView m){
		m.setViewName("search/autocomplete");
		return m;
	}
	
	@RequestMapping(value="relations/add", method=RequestMethod.POST)
	public ModelAndView addRelations(@RequestParam int contentId, @RequestParam String relations, ModelAndView m){
		
		Content c = getBaseModel().getModel().loadContentById(contentId);
		
		String[] rel = relations.split("R");
		
		for(String relationString : rel){
			if(StringUtils.isNotEmpty(relationString)){
				String[] elements = relationString.split("_");
				int id = Integer.valueOf(elements[0]);
				int type = Integer.valueOf(elements[1]);
				Content related = getBaseModel().getModel().loadContentById(id);
				if(related != null && related.getId() != c.getId()){
					Relation r = new Relation();
					r.setType(RelationType.get(type));
					r.setRelated(related);
					r.setAuthor(getBaseModel().getModel().getLoggedInUser());
					getBaseModel().getPrevayler().execute(new AddRelation(contentId, r));
				}
			}
		}
		m.addObject("content", c);
		m.setViewName("/content/relations-box");
		return m;
	}
	
	@RequestMapping(value="relation/remove", method=RequestMethod.POST)
	public @ResponseBody String removeRelation(@RequestParam int relatedId, @RequestParam int relationId, ModelAndView m){
		
		Content c = getBaseModel().getModel().loadContentById(relatedId);
		Relation r = null;
		if(c == null){
			// TODO handle
		}
		for(Relation rel : c.getRelations()){
			if(rel.getId() == relationId){
				r = rel;
				break;
			}
		}
		if(r == null){
			// TODO handle
		}
		getBaseModel().getPrevayler().execute(new RemoveRelation(c,r));
		return "La relacion ha sido eliminada";
	}
	
	@RequestMapping(value="relations/list", method=RequestMethod.GET)
	public @ResponseBody List<AutoCompleteItem> listRelationTypes(ModelAndView m){
		
		List<AutoCompleteItem> result = new ArrayList<AutoCompleteItem>();
		for(RelationType rt : RelationType.values()){
			AutoCompleteItem item = new AutoCompleteItem();
			item.setId(rt.getId());
			item.setValue(rt.getDescription());
			result.add(item);
		}
		return result;
	}
}
