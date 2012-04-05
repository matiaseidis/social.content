package com.mati.demo.model.relationships;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import com.mati.demo.model.user.User;

public class Relation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter User author;
	@Setter private RelationType type;
//	@Getter @Setter private String name;
	@Getter @Setter private Integer relatedId;
	
	public String getName(){
		return type.getDescription();
	}
	
	public int getCode(){
		return type.getId();
	}

}
