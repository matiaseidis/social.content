package com.mati.demo.prevalence.transaction.content;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.TagRepository;
import com.mati.demo.model.user.User;

public class CreateContent<T extends Content> implements Transaction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final T content;
	private final String userName;
	private final String plainTags;

	public CreateContent(T content, String userName, String plainTags) {
		this.content = content;
		this.userName = userName;
		this.plainTags = plainTags;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model)prevalentSystem;
			content.setId(content.hashCode());
			User user = model.loadUserByUsername(userName);
			content.setAuthor(user);
			content.setPostDate(new Date());
			
			setTags(content, model);
			
			user.addContent(content);
			model.addContent(content);
	}

	private void setTags(T content, Model model) {
		if(StringUtils.isNotEmpty(plainTags)){
			String [] tagNames = plainTags.split(",");
			
			for(String tagName : tagNames){
				content.addTag(model.getTagRepository(), new Tag(tagName));
			}
		}
	}
	
}