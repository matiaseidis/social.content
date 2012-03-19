package com.mati.demo.util;

import com.mati.demo.model.user.User;


public class UserUtils {
	
	public static boolean isFollowedBy(User followed, User follower){
		return follower.isFollowing(followed);
	}

}
