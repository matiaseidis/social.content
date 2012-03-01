package com.mati.demo.model.content;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.Taggable;

public abstract class Content extends Taggable implements Serializable{

	@Getter @Setter private int id;
	
	@NotNull(message = "Password must not be null.")
	@Size(min = 1, max = 64, message = "Password must not be blank.")
	@Getter @Setter private String title;

	@Override
	protected void registerWithTag(Tag tag) {
		tag.addTagged(this);
	}

	@Override
	protected void unregisterWithTag(Tag tag) {
		tag.removeTagged(this);
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
