package com.mati.demo.prevalence.query

import java.util.Date;

import org.prevayler.Query;

class FollowedUsersContentQuery implements Query{
	
	def followedUsers
	
	def FollowedUsersContentQuery(followedUsers){
		this.followedUsers = followedUsers
	}

	public Object query(Object prevalentSystem, Date executionTime)
			throws Exception {
		
		return null;
	}

}
