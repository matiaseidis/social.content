package com.mati.demo.model.relationships;

import lombok.Getter;

public enum RelationType {

	TIENE_QUE_VER_CON(0, "Tiene que ver con"),
	ES_HORRIBLE_IGUAL_QUE(1, "Es igual que");

	@Getter private final int id;

	@Getter private final String description;

	RelationType(int id, String description){
		this.id = id;
		this.description = description;
	}

	public static final RelationType get(int id) {
		for (RelationType rt : RelationType.values()) {
			if (rt.id == id) {
				return rt;
			}
		}
		throw new IllegalArgumentException(String.format("Invalid Relation type ID " + id));
	}
	
	@Override
	public String toString() {
		return Integer.toString(this.id);
	}
}
