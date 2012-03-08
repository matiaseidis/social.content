package com.mati.demo.controller.content;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.validator.GenericValidator;
import org.prevayler.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.post.CreatePost;
import com.mati.demo.prevalence.transaction.content.post.DeletePost;
import com.mati.demo.prevalence.transaction.content.post.UpdatePost;



@Controller
@RequestMapping("content/post/")
public class PostController extends ContentController<Post>{
	
	@Getter @Setter private BaseModel baseModel;
	@Setter private String staticContentBase;
	
	@Getter @Setter private String entityName;
	@Getter @Setter private String entityPluralName;
	
	@Override
	protected Post retrieveContent(User user, int contentHash) {
		return getBaseModel().getModel().getLoggedInUser().getPost(contentHash);
	}

	@Override
	protected Map<String, Object> isValidContent(Post updatedContent) {
		Map<String, Object> errors = new HashMap<String, Object>();
		
		String title = updatedContent.getTitle();
		if(GenericValidator.isBlankOrNull(title) || !GenericValidator.maxLength(title, 64)){
			errors.put("title", "el titulo es obligatorio");
			errors.put("post", updatedContent);
			
		}
		
		return errors;

	}

	@Override
	protected void transferMetaData(Post oldContent, Post updatedContent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void updateContent(Post oldContent, Post updatedContent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Transaction updateTransaction(Post initial, Post updated) {
		return new UpdatePost(initial, updated);
	}

	@Override
	protected Transaction updateTransaction(Post updated) {
		return new UpdatePost(updated);
	}

	@Override
	protected Transaction addTransaction(Post content) {
		return new CreatePost(content);
	}

	

	@Override
	protected Transaction deleteTransaction(int nodeId) {
		return new DeletePost(nodeId);
	}

	@Override
	protected Collection listEntities() {
		return getBaseModel().getModel().getLoggedInUser().getPosts();
	}

	@Override
	protected Post getEntity(int nodeId) {
		return getBaseModel().getModel().getLoggedInUser().getPost(nodeId);
	}

	@Override
	protected boolean isValidContent(Post content, Map<String, Object> errors) {
		// TODO Auto-generated method stub
		return true;
	}
}
