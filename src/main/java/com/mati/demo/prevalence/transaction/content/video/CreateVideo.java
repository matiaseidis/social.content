package com.mati.demo.prevalence.transaction.content.video;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Video;

public class CreateVideo implements Transaction {
	
	private final Video video;
	
	public CreateVideo(Video video){
		this.video = video;
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model)prevalentSystem;
		model.getLoggedInUser().addVideo(video);

	}

}
