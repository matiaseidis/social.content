package com.mati.demo.controller.content;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.GenericValidator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.post.CreatePost;
import com.mati.demo.prevalence.transaction.content.post.DeletePost;
import com.mati.demo.prevalence.transaction.content.post.UpdatePost;



@Controller
@RequestMapping("content/post/")
public class PostController {
	
	@Resource
	private BaseModel baseModel;
	

	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView add(HttpSession session){
		ModelAndView m = retrieveErrorsFromSession(session);
		
		if(m == null){
			m = new ModelAndView();
		}
		
		m.setViewName("/content/post/add-edit");
		m.addObject("action", "create");
		return  m;
	}
	
	private ModelAndView retrieveErrorsFromSession(HttpSession session) {
		ModelAndView m = null;
		Map<String, String> errors = (Map<String, String>)session.getAttribute("errors");
		
		if(errors != null && !errors.isEmpty()){
			m = new ModelAndView(); 
			m.addObject("errors", errors);
		}
		
		session.setAttribute("errors", null);
		return m;
	}

	@RequestMapping(value="create", method=RequestMethod.POST)
	public ModelAndView save(@ModelAttribute Post post, HttpSession session){
	
		if(sendErrorsToSession(session, isValidPost(post))){
			return new ModelAndView("redirect:add");
		}
		
		User user = baseModel.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		baseModel.getPrevayler().execute(new CreatePost(post));
		
		return new ModelAndView("redirect:list", "posts", user.getPosts());
	}
	 
	private boolean sendErrorsToSession(HttpSession session, Map<String, Object> errors) {
		
		if(!errors.isEmpty()){
			session.setAttribute("errors", errors);
			return true;
		}
		return false;
	}

	private Map<String, Object> isValidPost(Post command) {

		Map<String, Object> errors = new HashMap<String, Object>();
		
		String title = command.getTitle();
		if(GenericValidator.isBlankOrNull(title) || !GenericValidator.maxLength(title, 64)){
			errors.put("title", "el titulo es obligatorio");
			errors.put("post", command);
			
		}
		
		return errors;
	}

	@RequestMapping(value="delete/{nodeId}", method=RequestMethod.POST)
	public ModelAndView delete(@PathVariable int nodeId){
		
		User user = baseModel.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		baseModel.getPrevayler().execute(new DeletePost(nodeId));
		
		return new ModelAndView("redirect:list", "posts", user.getPosts());
	}
	
	@RequestMapping(value="edit/{nodeId}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable int nodeId){
		Post post = baseModel.getModel().getLoggedInUser().getPost(nodeId);
		ModelAndView m = new ModelAndView("content/post/add-edit", "post", post);
		m.addObject("action", "../update/"+nodeId);
		return  m;

	}
	
	@RequestMapping(value="{title}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable String title){
		ModelAndView mav = new ModelAndView();
		Post post = baseModel.getModel().getLoggedInUser().getPost(title.hashCode());

		if(post == null){
			/*
			 * TODO handle
			 */
		}
		
		return new ModelAndView("content/post/show", "post", post);
	}
	
	@RequestMapping(value="update/{nodeId}", method=RequestMethod.POST)
	public ModelAndView update(@ModelAttribute Post updatedPost, @PathVariable int oldNodeId, HttpSession session){
		/*
		 * TODO 
		 * hay que pasarle el hash a la vista, y comparar si el hash del updated es igual al anterior. 
		 * Si no son iguales, hay que pasar la metadata del viejo (fecha de creacion, etc) al nuevo, persistir el nuevo y borrar el viejo
		 * Por ahora el hash se saca del titulo
		 */

		if(this.sendErrorsToSession(session, isValidPost(updatedPost))){
			return new ModelAndView("redirect:edit");
		}
		
		Post oldPost = baseModel.getModel().getLoggedInUser().getPost(oldNodeId);
		
		if(updatedPost.getTitle().hashCode() != oldNodeId){
			/*
			 * el titulo se modifico, asi que tengo que reemplazar al post
			 */
			transferMetaData(oldPost, updatedPost);
			baseModel.getPrevayler().execute(new UpdatePost(oldPost));
			
		} else{
			/*
			 * el titulo no se modifico, por lo que solo aplico los cambios al viejo post
			 */
			updatePost(oldPost, updatedPost);
			baseModel.getPrevayler().execute(new UpdatePost(oldPost, updatedPost));
		}
		
		/*
		 * TODO validate and update post
		 */
		return new ModelAndView("redirect:/content/post/list", "posts", baseModel.getModel().getLoggedInUser().getPosts());
	}

	private void updatePost(Post oldPost, Post updatedPost) {
		// TODO Auto-generated method stub
		
	}

	private void transferMetaData(Post oldPost, Post updatedPost) {
		// TODO Auto-generated method stub
		
	}

	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView list(){
		
		User user = baseModel.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		return new ModelAndView("content/post/list", "posts", user.getPosts());
	}
		

}
