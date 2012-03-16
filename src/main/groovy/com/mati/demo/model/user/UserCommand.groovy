package com.mati.demo.model.user

import org.springframework.web.multipart.commons.CommonsMultipartFile;

class UserCommand {
	
	String userName;
	String password;
	String confirmPassword;
	String email
	String info
	CommonsMultipartFile image;

}
