package com.mati.demo.controller.content;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.controller.BaseController;
import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Comment;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.model.validator.content.ContentValidator;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.CreateContent;
import com.mati.demo.prevalence.transaction.content.UpdateContent;

public abstract class ContentController<T extends Content> extends BaseController<T> {

	String packageName = "com.mati.demo.model.content.type";
	
	
	@RequestMapping(value="edit/{nodeId}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable int nodeId, ModelAndView m){

		String base = (StringUtils.isEmpty(basePath)) ? StringUtils.EMPTY : basePath;

		m.setViewName(base + File.separator + getEntityName() + File.separator + ADD_EDIT);

		T content = (T)getBaseModel().getModel().loadContentById(nodeId);

		if(content != null){
			m.addObject("content", content);
			m.addObject("contentType", getEntityName());
			m.addObject(ACTION, "../"+UPDATE+"/"+nodeId);
		}
		return  m;


	}

	protected List<Content> list(String username, Class clazz) {

		User u = getBaseModel().getModel().loadUserByUsername(username);
		if(u != null){
			return u.getContent();
		}
		return Collections.EMPTY_LIST;
	}

	protected List<Content> list(Class clazz) {
		return getBaseModel().getModel().getLoggedInUser().getContent();
	}

	protected abstract BaseModel getBaseModel();
	//	protected abstract boolean isValidContent(T content, Map<String, Object> errors);
	
	/*
	 * llamo metadata a las properties que no estan en el form (post date, etc)
	 */
	protected void transferMetaData(T oldContent, T updatedContent){

		BeanUtils.copyProperties(updatedContent, oldContent, getEntityClass());
		updatedContent.setId(updatedContent.hashCode());
	}
	
	/*
	 * pasaje de las properties de un objeto al otro, pero la metadata no
	 */
	protected void updateContent(T oldContent, T updatedContent){
		
		oldContent.setTitle(updatedContent.getTitle());
		
	}

	protected void processBeforeShow(T content) {}
	protected boolean processContentBeforeSave(T content, HttpServletRequest request) {return true;}

	@RequestMapping(value=CREATE, method=RequestMethod.POST)
	public ModelAndView save(@ModelAttribute T content, @RequestParam String plainTags, HttpServletRequest request){

		content.setAuthor(getBaseModel().getModel().getLoggedInUser());
		content.setId(content.hashCode());
		ContentValidator<T> validator = getValidator(content, getBaseModel().getModel());
		String userName = getBaseModel().getModel().getLoggedInUser().getUserName();
		CreateContent<T> create = new CreateContent<T>(content, userName, plainTags);
		if(validator.validate()){
			getBaseModel().getPrevayler().execute(create);
		} else {
			request.getSession().setAttribute("errors", validator.getErrors());
			return new ModelAndView("redirect:"+"/"+ADD);
		}
		return new ModelAndView("redirect:"+LIST);
	}

	protected abstract ContentValidator<T> getValidator(T content, Model model);

	protected Class getEntityClass(){
		Class c = null;
		try{
			String entityClassName = getEntityName().substring(0, 1).toUpperCase() + getEntityName().substring(1);
			
			c = Class.forName(packageName +"."+ entityClassName);
		}catch(ClassNotFoundException e){
			//TODO log
			e.printStackTrace();
		}
		return c;
	}

	@RequestMapping(value=LIST, method=RequestMethod.GET)
	public ModelAndView list(){

		ModelAndView m = new ModelAndView();

		String base = (StringUtils.isEmpty(basePath)) ? StringUtils.EMPTY : basePath + File.separator;
		m.setViewName(base + getEntityName() + File.separator + LIST);

		m.addObject(getEntityPluralName(), listContent(getBaseModel().getModel().getLoggedInUser()));

		return m;
	}

	protected abstract List<T> listContent(User user);

//	@RequestMapping(value=DELETE+"/{nodeId}", method=RequestMethod.POST)
//	public ModelAndView delete(@PathVariable int nodeId){
//
//		User user = getBaseModel().loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//
//		getBaseModel().getPrevayler().execute(new DeleteContent(nodeId));
//
//		return new ModelAndView("redirect:"+LIST, getEntityPluralName(), list(getEntityClass()));
//	}

	@RequestMapping(value=UPDATE+"/{nodeId}", method=RequestMethod.POST)
	public ModelAndView update(@ModelAttribute T updatedContent, @PathVariable int nodeId, @RequestParam String plainTags, HttpSession session){

		T initialContent = (T)getBaseModel().getModel().getLoggedInUser().getContent(nodeId);

		User loggedInUser = getBaseModel().getModel().getLoggedInUser();

		ContentValidator<T> validator = getValidator(updatedContent, getBaseModel().getModel());
		
		if(validator.exists()){
			if(updatedContent.getTitle().equals(initialContent.getTitle())){
				/*
				 * 
				 */
			}
		}
		
		
		if(validator.validate()){
			
			updateContent(initialContent, updatedContent);
		
//		updatedContent.setId(nodeId);
			getBaseModel().getPrevayler().execute(new UpdateContent(initialContent, loggedInUser.getUserName()));
		}else{
			session.setAttribute("errors", validator.getErrors());
			return new ModelAndView("redirect:"+"/"+ADD);
		}
		
		ModelAndView m = new ModelAndView();

		String base = (StringUtils.isEmpty(basePath)) ? StringUtils.EMPTY : basePath;
		m.setViewName("redirect:/" + base + File.separator + getEntityName() + File.separator + LIST);

		m.addObject(getEntityPluralName(), list(getEntityClass()));

		return m;
	}

	@RequestMapping(value="show/{id}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable int id, ModelAndView m){
		T content = (T)getBaseModel().getModel().loadContentById(id);

		if(content == null){
			/*
			 * TODO handle
			 */
		}
		processBeforeShow(content);
		m.addObject("model", getBaseModel().getModel());
		m.addObject("content", content);
		m.addObject("contentType", getEntityName());
		m.addObject("tag", new Tag());
		m.setViewName("content/"+getEntityName()+"/"+SHOW);

		return m;
	}

}
