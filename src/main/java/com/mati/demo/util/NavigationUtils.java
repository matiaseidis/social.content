package com.mati.demo.util;

import java.util.Collection;

public class NavigationUtils {
	
	public static int from(Collection c, int page, int total){
		int from = (c.isEmpty() || page * total > c.size()) ? 0 : page * total; 
		return from;
	}
	
	public static int to(Collection c, int page, int total){
		int to = (((page * total) + total) < c.size()) ? (page * total) + total : c.size();
		return to;
	}

}
