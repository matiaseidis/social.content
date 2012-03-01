package com.mati.demo.controller.content;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.CreatePost;
import com.mati.demo.prevalence.transaction.DeletePost;
import com.mati.demo.prevalence.transaction.UpdatePost;



@Controller
@RequestMapping("content/post/")
public class PostController {
	
	@Resource
	private BaseModel baseModel;

	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView add(){
		return null;
	}
	
	@RequestMapping(value="create", method=RequestMethod.POST)
	public ModelAndView save(@ModelAttribute Post command){
		
		User user = baseModel.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		baseModel.getPrevayler().execute(new CreatePost(command));
		
		return new ModelAndView("redirect:list", "posts", user.getPosts());
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
		return new ModelAndView("content/post/edit", "post", post);
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
	public ModelAndView update(@ModelAttribute Post updatedPost, @PathVariable int oldNodeId){
		/*
		 * TODO 
		 * hay que pasarle el hash a la vista, y comparar si el hash del updated es igual al anterior. 
		 * Si no son iguales, hay que pasar la metadata del viejo (fecha de creacion, etc) al nuevo, persistir el nuevo y borrar el viejo
		 * Por ahora el hash se saca del titulo
		 */
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
