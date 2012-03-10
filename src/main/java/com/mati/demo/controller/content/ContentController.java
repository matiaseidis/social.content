package com.mati.demo.controller.content;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.prevayler.Transaction;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.controller.BaseController;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.user.User;

public abstract class ContentController<T extends Content> extends BaseController<T> {

	protected abstract T retrieveContent(User user, int contentHash);
	protected abstract boolean isValidContent(T content, Map<String, Object> errors);
	protected abstract void transferMetaData(T oldContent, T updatedContent);
	protected abstract void updateContent(T oldContent, T updatedContent);
	protected abstract Transaction updateTransaction(T initial, T updated);
	protected abstract Transaction updateTransaction(T updated);
	protected abstract Transaction addTransaction(T content);

	protected Map<String, Object> isValidContent(T content){
		boolean withErrors = false;
		Map<String, Object> errors = new HashMap<String, Object>();
		
		String title = content.getTitle();
		if(GenericValidator.isBlankOrNull(title) || !GenericValidator.maxLength(title, 64)){
			errors.put("title", "el titulo es obligatorio");
			withErrors = true;
		}
		
		if(!isValidContent(content, errors)){
			withErrors = true;
		}
		
		if(withErrors){
			errors.put(getEntityName(), content);
		}
		return errors;
	}
	
	protected void processBeforeShow(T content) {}
	protected boolean processContentBeforeSave(T content, HttpServletRequest request) {return true;}

//	@RequestMapping(value=ADD, method=RequestMethod.GET)
//	public ModelAndView add(HttpSession session){
//		ModelAndView m = retrieveErrorsFromSession(session);
//		
//		if(m == null){
//			m = new ModelAndView();
//		}
//		String base = (StringUtils.isEmpty(basePath)) ? StringUtils.EMPTY : basePath;
//		m.setViewName(base + File.separator + getEntityName() + File.separator + ADD);
//		m.addObject(ACTION, CREATE);
//		m.addObject(getEntityName(), new Video());
//		return  m;
//	}
	
	@RequestMapping(value=CREATE, method=RequestMethod.POST)
	public ModelAndView save(@ModelAttribute T content, HttpServletRequest request){
	
		if(sendErrorsToSession(request.getSession(), isValidContent(content))){
			return new ModelAndView("redirect:"+ADD);
		}
		
		if(!processContentBeforeSave(content, request)){
			return new ModelAndView("redirect:"+ADD);
		}
		
		content.setId(content.getTitle().hashCode());
		getBaseModel().getPrevayler().execute(addTransaction(content));
		
		User user = getBaseModel().getModel().getLoggedInUser();
		return new ModelAndView("redirect:"+LIST, getEntityPluralName(), listEntities());
	}
	 
	@RequestMapping(value=UPDATE+"/{nodeId}", method=RequestMethod.POST)
	public ModelAndView update(@ModelAttribute T updatedContent, @PathVariable int oldNodeId, HttpSession session){
		/*
		 * TODO 
		 * hay que pasarle el hash a la vista, y comparar si el hash del updated es igual al anterior. 
		 * Si no son iguales, hay que pasar la metadata del viejo (fecha de creacion, etc) al nuevo, persistir el nuevo y borrar el viejo
		 * Por ahora el hash se saca del titulo
		 */

		if(this.sendErrorsToSession(session, isValidContent(updatedContent))){
			return new ModelAndView("redirect:"+EDIT);
		}
		
		T initialContent = retrieveContent(getBaseModel().getModel().getLoggedInUser(), oldNodeId);
		
		if(updatedContent.getTitle().hashCode() != oldNodeId){
			/*
			 * el titulo se modifico, asi que tengo que reemplazar al COntent inicial con el nuevo
			 */
			transferMetaData(initialContent, updatedContent);
			getBaseModel().getPrevayler().execute(updateTransaction(updatedContent));
			
		} else{
			/*
			 * el titulo no se modifico, por lo que solo aplico los cambios al Content original
			 */
			updateContent(initialContent, updatedContent);
			getBaseModel().getPrevayler().execute(updateTransaction(initialContent, updatedContent));
		}
		
		ModelAndView m = new ModelAndView();
		
		String base = (StringUtils.isEmpty(basePath)) ? StringUtils.EMPTY : basePath;
		m.setViewName("redirect:/" + base + File.separator + getEntityName() + File.separator + LIST);
		
		m.addObject(getEntityPluralName(), listEntities());
		
		return m;
	}
	
	

	@RequestMapping(value="show/{title}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable String title){
		T content = retrieveContent(getBaseModel().getModel().getLoggedInUser(), title.hashCode());
		
		if(content == null){
			/*
			 * TODO handle
			 */
		}
		processBeforeShow(content);
		
		return new ModelAndView("content/"+getEntityName()+"/"+SHOW, getEntityName(), content);
	}
	
}
