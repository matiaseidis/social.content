package com.mati.demo.model.validator;

import javax.annotation.Resource;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mati.demo.model.content.type.Post;
import com.mati.demo.prevalence.BaseModel;

public class PostValidator implements Validator {

	@Resource
	private BaseModel baseModel;
	
	public boolean supports(Class<?> arg) {
		return Post.class.equals(arg);
	}

	public void validate(Object obj, Errors errors) {
		
		Post post = (Post) obj;
		 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required", "Hay que poner un titulo");
 
		if (!errors.hasFieldErrors("title")) {
			
			if (isInModel(post))
				errors.rejectValue("title", "duplicated", "Ya existe un post con el mismo titulo");
		}		
	}

	private boolean isInModel(Post post) {
		return baseModel.getModel().getLoggedInUser().getPost(post.getTitle().hashCode()) != null;
	}

}
