package com.mati.demo.controller.content;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.UrlValidator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.video.CreateVideo;
import com.mati.demo.prevalence.transaction.content.video.DeleteVideo;
import com.mati.demo.prevalence.transaction.content.video.UpdateVideo;

@Controller
@RequestMapping("content/video/")
public class VideoController {

		@Resource
		private BaseModel baseModel;
		

		
		@RequestMapping(value="add", method=RequestMethod.GET)
		public ModelAndView add(HttpSession session){
			ModelAndView m = retrieveErrorsFromSession(session);
			
			if(m == null){
				m = new ModelAndView();
			}
			
			m.setViewName("/content/video/add-edit");
			m.addObject("action", "create");
			m.addObject("video", new Video());
			return  m;
		}
		
		private ModelAndView retrieveErrorsFromSession(HttpSession session) {
			ModelAndView m = null;
			Map<String, String> errors = (Map<String, String>)session.getAttribute("errors");
			
			if(errors != null && !errors.isEmpty()){
				m = new ModelAndView(); 
				m.addObject("errors", errors);
			}
			
			session.setAttribute("errors", null);
			return m;
		}

		@RequestMapping(value="create", method=RequestMethod.POST)
		public ModelAndView save(Video video, BindingResult result, HttpSession session, HttpServletRequest request){
		
			
			if(sendErrorsToSession(session, isValidVideo(video))){
				return new ModelAndView("redirect:add");
			}
			
			if (result.hasErrors())
		    {
		      for(ObjectError error : result.getAllErrors()) {
		        System.err.println("Error: " + error.getCode() +  " - " + error.getDefaultMessage());
		      }
		      if(sendErrorsToSession(session, isValidVideo(video))){
					return new ModelAndView("redirect:add");
				}
		    }
			
			CommonsMultipartFile multipartFile = video.getFileData();
			String home = System.getProperty("user.home") + "/social.content.files/";
			String dir = "content/video/";
			String uri = home+dir+StringUtils.replace(multipartFile.getOriginalFilename(), " ", "-");
			
			System.out.println(uri);
			
			File file = new File(uri);
			try {
				FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			video.setUri(uri);
			
			User user = baseModel.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			
			baseModel.getPrevayler().execute(new CreateVideo(video));
			
			return new ModelAndView("redirect:list", "videos", user.getVideos());
		}
		 
		private boolean sendErrorsToSession(HttpSession session, Map<String, Object> errors) {
			
			if(!errors.isEmpty()){
				session.setAttribute("errors", errors);
				return true;
			}
			return false;
		}

		private Map<String, Object> isValidVideo(Video command) {

			boolean withErrors = false;
			Map<String, Object> errors = new HashMap<String, Object>();
			
			String title = command.getTitle();
			if(GenericValidator.isBlankOrNull(title) || !GenericValidator.maxLength(title, 64)){
				errors.put("title", "el titulo es obligatorio");
				withErrors = true;
			}
			
			UrlValidator urlValidator = new UrlValidator(); 
			if(urlValidator.isValid(command.getUri())){
				errors.put("uri", "uri no valida");
				withErrors = true;
			}
			
			if(withErrors){
				errors.put("video", command);
			}
			return errors;
		}

		@RequestMapping(value="delete/{nodeId}", method=RequestMethod.POST)
		public ModelAndView delete(@PathVariable int nodeId){
			
			User user = baseModel.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			
			baseModel.getPrevayler().execute(new DeleteVideo(nodeId));
			
			return new ModelAndView("redirect:list", "videos", user.getVideos());
		}
		
		@RequestMapping(value="edit/{nodeId}", method=RequestMethod.GET)
		public ModelAndView edit(@PathVariable int nodeId){
			Video video = baseModel.getModel().getLoggedInUser().getVideo(nodeId);
			ModelAndView m = new ModelAndView("content/video/add-edit", "video", video);
			m.addObject("action", "../update/"+nodeId);
			return  m;

		}
		
		@RequestMapping(value="{title}", method=RequestMethod.GET)
		public ModelAndView show(@PathVariable String title){
			ModelAndView mav = new ModelAndView();
			Video video = baseModel.getModel().getLoggedInUser().getVideo(title.hashCode());

			if(video == null){
				/*
				 * TODO handle
				 */
			}
			
			return new ModelAndView("content/video/show", "video", video);
		}
		
		@RequestMapping(value="update/{nodeId}", method=RequestMethod.POST)
		public ModelAndView update(@ModelAttribute Video updatedVideo, @PathVariable int oldNodeId, HttpSession session){
			/*
			 * TODO 
			 * hay que pasarle el hash a la vista, y comparar si el hash del updated es igual al anterior. 
			 * Si no son iguales, hay que pasar la metadata del viejo (fecha de creacion, etc) al nuevo, persistir el nuevo y borrar el viejo
			 * Por ahora el hash se saca del titulo
			 */

			if(this.sendErrorsToSession(session, isValidVideo(updatedVideo))){
				return new ModelAndView("redirect:edit");
			}
			
			Video oldVideo = baseModel.getModel().getLoggedInUser().getVideo(oldNodeId);
			
			if(updatedVideo.getTitle().hashCode() != oldNodeId){
				/*
				 * el titulo se modifico, asi que tengo que reemplazar al video
				 */
				transferMetaData(oldVideo, updatedVideo);
				baseModel.getPrevayler().execute(new UpdateVideo(oldVideo));
				
			} else{
				/*
				 * el titulo no se modifico, por lo que solo aplico los cambios al viejo video
				 */
				updateVideo(oldVideo, updatedVideo);
				baseModel.getPrevayler().execute(new UpdateVideo(oldVideo, updatedVideo));
			}
			
			/*
			 * TODO validate and update video
			 */
			return new ModelAndView("redirect:/content/video/list", "videos", baseModel.getModel().getLoggedInUser().getVideos());
		}

		private void updateVideo(Video oldVideo, Video updatedVideo) {
			// TODO Auto-generated method stub
			
		}

		private void transferMetaData(Video oldVideo, Video updatedVideo) {
			// TODO Auto-generated method stub
			
		}

		@RequestMapping(value="list", method=RequestMethod.GET)
		public ModelAndView list(){
			
			User user = baseModel.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			
			return new ModelAndView("content/video/list", "videos", user.getVideos());
		}
	
}
