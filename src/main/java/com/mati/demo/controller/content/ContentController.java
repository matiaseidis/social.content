package com.mati.demo.controller.content;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.controller.BaseController;
import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.model.validator.content.ContentValidator;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.CreateContent;
import com.mati.demo.prevalence.transaction.content.DeleteContent;
import com.mati.demo.prevalence.transaction.content.UpdateContent;

public abstract class ContentController<T extends Content> extends BaseController<T> {

	String packageName = "com.mati.demo.model.content.type";
	
	@Autowired @Setter @Getter private BaseModel baseModel;

	@Getter @Setter	protected String serverBasePath;
	@Getter @Setter	protected String fileSystemBasePath;
	
	@RequestMapping(value="edit/{nodeId}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable int nodeId, HttpSession session){

		ModelAndView m = retrieveErrorsFromSession(session);

		T content = (T)getBaseModel().getModel().loadContentById(nodeId);

		if(content == null){
			session.setAttribute(MESSAGE, "No existe el contenido de ID " + nodeId);
			m.setViewName("redirect:/");
			return m;
		} 
		
		String base = (StringUtils.isEmpty(basePath)) ? StringUtils.EMPTY : basePath;
		
		m.setViewName(base + File.separator + getEntityName() + File.separator + ADD_EDIT);
		
		m.addObject("content", content);
		m.addObject("contentType", getEntityName());
		m.addObject(ACTION, "../"+UPDATE/*+"/"+nodeId*/);
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

	/*
	 * llamo metadata a las properties que no estan en el form (post date, etc)
	 */
//	protected void transferMetaData(T oldContent, T updatedContent){
//
//		BeanUtils.copyProperties(updatedContent, oldContent, getEntityClass());
//		updatedContent.setId(updatedContent.hashCode());
//	}
	
	/*
	 * pasaje de las properties de un objeto al otro, pero la metadata no
	 */
	protected void updateContent(T oldContent, T updatedContent){
		
		updatedContent.setAuthor(getBaseModel().getModel().getLoggedInUser());
		
//		oldContent.setTitle(updatedContent.getTitle());
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
			validator.addError("plainTags", plainTags);
			validator.addError("plainTagsList", tags(plainTags));

			request.getSession().setAttribute("errors", validator.getErrors());
			return new ModelAndView("redirect:"+ADD);
		}
		return new ModelAndView("redirect:"+LIST);
	}

	private List<Tag> tags(String plainTags) {
		List<Tag> result = new ArrayList<Tag>();
		for(String tag : plainTags.split(",")){
			if(StringUtils.isNotEmpty(tag)){
				result.add(new Tag(tag));
			}
		}
		return result;
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

	@RequestMapping(value=DELETE+"/{nodeId}", method=RequestMethod.POST)
	public ModelAndView delete(@PathVariable int nodeId){

		User user = getBaseModel().getModel().getLoggedInUser();
		
		if(user.getContent(nodeId) != null){
			getBaseModel().getPrevayler().execute(new DeleteContent(nodeId, user.getUserName()));
		}
		
		return new ModelAndView("redirect:../"+LIST, getEntityPluralName(), list(getEntityClass()));
	}

	@RequestMapping(value=UPDATE, method=RequestMethod.POST)
	public ModelAndView update(@ModelAttribute T updatedContent, @RequestParam String plainTags, HttpSession session){

		final int updatedContentId = updatedContent.getId();
		
		T initialContent = (T)getBaseModel().getModel().getLoggedInUser().getContent(updatedContentId);

		updateContent(initialContent, updatedContent);

		ContentValidator<T> validator = getValidator(updatedContent, getBaseModel().getModel());

		if(validator.validate()){
			
//			updateContent(initialContent, updatedContent);
		
			/*
			 * TODO FIXME
			 * de algun modo, en este momento tengo que saber 
			 * cuales son las etiquetas que tenia el content y ahora no tiene mas (que se borraron en la edicion)
			 * 
			 * si las etiquetas borradas no estan referenciando a otro content u algun user, deberian ser borradas del tag repository
			 */
			
			getBaseModel().getPrevayler().execute(new UpdateContent(updatedContent, baseModel.getModel().getLoggedInUser().getUserName()));
		}else{
			session.setAttribute("errors", validator.getErrors());
			
			return new ModelAndView("redirect:edit/"+updatedContentId);
		}
		
		ModelAndView m = new ModelAndView();

		String base = (StringUtils.isEmpty(basePath)) ? StringUtils.EMPTY : basePath;
		m.setViewName("redirect:/" + base + File.separator + getEntityName() + File.separator + LIST);

		m.addObject(getEntityPluralName(), list(getEntityClass()));

		return m;
	}

	@RequestMapping(value="show/{id}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable int id, ModelAndView m, HttpSession session){
		T content = (T)getBaseModel().getModel().loadContentById(id);

		if(content == null){
			session.setAttribute(MESSAGE, "No existe el contenido de ID " + id);
			m.setViewName("redirect:/");
			return m;
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
