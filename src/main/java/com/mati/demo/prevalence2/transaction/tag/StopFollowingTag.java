package com.mati.demo.prevalence2.transaction.tag;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.tag.Tag;

public class StopFollowingTag implements Transaction {

	
private final String _tagName;
	
	public StopFollowingTag(String tagName) {
		_tagName = tagName;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		Tag tagToStopFollowing = model.loadTagByTagName(_tagName);
		
		if(tagToStopFollowing == null){
			throw new RuntimeException("the tag to unfollow does not exist");
		}
		if(!model.getLoggedInUser().getFollowedTags().contains(tagToStopFollowing)){
			throw new RuntimeException("the tag "+tagToStopFollowing.getTagName()+" to unfollow is not being followed by "+model.getLoggedInUser().getUserName());
		}
		
		model.getLoggedInUser().unfollow(tagToStopFollowing);
		tagToStopFollowing.removeTagged(model.getLoggedInUser());
	}

}
