package com.mati.demo.prevalence.transaction.tag;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.tag.Tag;

public class CreateTag implements Transaction {

	private final Tag tag;
	
	public CreateTag(Tag tag) {
		this.tag = tag;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
//		if(model.hasTag(tag)){
//			throw new RuntimeException("Tag already exist");
//		}
		model.addTag(tag);
	}

}
