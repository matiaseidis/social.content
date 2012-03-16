package com.mati.demo.controller.content;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils
import org.apache.commons.lang.StringUtils
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.controller.BaseController;
import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.tag.Tag
import com.mati.demo.model.user.User
import com.mati.demo.model.validator.content.ContentValidator;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.CreateContent
import com.mati.demo.prevalence.transaction.content.DeleteContent
import com.mati.demo.prevalence.transaction.content.UpdateContent

public abstract class ContentController<T extends Content> extends BaseController<T> {

	String packageName = "com.mati.demo.model.content.type";
	
	
	@RequestMapping(value="edit/{nodeId}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable int nodeId){

		ModelAndView m = new ModelAndView();
		String base = (StringUtils.isEmpty(basePath)) ? StringUtils.EMPTY : basePath;

		m.setViewName(base + File.separator + getEntityName() + File.separator + ADD_EDIT);

		T content = retrieveContent(nodeId);

		if(content != null){
			m.addObject(getEntityName(), content);
			m.addObject(ACTION, "../"+UPDATE+"/"+nodeId);
		}

		return  m;


	}

	protected T retrieveContent(int contentHash){

		Content c = baseModel.getModel().loadContentById(contentHash);

		if(c != null){
			try{

				T t = (T)c;
				return t;

			}catch(Exception e){
				//TODO log in the right way
				System.err.println("unable to cast "+c.getClass());
			}
		}
		return null;
	}

	protected Collection<T> list(String username, Class clazz) {

		User u = baseModel.getModel().loadUserByUsername(username);
		if(u != null){
			return u.getContent(clazz);
		}
		return Collections.EMPTY_SET;

	}

	protected Collection<T> list(Class clazz) {
		println baseModel
		baseModel.getModel().getLoggedInUser().getContent(clazz);
	}



	
	//	protected abstract boolean isValidContent(T content, Map<String, Object> errors);
	protected abstract void transferMetaData(T oldContent, T updatedContent);
	protected abstract void updateContent(T oldContent, T updatedContent);
	//	protected abstract Transaction updateTransaction(T initial, T updated);
	//	protected abstract Transaction updateTransaction(T updated);
	//	protected abstract Transaction addTransaction(T content);

	//	protected Map<String, Object> isValidContent(T content){
	//		boolean withErrors = false;
	//		Map<String, Object> errors = new HashMap<String, Object>();
	//		
	//		String title = content.getTitle();
	//		if(GenericValidator.isBlankOrNull(title) || !GenericValidator.maxLength(title, 64)){
	//			errors.put("title", "el titulo es obligatorio");
	//			withErrors = true;
	//		}
	//		
	//		if(!isValidContent(content, errors)){
	//			withErrors = true;
	//		}
	//		
	//		if(withErrors){
	//			errors.put(getEntityName(), content);
	//		}
	//		return errors;
	//	}

	protected void processBeforeShow(T content) {}
	protected boolean processContentBeforeSave(T content, HttpServletRequest request) {return true;}

//		@RequestMapping(value="add", method=RequestMethod.GET)
//		public ModelAndView add(HttpSession session){
//			ModelAndView m = retrieveErrorsFromSession(session);
//			
//			if(m == null){ 
//				m = new ModelAndView();
//			}
//			String base = (StringUtils.isEmpty(basePath)) ? StringUtils.EMPTY : basePath;
//			m.setViewName(base + File.separator + getEntityName() + File.separator + ADD_EDIT);
//			m.addObject(ACTION, CREATE);
//			m.addObject(getEntityName(), new Video());
//			return  m;
//		}

	@RequestMapping(value="create", method=RequestMethod.POST)
	public ModelAndView save(@ModelAttribute T content, HttpServletRequest request){

		//		if(sendErrorsToSession(request.getSession(), isValidContent(content))){
		//			
		//		}

		//		if(!processContentBeforeSave(content, request)){
		//			return new ModelAndView("redirect:"+ADD);
		//		}

		content.setId(content.getTitle().hashCode());
		def validator = getValidator(content, baseModel.getModel());
		try{
			def create = new CreateContent<T>(content);
			if(validator.validate()){
				baseModel.getPrevayler().execute(create);
			}
		}catch(Exception e){
			if(CollectionUtils.isNotEmpty(validator.getErrors())){
				request.getSession().setAttribute("errors", validator.getErrors());
				return new ModelAndView("redirect:"+ADD);
			}
		}

		return new ModelAndView("redirect:"+LIST);
	}

	protected abstract def getValidator(T content, Model model);

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

	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView list(){

		ModelAndView m = new ModelAndView();

		String base = (StringUtils.isEmpty(basePath)) ? StringUtils.EMPTY : basePath + File.separator;
		m.setViewName(base + getEntityName() + File.separator + LIST);

		m.addObject(getEntityPluralName(), list(getEntityClass()));

		return m;
	}

	@RequestMapping(value="delete/{nodeId}", method=RequestMethod.POST)
	public ModelAndView delete(@PathVariable int nodeId){

		User user = baseModel.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		baseModel.getPrevayler().execute(new DeleteContent(nodeId));

		return new ModelAndView("redirect:"+LIST, getEntityPluralName(), list(getEntityClass()));
	}

	@RequestMapping(value="update/{nodeId}", method=RequestMethod.POST)
	public ModelAndView update(@ModelAttribute T updatedContent, @PathVariable int oldNodeId, HttpSession session){
		/*
		 * TODO 
		 * hay que pasarle el hash a la vista, y comparar si el hash del updated es igual al anterior. 
		 * Si no son iguales, hay que pasar la metadata del viejo (fecha de creacion, etc) al nuevo, persistir el nuevo y borrar el viejo
		 * Por ahora el hash se saca del titulo
		 */

		T initialContent = (T)baseModel.getModel().getLoggedInUser().getContent(oldNodeId);

		ContentValidator<T> validator = getValidator(updatedContent, baseModel.getModel());
		if(updatedContent.getTitle().hashCode() != oldNodeId){
			/*
			 * el titulo se modifico, asi que tengo que reemplazar al Content inicial con el nuevo
			 */
			transferMetaData(initialContent, updatedContent);

			try{
				baseModel.getPrevayler().execute(new UpdateContent(updatedContent, validator));
			}catch(Exception e){
				if(CollectionUtils.isNotEmpty(validator.getErrors())){
					session.setAttribute("errors", validator.getErrors());
					return new ModelAndView("redirect:"+ADD);
				}
			}
		} else{
			/*
			 * el titulo no se modifico, por lo que solo aplico los cambios al Content original
			 */
			updateContent(initialContent, updatedContent);
			try{
				baseModel.getPrevayler().execute(new UpdateContent(initialContent, updatedContent, getValidator(null, baseModel.getModel())));
			}catch(Exception e){
				if(CollectionUtils.isNotEmpty(validator.getErrors())){
					session.setAttribute("errors", validator.getErrors());
					return new ModelAndView("redirect:"+ADD);
				}
			}
		}

		ModelAndView m = new ModelAndView();

		String base = (StringUtils.isEmpty(basePath)) ? StringUtils.EMPTY : basePath;
		m.setViewName("redirect:/" + base + File.separator + getEntityName() + File.separator + LIST);

		m.addObject(getEntityPluralName(), list(getEntityClass()));

		return m;
	}



	@RequestMapping(value="show/{id}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable int id, ModelAndView m){
		T content = (T)baseModel.getModel().loadContentById(id);

		if(content == null){
			/*
			 * TODO handle
			 */
		}
		processBeforeShow(content);
		m.addObject("content", content);
		m.addObject("contentType", getEntityName());
		m.addObject("tag", new Tag());
		m.setViewName("content/"+getEntityName()+"/"+SHOW);

		return m;
	}

}
