package com.mati.demo.model.content;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.Taggable;
import com.mati.demo.model.user.User;

public abstract class Content extends Taggable implements Commentable{

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;
	int id;
	Date postDate;
	String title;
	User author;
	String contentType = getClass().getSimpleName().toLowerCase();
	
	private List<Comment> comments = new ArrayList<Comment>(); 
	
	private Map<String, Tag> tagsMap = new HashMap<String, Tag>();

	@Override
	protected void registerWithTag(Tag tag) {
		tag.addTagged(this);
	}

	@Override
	protected void unregisterWithTag(Tag tag) {
		tag.removeTagged(this);
	}
	
//	protected abstract String getContentType();
	
	public void comment(Comment comment) {
		comments.add(comment);
	}
	
	@Override
	public int hashCode() {
		return getTitle().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Content)){
			return false;
		}
		return this.getTitle().equals(((Content)obj).getTitle());
	}

	public boolean hasTag(Tag tag) {
		return tagsMap.containsKey(tag.getTagName());
	}

}
