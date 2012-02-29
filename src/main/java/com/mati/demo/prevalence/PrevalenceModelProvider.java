package com.mati.demo.prevalence;

import lombok.Getter;
import lombok.Setter;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

import com.mati.demo.model.base.BaseModel;
import com.mati.demo.model.user.User;

public class PrevalenceModelProvider {
	
	@Setter private BaseModel prevalentSystem;
	@Setter private String prevalenceDirectory;
	@Setter private String prevalenceBase;
	private BaseModel baseModel;
	@Getter private Prevayler prevayler;
	@Setter private PrevaylerFactory prevaylerFactory;

	public PrevalenceModelProvider(){}
	
	public void init(){
		prevaylerFactory.configurePrevalenceDirectory(prevalenceDirectory);
		try{
			if(baseModel == null){
				prevayler = prevaylerFactory.createPrevayler(prevalentSystem, prevalenceBase); 
				baseModel = (BaseModel) prevayler.prevalentSystem();
				baseModel.setPrevayler(prevayler);
			}
		}catch(Exception e){
			System.err.println("FAILDE TO LOAD PREVALENT SYSTEM " + e);
			System.exit(1);
		}
	}
	
	
	
	public BaseModel getModel(){
		return baseModel;
	}

	public User loadUserByUsername(String username) {
			return getModel().loadUserByUsername(username);
	}

}
