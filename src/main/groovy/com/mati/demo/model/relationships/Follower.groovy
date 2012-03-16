package com.mati.demo.model.relationships;

import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;

public interface Follower {
	
	public void follow(User u);
	
	public void unfollow(User u);
	
	public void follow(Tag t);
	
	public void unfollow(Tag t);

}
