package com.mati.demo.model.content.type;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.mati.demo.model.content.Content;

public class Post extends Content{
	
	@NotNull(message = "Password must not be null.")
	@Size(min = 1, max = 64, message = "Password must not be blank.")
	@Getter @Setter private String title;
	
	@Setter @Getter private String body;

}
