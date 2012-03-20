package com.mati.demo.prevalence.transaction.user;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.user.User;

public class StartFollowingUser implements Transaction {

	private final String followedUserName;
	private final String loggedInUserName;

	
	public StartFollowingUser(String followedUserName, String loggedInUserName) {
		this.followedUserName= followedUserName;
		this.loggedInUserName = loggedInUserName;

	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		
		User userToFollow = model.loadUserByUsername(followedUserName);
		User loggedInUser = model.loadUserByUsername(loggedInUserName);
		
		if(userToFollow == null){
			throw new RuntimeException("the user to follow does not exist");
		}
		if(userToFollow.equals(loggedInUser)){
			throw new RuntimeException("the user can not follow himself");
		}
		if(loggedInUser.getFollowedUsers().contains(userToFollow)){
			throw new RuntimeException("the user "+userToFollow.getUserName()+" to follow is already being followed by "+loggedInUser.getUserName());
		}
		
		loggedInUser.follow(userToFollow);
		userToFollow.addFollower(loggedInUser);

	}

}
