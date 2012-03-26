package com.mati.demo.controller.content;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Audio;
import com.mati.demo.model.user.User;
import com.mati.demo.model.validator.content.AudioValidator;
import com.mati.demo.model.validator.content.ContentValidator;
import com.mati.demo.util.MediaUtils;

@Controller
@RequestMapping("content/audio")
public class AudioController extends ContentController<Audio>{

	@Resource
	private MediaUtils mediaUtils;

	@Override
	protected ContentValidator<Audio> getValidator(Audio audio, Model model) {
		return new AudioValidator(audio, model, mediaUtils);
	}

	@Override
	protected List<Audio> listContent(User user) {
		return user.getAudios();
	}
	
	@Override
	protected void processBeforeShow(Audio content) {
		if(StringUtils.isNotEmpty(content.getFileName())){
			content.setMediaFileRef("http://"+getServerBasePath()+getEntityName()+"/"+content.getFileName());
		} 
		super.processBeforeShow(content);
	}

	@Override
	protected Audio createEntity() {
		return new Audio();
	}
	
	@Override
	protected void updateContent(Audio oldContent, Audio updatedContent) {
		
		oldContent.setBody(updatedContent.getBody());;
		oldContent.setFileName(updatedContent.getFileName());
		
		super.updateContent(oldContent, updatedContent);
	}

}
