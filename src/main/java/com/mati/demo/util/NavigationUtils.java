package com.mati.demo.util;

import java.util.Collection;

public class NavigationUtils {
	
	public static int from(Collection c, int page, int total){
		int from = (c.isEmpty() || page * total > c.size()) ? 0 : page * total; 
		return from;
	}
	
	public static int to(Collection c, int page, int total){
		int size = c.isEmpty() ? 0 : c.size()-1;
		
		int to = (((page * total) + total) <= size) ? (page * total) + total : size;
		return to;
	}

}
