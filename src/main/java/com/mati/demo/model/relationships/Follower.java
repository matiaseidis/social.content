package com.mati.demo.model.relationships;

import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user._User;

public interface Follower {
	
	public void follow(_User u);
	
	public void unfollow(_User u);
	
	public void follow(Tag t);
	
	public void unfollow(Tag t);

}
