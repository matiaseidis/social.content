package com.mati.demo.prevalence.transaction.content;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.tag.Tag;

public class UpdateContent implements Transaction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Content content;
	private final String loggedInUserName;
	public Logger logger = Logger.getLogger(getClass());

	
	/*
	 * cons for title not changed
	 */
	public UpdateContent(Content content, String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
		this.content = content;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		
		List<Tag> originalTags = model.loadContentById(content.getId()).getTags();
		
		List<Tag> updatedTags = content.getTags();
		
		if(!originalTags.equals(updatedTags)){
			logger.info("NOT SAME");
			
			List<Tag> diff = new ArrayList<Tag>(originalTags);
			diff.removeAll(updatedTags);
			
			for(Tag t : diff){
				t.removeTagged(content);
				content.removeTag(model.getTagRepository(), t);
			}
		} 

		model.updateContent(content);
	}
}
