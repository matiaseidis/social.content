package com.mati.demo.model.relationships;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Relation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter private String name;
	@Getter @Setter private Integer relatedId;

}
