package com.mati.demo.prevalence.transaction;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.user.User;

public class CreateUser implements Transaction {

	private User user;
	
	public CreateUser() {}
	
	public CreateUser(User user) {this.user = user;}
	
	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model baseModel = (Model) prevalentSystem;
		baseModel.getUsersMap().put(user.getUserName(), user);
	}

}
