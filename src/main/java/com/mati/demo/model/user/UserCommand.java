package com.mati.demo.model.user;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UserCommand {
	
	
	@Getter @Setter private String userName;
	@Getter @Setter private String password;
	@Getter @Setter private String confirmPassword;
	@Getter @Setter private String email;
	@Getter @Setter private String info;
	@Getter @Setter private CommonsMultipartFile image;

}
