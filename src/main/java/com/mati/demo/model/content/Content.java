package com.mati.demo.model.content;

import java.io.Serializable;

import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.tag.Taggable;

import lombok.Getter;
import lombok.Setter;

public abstract class Content extends Taggable implements Serializable{

	@Getter @Setter private String title;

	@Override
	protected void registerWithTag(Tag tag) {
		tag.addTagged(this);
	}

	@Override
	protected void unregisterWithTag(Tag tag) {
		tag.removeTagged(this);
	}
	
}
