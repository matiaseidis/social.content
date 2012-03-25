package com.mati.demo.controller.content;

import java.util.List;

import javax.annotation.Resource;

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
import com.mati.demo.util.MediaUtils;

@Controller
@RequestMapping("content/video")
public class VideoController extends ContentController<Video>{

	@Resource
	private MediaUtils mediaUtils;
	
	@Override
	protected Video createEntity(){return new Video();}

	@Override
	protected ContentValidator<Video> getValidator(Video video, Model model) {
		return new VideoValidator(video, model, mediaUtils);
	}

	@Override
	protected void processBeforeShow(Video content) {
		
		if(StringUtils.isNotEmpty(content.getFileName())){
			content.setMediaFileRef("http://"+getServerBasePath()+getEntityName()+"/"+content.getFileName());
		} else {
			content.setMediaFileRef(content.getUrl());
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
