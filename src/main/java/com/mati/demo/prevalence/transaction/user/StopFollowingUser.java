package com.mati.demo.prevalence.transaction.user;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.user.User;

public class StopFollowingUser implements Transaction {

	
	private final String followedUserName;
	private final String loggedInUserName;
	
	public StopFollowingUser(String followedUserName, String loggedInUserName) {
		this.followedUserName= followedUserName;
		this.loggedInUserName = loggedInUserName;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		
		User userToStopFollowing = model.loadUserByUsername(followedUserName);
		User loggedInUser = model.loadUserByUsername(loggedInUserName);
		
		if(userToStopFollowing == null){
			throw new RuntimeException("the user to unfollow does not exist");
		}
		if(userToStopFollowing.equals(loggedInUser)){
			throw new RuntimeException("the user can not follow or unfollow himself");
		}
		if(!loggedInUser.getFollowedUsers().contains(userToStopFollowing)){
			throw new RuntimeException("the user "+userToStopFollowing.getUserName()+" to unfollow is no being followed by "+loggedInUser.getUserName());
		}
		
		loggedInUser.unfollow(userToStopFollowing);
		userToStopFollowing.removeFollower(loggedInUser);

	}

}
