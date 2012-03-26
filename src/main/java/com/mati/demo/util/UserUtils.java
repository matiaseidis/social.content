package com.mati.demo.util;

import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;


public class UserUtils {
	
	public static boolean isUserFollowedBy(User followed, User follower){
		return follower.isFollowing(followed);
	}
	
	public static boolean isTagFollowedBy(Tag followed, User follower){
		return follower.isFollowing(followed);
	}

}
