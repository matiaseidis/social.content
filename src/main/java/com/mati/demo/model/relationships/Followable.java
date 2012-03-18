package com.mati.demo.model.relationships;

import java.io.Serializable;

import com.mati.demo.model.user.User;

public interface Followable extends Serializable{
	
	public void startFollowing(User follower);
	
	public void stopFollowing(User follower);
	
}
