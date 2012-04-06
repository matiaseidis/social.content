package com.mati.demo.model.relationships;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.mati.demo.model.content.Content;
import com.mati.demo.model.user.User;

public class Relation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter User author;
	@Setter private RelationType type;
	@Getter @Setter private Content related;
	@Getter @Setter private Date created;
	@Getter @Setter private int id;
	public String getName(){
		return type.getDescription();
	}
	
	public int getCode(){
		return type.getId();
	}
	
	@Override
	public int hashCode() {
		return getAuthor().getUserName().hashCode() + getCreated().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Relation)){
			return false;
		}
		Relation other = (Relation) obj;
		return getAuthor().getUserName().equals(other.getAuthor().getUserName()) && getCreated().equals(other.getCreated());
	}

}
