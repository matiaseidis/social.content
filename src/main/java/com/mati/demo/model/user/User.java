package com.mati.demo.model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.prevayler.Prevayler;
import org.springframework.util.CollectionUtils;

import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.tag.Taggable;
import com.mati.demo.prevalence.transaction.CreatePost;
import com.mati.demo.prevalence.transaction.CreateUser;

public class User extends Taggable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3080275587386819680L;

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	@Setter @Getter private String userName;
	@Setter @Getter private String password;
	@Setter @Getter private List<String> roles = new ArrayList<String>();
	
	@Setter @Getter private List<Post> posts = new ArrayList<Post>();
	
	public User() {
		
	}
	
//	public void create(Prevayler prevayler){
//		if(CollectionUtils.isEmpty(this.getRoles())){
//			this.getRoles().add(ROLE_USER);
//			if(this.getUserName().equals("admin")){
//				this.getRoles().add(ROLE_ADMIN);
//			}
//		}
//		
//		prevayler.execute(new CreateUser(this));
//	}

	public void addPost(Prevayler prevayler, Post post) {
		prevayler.execute(new CreatePost(this, post));
		
	}

}
