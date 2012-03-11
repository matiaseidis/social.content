package com.mati.demo.controller.content;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.validator.content.ContentValidator;
import com.mati.demo.model.validator.content.PostValidator;
import com.mati.demo.prevalence.BaseModel;



@Controller
@RequestMapping("content/post/")
public class PostController extends ContentController<Post>{
	
	@Autowired @Getter @Setter private BaseModel baseModel;
	@Setter private String staticContentBase;
	
	@Getter @Setter private String entityName;
	@Getter @Setter private String entityPluralName;
	
//	@Override
//	protected Map<String, Object> isValidContent(Post updatedContent) {
//		Map<String, Object> errors = new HashMap<String, Object>();
//		
//		String title = updatedContent.getTitle();
//		if(GenericValidator.isBlankOrNull(title) || !GenericValidator.maxLength(title, 64)){
//			errors.put("title", "el titulo es obligatorio");
//			errors.put("post", updatedContent);
//			
//		}
//		
//		return errors;
//
//	}

	@Override
	protected void transferMetaData(Post oldContent, Post updatedContent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void updateContent(Post oldContent, Post updatedContent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected ContentValidator<Post> getValidator(Post post, Model model) {
		return new PostValidator(post, model);
	}

//	@Override
//	protected Transaction updateTransaction(Post initial, Post updated) {
//		return new Update(initial, updated);
//	}
//
//	@Override
//	protected Transaction updateTransaction(Post updated) {
//		return new Update(updated);
//	}
//
//	@Override
//	protected Transaction addTransaction(Post content) {
//		return new Create(content, new PostValidator());
//	}
//
//	@Override
//	protected Transaction deleteTransaction(int nodeId) {
//		return new Delete(nodeId);
//	}

//	@Override
//	protected boolean isValidContent(Post content, Map<String, Object> errors) {
//		// TODO Auto-generated method stub
//		return true;
//	}
}
