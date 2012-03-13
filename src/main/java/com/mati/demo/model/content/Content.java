package com.mati.demo.model.content;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.Taggable;
import com.mati.demo.model.user.User;

public abstract class Content extends Taggable implements Commentable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter @Setter private int id;
	@Getter @Setter private Date postDate;
	@Getter @Setter private String title;
	@Getter @Setter private User author;
	@Getter @Setter private String contentType = getClass().getSimpleName().toLowerCase();
	
	@Getter private final List<Comment> comments = new ArrayList<Comment>(); 

	@Override
	protected void registerWithTag(Tag tag) {
		tag.addTagged(this);
	}

	@Override
	protected void unregisterWithTag(Tag tag) {
		tag.removeTagged(this);
	}
	
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

}
