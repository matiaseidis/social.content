package com.mati.demo.util;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;

import com.mati.demo.model.content.type.Video;

public class ContentUtils {
	
	
	
	static String thumbnailPre = "http://i4.ytimg.com/vi/";
	static String thumbnailPos = "/default.jpg";
	
	
	public static String youTubeThumbnailUri(Video video) {
		String[] s = video.getVideoRef().split("/");
		return thumbnailPre + s[s.length-1] + thumbnailPos;
	}
}
