package com.mati.demo.prevalence2.transaction.tag;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.tag.Tag;

public class StartFollowingTag implements Transaction {

	private final String _tagName;

	public StartFollowingTag(String tagName) {
		_tagName = tagName;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		Tag tagToStartFollowing = model.loadTagByTagName(_tagName);
		
		if(tagToStartFollowing == null){
			throw new RuntimeException("the tag to follow does not exist");
		}
		if(model.getLoggedInUser().getFollowedTags().contains(tagToStartFollowing)){
			throw new RuntimeException("the tag "+tagToStartFollowing.getTagName()+" to follow is already being followed by "+model.getLoggedInUser().getUserName());
		}
		
		model.getLoggedInUser().follow(tagToStartFollowing);
		tagToStartFollowing.addTagged(model.getLoggedInUser());
	}

}
