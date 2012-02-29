package com.mati.demo.model.content.type;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import com.mati.demo.model.content.Content;

public class Post implements Content, Serializable{
	
	@Setter @Getter private String title;
	@Setter @Getter private String body;

}
