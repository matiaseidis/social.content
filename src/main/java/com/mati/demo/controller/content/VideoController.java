package com.mati.demo.controller.content;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.user.User;
import com.mati.demo.model.validator.content.ContentValidator;
import com.mati.demo.model.validator.content.VideoValidator;
import com.mati.demo.prevalence.BaseModel;

@Controller
@RequestMapping("content/video")
public class VideoController extends ContentController<Video>{

	@Override
	protected Video createEntity(){return new Video();}

	@Override
	protected ContentValidator<Video> getValidator(Video video, Model model) {
		return new VideoValidator(video, model, getFileSystemBasePath());
	}

	@Override
	protected void processBeforeShow(Video content) {
		
		if(StringUtils.isNotEmpty(content.getFileName())){
			content.setVideoRef("http://"+getServerBasePath()+getEntityName()+"/"+content.getFileName());
		} else {
			content.setVideoRef(content.getUrl());
		}

		super.processBeforeShow(content);
	}

	@Override
	protected void updateContent(Video oldContent, Video updatedContent) {
		
		oldContent.setBody(updatedContent.getBody());;
		oldContent.setFileData(updatedContent.getFileData());
		oldContent.setFileName(updatedContent.getFileName());
		oldContent.setUrl(updatedContent.getUrl());
		
		super.updateContent(oldContent, updatedContent);
	}

	@Override
	protected List<Video> listContent(User user) {
		return user.getVideos();
	}
}
