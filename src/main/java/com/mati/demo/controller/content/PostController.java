package com.mati.demo.controller.content;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.user.User;
import com.mati.demo.model.validator.content.ContentValidator;
import com.mati.demo.model.validator.content.PostValidator;
import com.mati.demo.prevalence.BaseModel;



@Controller
@RequestMapping("content/post")
public class PostController extends ContentController<Post>{
	
//	@Autowired @Getter @Setter private BaseModel baseModel;
//	@Setter private String staticContentBase;
	
//	@Getter @Setter private String entityName;
//	@Getter @Setter private String entityPluralName;
	
	@Override
	protected ContentValidator<Post> getValidator(Post post, Model model) {
		return new PostValidator(post, model);
	}

	@Override
	protected List<Post> listContent(User user) {
		return user.getPosts();
	}

	@Override
	protected Post createEntity() {
		return new Post(); 
	}
}
