package com.mati.demo.prevalence2.transaction.user;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.user.User;

public class StartFollowingUser implements Transaction {

	private final String followedUserName;
	
	public StartFollowingUser(String followedUserName) {
		this.followedUserName= followedUserName;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		
		User userToFollow = model.loadUserByUsername(followedUserName);
		if(userToFollow == null){
			throw new RuntimeException("the user to follow does not exist");
		}
		if(userToFollow.equals(model.getLoggedInUser())){
			throw new RuntimeException("the user can not follow himself");
		}
		if(model.getLoggedInUser().getFollowedUsers().contains(userToFollow)){
			throw new RuntimeException("the user "+userToFollow.getUserName()+" to follow is already being followed by "+model.getLoggedInUser().getUserName());
		}
		
		model.getLoggedInUser().follow(userToFollow);
		userToFollow.addFollower(model.getLoggedInUser());

	}

}
