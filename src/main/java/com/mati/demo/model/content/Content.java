package com.mati.demo.model.content;

import java.util.Comparator;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

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
	
	@Getter @Setter private int visited;
	
//	@Getter @Setter private MetaData meta = new MetaData();
	@Getter @Setter private String title;
	@Getter @Setter private User author;
	@Getter final private String contentType = getClass().getSimpleName().toLowerCase();
	
//	@Getter private final List<Comment> comments = new ArrayList<Comment>(); 
	@Getter private final SortedSet<Comment> comments = new TreeSet<Comment>(new Comparator<Comment>(){

		@Override
		public int compare(Comment c1, Comment c2) {
			return c2.getPostDate().compareTo(c1.getPostDate());
		}
	}); 
	
//	private Map<String, Tag> tagsMap = new HashMap<String, Tag>();

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
		return getAuthor().getUserName().hashCode() + getTitle().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Content)){
			return false;
		}
		return this.getTitle().equals(((Content)obj).getTitle());
	}

	public boolean hasTag(Tag tag) {
		return getTags().contains(tag);
	}
	
}
