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

	@Autowired @Setter @Getter private BaseModel baseModel;
	@Setter String staticContentBase;

	@Getter @Setter private String entityName;
	@Getter @Setter private String entityPluralName;
	@Getter @Setter	protected String serverBasePath;
	@Getter @Setter	protected String fileSystemBasePath;

	@Override
	protected void transferMetaData(Video oldContent, Video updatedContent) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateContent(Video oldContent, Video updatedContent) {
		// TODO Auto-generated method stub
	}

	@Override
	protected Video createEntity(){return new Video();}

	@Override
	protected ContentValidator getValidator(Video video, Model model) {
		return new VideoValidator(video, model, fileSystemBasePath);
	}

	@Override
	protected void processBeforeShow(Video content) {
		if(StringUtils.isNotEmpty(content.getFileName())){
			content.setVideoRef("http://"+serverBasePath+getEntityName()+"/"+content.getFileName());
		} else {
			content.setVideoRef(content.getUrl());
		}

		super.processBeforeShow(content);
	}

	@Override
	protected List<Video> listContent(User user) {
		return user.getVideos();
	}
}
