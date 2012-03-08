package com.mati.demo.controller.content;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.prevayler.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.video.CreateVideo;
import com.mati.demo.prevalence.transaction.content.video.DeleteVideo;
import com.mati.demo.prevalence.transaction.content.video.UpdateVideo;

@Controller
@RequestMapping("content/video/")
public class VideoController extends ContentController<Video>{

		@Setter @Getter private BaseModel baseModel;
		@Setter String staticContentBase;
		
		@Getter @Setter private String entityName;
		@Getter @Setter private String entityPluralName;
		@Getter @Setter	protected String serverBasePath;
		@Getter @Setter	protected String fileSystemBasePath;
		
		@Override
		protected Video retrieveContent(User user, int contentHash) {
			return getBaseModel().getModel().getLoggedInUser().getVideo(contentHash);
		}

		

		@Override
		protected void transferMetaData(Video oldContent, Video updatedContent) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void updateContent(Video oldContent, Video updatedContent) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected Transaction updateTransaction(Video initial, Video updated) {
			return new UpdateVideo(initial, updated);
		}

		@Override
		protected Transaction updateTransaction(Video updated) {
			return new UpdateVideo(updated);
		}

		@Override
		protected Transaction addTransaction(Video content) {
			return new CreateVideo(content);
		}

		

		@Override
		protected Transaction deleteTransaction(int nodeId) {
			return new DeleteVideo(nodeId);
		}

		@Override
		protected Collection listEntities() {
			return getBaseModel().getModel().getLoggedInUser().getVideos();
		}

		@Override
		protected Video getEntity(int nodeId) {
			return getBaseModel().getModel().getLoggedInUser().getVideo(nodeId);
		}
		
		@Override
		protected void processBeforeShow(Video content) {
			content.setBaseUrl("http://"+serverBasePath+getEntityName()+"/");
		}
		
		@Override
		protected boolean processContentBeforeSave(Video video, HttpServletRequest request) {
			
			CommonsMultipartFile multipartFile = video.getFileData();
			
			String destinationPath = fileSystemBasePath + File.separator + getEntityName() + File.separator; 
			
			String fileName = StringUtils.replace(multipartFile.getOriginalFilename(), " ", "-");
			
			File file = new File(destinationPath + fileName);
			try {
				FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
			} catch (IOException e) {
				Map<String, String> errors  = (Map<String, String>)request.getSession().getAttribute(ERRORS);// TODO Auto-generated catch block
				errors.put("save", "No se puedo guardar el video");
				return false;
			}
			
			video.setFileName(fileName);
			return super.processContentBeforeSave(video, request);
		}



		@Override
		protected boolean isValidContent(Video content,
				Map<String, Object> errors) {
			// TODO Auto-generated method stub
			return true;
		};
		
		@Override
		protected Video createEntity(){return new Video();}
}
