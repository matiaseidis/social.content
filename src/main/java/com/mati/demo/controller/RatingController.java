package com.mati.demo.controller;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.Rate;
import com.mati.demo.prevalence.transaction.content.Unrate;

@Controller
@RequestMapping("ajax")
public class RatingController {

	@Autowired @Setter @Getter private BaseModel baseModel;
	
	@RequestMapping(value="rate/{contentId}", method=RequestMethod.POST)
	public @ResponseBody String rate(@RequestParam float rating, @PathVariable int contentId){
		/*
		 * TODO cuando viene 0.0 impĺementar removeRating
		 * 
		 * y
		 *
		 * para mostrar el promedio ni bien se hace el voto, 
		 * tengo que devolver rating-box.jsp en vez de StringUtils.EMPTY
		 */
		User user = getBaseModel().getModel().getLoggedInUser();
		if(getBaseModel().getModel().loadContentById(contentId) != null){
			if(rating > 0){
				getBaseModel().getPrevayler().execute(new Rate(contentId, user.getUserName(), rating));
			} else {
				getBaseModel().getPrevayler().execute(new Unrate(contentId, user.getUserName()));
			}
		} else{
			//TODO handle
		}
		
		
		
		return StringUtils.EMPTY;
	}
	
}
