package com.mati.demo.prevalence.transaction.content.video;

import java.util.Date;

import org.prevayler.Transaction;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Video;

public class UpdateVideo implements Transaction {

	private final Video oldVideo;
	private final Video updatedVideo;
	
	public UpdateVideo(Video oldVideo, Video updatedVideo) {
		this.oldVideo = oldVideo;
		this.updatedVideo = updatedVideo;
	}
	public UpdateVideo(Video oldVideo) {
		this(oldVideo, null);
	}

	public void executeOn(Object prevalentSystem, Date executionTime) {
		Model model = (Model) prevalentSystem;
		model.getLoggedInUser().updateVideo(oldVideo, updatedVideo);
	}

}
