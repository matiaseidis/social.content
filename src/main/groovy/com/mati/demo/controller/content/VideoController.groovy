package com.mati.demo.controller.content;


import org.apache.commons.lang.StringUtils
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.validator.content.ContentValidator;
import com.mati.demo.model.validator.content.VideoValidator
import com.mati.demo.prevalence.BaseModel;

@Controller
@RequestMapping("content/video/")
public class VideoController extends ContentController<Video>{

//	def baseModel
//	def baseModel
	String staticContentBase;


	String serverBasePath;
	String fileSystemBasePath;
	

	@Override
	protected void transferMetaData(Video oldContent, Video updatedContent) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateContent(Video oldContent, Video updatedContent) {
		// TODO Auto-generated method stub

	}

	//		@Override
	//		protected Transaction deleteTransaction(int nodeId) {
	//			return new Delete(nodeId);
	//		}

	//		@Override
	//		protected Collection listEntities() {
	//			return getBaseModel().getModel().getLoggedInUser().getVideos();
	//		}

	//		@Override
	//		protected Video getEntity(int nodeId) {
	//			return getBaseModel().getModel().getLoggedInUser().getVideo(nodeId);
	//		}
	//
	@Override
	protected void processBeforeShow(Video content) {
		if(StringUtils.isNotEmpty(content.getFileName())){
			content.setVideoRef("http://"+serverBasePath+getEntityName()+"/"+content.getFileName());
		} else {
			content.setVideoRef(content.getUrl());
		}
	}

	//		@Override
	//		protected boolean processContentBeforeSave(Video video, HttpServletRequest request) {
	//
	//			CommonsMultipartFile multipartFile = video.getFileData();
	//
	//			String destinationPath = fileSystemBasePath + File.separator + getEntityName() + File.separator;
	//
	//			String fileName = StringUtils.replace(multipartFile.getOriginalFilename(), " ", "-");
	//
	//			File file = new File(destinationPath + fileName);
	//			try {
	//				FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
	//			} catch (IOException e) {
	//				Map<String, String> errors  = (Map<String, String>)request.getSession().getAttribute(ERRORS);// TODO Auto-generated catch block
	//				errors.put("save", "No se puedo guardar el video");
	//				return false;
	//			}
	//
	//			video.setFileName(fileName);
	//			return super.processContentBeforeSave(video, request);
	//		}


	@Override
	protected Video createEntity(){return new Video();}

	@Override
	protected ContentValidator getValidator(Video video, Model model) {
		new VideoValidator(video, model, fileSystemBasePath);
	}
}
