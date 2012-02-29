package com.mati.demo.prevalence;

import lombok.Getter;
import lombok.Setter;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;
import org.prevayler.foundation.monitor.SimpleMonitor;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.user.User;

public class BaseModel {
	
	@Setter private String prevalenceDirectory;
	@Getter private Prevayler prevayler;
	@Setter private PrevaylerFactory prevaylerFactory;

	public BaseModel(){}
	
	public void init(){
		prevaylerFactory = new PrevaylerFactory();
		prevaylerFactory.configurePrevalenceDirectory(prevalenceDirectory);
		prevaylerFactory.configurePrevalentSystem(new Model());
		prevaylerFactory.configureMonitor(new SimpleMonitor(System.err));

		try{
		prevayler = prevaylerFactory.create(); 
		}catch(Exception e){
			System.err.println("FAILDE TO LOAD PREVALENT SYSTEM " + e);
			System.exit(1);
		}
	}
	
	
	
	public Model getModel(){
		return (Model) prevayler.prevalentSystem();
	}

	public User loadUserByUsername(String username) {
			return getModel().loadUserByUsername(username);
	}

}
