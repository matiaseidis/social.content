package com.mati.demo.model.content.type;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class Post implements Serializable{
	
	@Setter @Getter private String title;
	@Setter @Getter private String body;

}
