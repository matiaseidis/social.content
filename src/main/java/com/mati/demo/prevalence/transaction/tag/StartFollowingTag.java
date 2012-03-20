package com.mati.demo.prevalence.transaction.tag;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;

public class StartFollowingTag implements Transaction {

	private final String _tagName;
	private final String loggedInUserName;

	public StartFollowingTag(String tagName, String loggedInUserName) {
		this._tagName = tagName;
		this.loggedInUserName = loggedInUserName;

	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		Tag tagToStartFollowing = model.loadTagByTagName(_tagName);
		
		if(tagToStartFollowing == null){
			throw new RuntimeException("the tag to follow does not exist");
		}
		User loggedInUser = model.loadUserByUsername(loggedInUserName);

		if(loggedInUser.getFollowedTags().contains(tagToStartFollowing)){
			throw new RuntimeException("the tag "+tagToStartFollowing.getTagName()+" to follow is already being followed by "+loggedInUser.getUserName());
		}
		
		loggedInUser.follow(tagToStartFollowing);
		tagToStartFollowing.addTagged(loggedInUser);
	}

}
