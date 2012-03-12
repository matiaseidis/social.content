package com.mati.demo.prevalence.transaction.user;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.user.User;

public class CreateUser implements Transaction {

	private User user;
	private final String userRole = "ROLE_USER"; 
	
	public CreateUser() {}
	
	public CreateUser(User user) {this.user = user;}
	
	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		if(model.containsUser(user)){
			throw new RuntimeException("User "+user.getUserName()+" is already registrated");
		}
		user.getRoles().add(userRole);
		model.addUser(user);
	}

}
