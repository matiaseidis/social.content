package com.mati.demo.util;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Video;

public class ContentUtils {
	
	
	
	static String thumbnailPre = "http://i4.ytimg.com/vi/";
	static String thumbnailPos = "/default.jpg";
	
	
	public static String youTubeThumbnailUri(Video video) {
		String[] s = video.getVideoRef().split("/");
		return thumbnailPre + s[s.length-1] + thumbnailPos;
	}
	
	public static boolean isContentFromLoggedInUser(Integer contentId, Model model){
		return model.getLoggedInUser().getContent(contentId) != null;
	}
}
