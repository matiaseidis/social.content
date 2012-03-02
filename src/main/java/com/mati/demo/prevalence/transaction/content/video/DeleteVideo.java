package com.mati.demo.prevalence.transaction.content.video;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;

public class DeleteVideo implements Transaction {
	
	private static final long serialVersionUID = 1L;
	private final int id;
	
	public DeleteVideo(int nodeId) {
		this.id = nodeId;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		model.getLoggedInUser().deleteVideo(id);
	}

}
