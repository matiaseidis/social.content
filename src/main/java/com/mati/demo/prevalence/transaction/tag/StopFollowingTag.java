package com.mati.demo.prevalence.transaction.tag;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;

public class StopFollowingTag implements Transaction {

	
private final String _tagName;
private final String loggedInUserName;

	
	public StopFollowingTag(String tagName, String loggedInUserName) {
		_tagName = tagName;
		this.loggedInUserName = loggedInUserName;

	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		Tag tagToStopFollowing = model.loadTagByTagName(_tagName);
		
		if(tagToStopFollowing == null){
			throw new RuntimeException("the tag to unfollow does not exist");
		}
		
		User loggedInUser = model.loadUserByUsername(loggedInUserName);

		if(!loggedInUser.getFollowedTags().contains(tagToStopFollowing)){
			throw new RuntimeException("the tag "+tagToStopFollowing.getTagName()+" to unfollow is not being followed by "+loggedInUser.getUserName());
		}
		
		loggedInUser.unfollow(tagToStopFollowing);
		tagToStopFollowing.removeTagged(loggedInUser);
	}

}
