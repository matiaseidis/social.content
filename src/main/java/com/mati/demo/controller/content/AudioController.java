package com.mati.demo.controller.content;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Audio;
import com.mati.demo.model.user.User;
import com.mati.demo.model.validator.content.AudioValidator;
import com.mati.demo.model.validator.content.ContentValidator;
import com.mati.demo.prevalence.BaseModel;

@Controller
@RequestMapping("content/audio")
public class AudioController extends ContentController<Audio>{

	@Autowired @Setter @Getter private BaseModel baseModel;
	@Setter String staticContentBase;
	@Getter @Setter private String entityName;
	@Getter @Setter private String entityPluralName;
	@Getter @Setter	protected String serverBasePath;
	@Getter @Setter	protected String fileSystemBasePath;

	@Override
	protected ContentValidator<Audio> getValidator(Audio audio, Model model) {
		return new AudioValidator(audio, model, fileSystemBasePath);
	}

	@Override
	protected List<Audio> listContent(User user) {
		// TODO Auto-generated method stub
		return user.getAudios();
	}
	
	@Override
	protected void processBeforeShow(Audio content) {
		if(StringUtils.isNotEmpty(content.getFileName())){
			content.setAudioRef("http://"+serverBasePath+getEntityName()+"/"+content.getFileName());
		} 
		super.processBeforeShow(content);
	}

	@Override
	protected Audio createEntity() {
		return new Audio();
	}

}
