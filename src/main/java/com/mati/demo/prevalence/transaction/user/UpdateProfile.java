package com.mati.demo.prevalence.transaction.user;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.user.User;

public class UpdateProfile implements Transaction {

	private final String userName; 
	private final String email; 
	private final String info;
	
	public UpdateProfile(String userName, String email, String info) {
		this.userName = userName;
		this.email = email;
		this.info = info;
	}

	@Override
	public void executeOn(Object arg0, Date arg1) {
		Model model = (Model)arg0;
		User user = model.loadUserByUsername(userName);
		
		user.setEmail(email);
		user.setInfo(info);
		
		/*
		 * TODO log
		 */
	}

}
