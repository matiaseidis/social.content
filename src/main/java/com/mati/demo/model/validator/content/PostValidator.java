package com.mati.demo.model.validator.content;

import org.apache.commons.validator.GenericValidator;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Post;

public class PostValidator extends ContentValidator<Post> {

	
	
	public PostValidator(Post content, Model model) {
		super(content, model);
	}

	@Override
	protected void performValidation() {
		String title = getContent().getTitle();
		if(GenericValidator.isBlankOrNull(title) || !GenericValidator.maxLength(title, 64)){
			addError("title", "el titulo es obligatorio");
			addError("post", getContent());
			
		}
	}

//	private boolean isInModel(Post post) {
//		return baseModel.getModel().getLoggedInUser().getContent(post.getTitle().hashCode()) != null;
//	}

		 
		


}
