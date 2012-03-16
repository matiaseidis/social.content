package com.mati.demo.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.validator.ValidationError;
import com.mati.demo.prevalence.BaseModel;

public abstract class BaseController<T> {

	protected abstract BaseModel getBaseModel();
	
	@Getter @Setter private String entityName;
	@Getter @Setter private String entityPluralName;
	@Getter @Setter	protected String basePath;
	protected static final String ADD = "add";
	protected static final String ADD_EDIT = "add-edit";
	protected static final String CREATE = "create";
	protected static final String EDIT = "add-edit";
	protected static final String UPDATE = "update";
	protected static final String LIST = "list";
	protected static final String DELETE = "delete";
	protected static final String SHOW = "show";
	
	
	@Getter protected final String ERRORS = "errors";
	
	@Getter protected static final String ACTION = "action";
	
	;
//	protected abstract Transaction updateTransaction(); //dejar solo para user esta version
//	protected abstract Transaction deleteTransaction(int nodeId); //new DeletePost(nodeId)
	
//	@RequestMapping(value=LIST, method=RequestMethod.GET)
//	public ModelAndView list(){
//		
//		ModelAndView m = new ModelAndView();
//		
//		String base = (StringUtils.isEmpty(basePath)) ? StringUtils.EMPTY : basePath + File.separator;
//		m.setViewName(base + getEntityName() + File.separator + LIST);
//		
//		m.addObject(getEntityPluralName(), list(T));
//		
//		return m;
//	}
	
	
	
	
	
	
	
	
	@RequestMapping(value=ADD, method=RequestMethod.GET)
	public ModelAndView add(HttpSession session){
		ModelAndView m = retrieveErrorsFromSession(session);
		
		if(m == null){
			m = new ModelAndView();
		}
		String base = (StringUtils.isEmpty(basePath)) ? StringUtils.EMPTY : basePath;
		m.setViewName(base + File.separator + getEntityName() + File.separator + ADD_EDIT);
		m.addObject(ACTION, CREATE);
		m.addObject(getEntityName(), createEntity());
		return  m;
	}

	protected T createEntity(){return null;}

	protected ModelAndView retrieveErrorsFromSession(HttpSession session) {
		ModelAndView m = null;
		List<ValidationError> errors = (List<ValidationError>)session.getAttribute(ERRORS);
		
		if(CollectionUtils.isNotEmpty(errors)){
			m = new ModelAndView(); 
			m.addObject(ERRORS, errors);
		}
		
		session.setAttribute(ERRORS, null);
		return m;
	}
	
	protected boolean sendErrorsToSession(HttpSession session, List<ValidationError> errors) {
		
		if(CollectionUtils.isNotEmpty(errors)){
			session.setAttribute(ERRORS, errors);
			return true;
		}
		return false;
	}

	
}
