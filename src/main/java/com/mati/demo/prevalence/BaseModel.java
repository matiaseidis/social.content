package com.mati.demo.prevalence;

import lombok.Getter;
import lombok.Setter;

import org.apache.log4j.Logger;
import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;
import org.prevayler.foundation.monitor.SimpleMonitor;
import org.prevayler.foundation.serialization.XStreamSerializer;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.transaction.user.AddTagToUser;
import com.mati.demo.prevalence.transaction.user.RemoveTagFromUser;

public class BaseModel {
	
	public Logger logger = Logger.getLogger(getClass());
	
	@Setter private String prevalenceDirectory;
	@Getter private Prevayler prevayler;
	@Setter private PrevaylerFactory prevaylerFactory;
	
	public BaseModel(){}
	
	public void init(){
		prevaylerFactory = new PrevaylerFactory();
		prevaylerFactory.configurePrevalenceDirectory(prevalenceDirectory);
		prevaylerFactory.configurePrevalentSystem(new Model());
		prevaylerFactory.configureMonitor(new SimpleMonitor(System.err));
		/*
		 * food taster off
		 */
		prevaylerFactory.configureTransactionFiltering(false);
		
		/*
		 * XML format
		 */
		XStreamSerializer s = new XStreamSerializer();
		prevaylerFactory.configureJournalSerializer(s);
		prevaylerFactory.configureSnapshotSerializer(s);

		try{
		prevayler = prevaylerFactory.create(); 
		}catch(Exception e){
			logger.error("FAILED TO LOAD PREVALENT SYSTEM " + e);
			logger.error(e.getMessage());
			System.exit(1);
		}
	}
	
	public Model getModel(){
		return (Model) prevayler.prevalentSystem();
	}

	public User loadUserByUsername(String username) {
		return getModel().loadUserByUsername(username);
	}
	
	public void addTagToUser(Tag tag){
		prevayler.execute(new AddTagToUser(tag, getModel().getLoggedInUser().getUserName()));
	}
	
	public void removeTagFromUser(Tag tag){
		prevayler.execute(new RemoveTagFromUser(tag, getModel().getLoggedInUser().getUserName()));
	}
	
//	public void addContent(Post post) {
//		prevayler.execute(new CreatePost(post));
//	}
//	
}
