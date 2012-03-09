package com.mati.demo.prevalence.transaction.user;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.user.User;

public class StopFollowingUser implements Transaction {

	
private final String followedUserName;
	
	public StopFollowingUser(String followedUserName) {
		this.followedUserName= followedUserName;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		
		User userToStopFollowing = model.loadUserByUsername(followedUserName);
		
		if(userToStopFollowing == null){
			throw new RuntimeException("the user to stop following does not exist");
		}
		
		model.getLoggedInUser().unfollow(userToStopFollowing);
		userToStopFollowing.removeFollower(model.getLoggedInUser());

	}

}
