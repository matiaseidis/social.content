package com.mati.demo.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.authentication.AuthenticationProviderImpl;
import com.mati.demo.model.user.User;
import com.mati.demo.model.validator.ValidationError;
import com.mati.demo.model.validator.Validator;
import com.mati.demo.model.validator.user.UserValidator;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.user.CreateUser;

@Controller
public class UserAccountController {

	@Autowired @Getter @Setter private BaseModel baseModel;
	@Resource(name="authenticationProvider") private AuthenticationProviderImpl authenticationProvider;

	@Getter @Setter private String fileSystemBasePath;

	@Getter @Setter private String userPictureFolder;
	@Setter private String defaultPictureName = "default";
	@Setter private String defaultImgExt= ".png";



	@RequestMapping(value="register", method=RequestMethod.GET)
	public ModelAndView registerForm(ModelAndView m){
		m.addObject("user", new User());
		m.addObject("action", "register");
		m.setViewName("account/register");
		return m;
	}

	@RequestMapping(value="welcome", method=RequestMethod.GET)
	public ModelAndView welcome(ModelAndView m){
		return m;
	}

	@RequestMapping(value="register", method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute User user, HttpServletRequest request, ModelAndView m){

		Validator v= new UserValidator(user, fileSystemBasePath, userPictureFolder);
		boolean unavailable = v.exists(baseModel.getModel());
		if(v.validate() && !unavailable){
			try{
				processImage(user, v);
				baseModel.getPrevayler().execute(new CreateUser(user));
			}catch(Exception e){
				v.addError("unavailable","El nombre ya esta siendo usado por otro usuario");
				m.addObject("errors", toMap(v.getErrors()));
				m.setViewName("account/register");
				return m;
			}
			m.setViewName("redirect:welcome");
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
			try {
				Authentication auth = authenticationProvider.authenticate(token);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}catch(AuthenticationException ae){
				//TODO handle this
			}

		} else{
			m.addObject("errors", toMap(v.getErrors()));
			m.addObject("user", user);
			m.setViewName("account/register");
		}
		return m;
	}

	private void processImage(User user, Validator v) {

		String destinationPath = fileSystemBasePath + File.separator + userPictureFolder + File.separator;

		if(user.getImage() == null || user.getImage().getSize() == 0){
			/*
			 * the user did not upload any image, setting a default one
			 */
			InputStream is = defaultImage(destinationPath);
			if (is != null){
				saveImage(user, v, destinationPath, is);
			}

		} else {
			/*
			 * process the image uploaded by the user 
			 */
			/* 
			 * TODO check image size 
			 * */
			CommonsMultipartFile multipartFile = user.getImage();

			InputStream is = null;

			try {
				is = multipartFile.getInputStream();
				saveImage(user, v, destinationPath, is);

			} catch (IOException e1) {
				v.addError("save", "No se puedo guardar la imagen, inetente cargarla en otro momento.");
				saveImage(user, v, destinationPath, defaultImage(destinationPath));
			}

		}
	}

	private InputStream defaultImage(String destinationPath) {
		InputStream is = null;
		try{
			is = new FileInputStream(destinationPath + defaultPictureName + defaultImgExt);
		}catch (Exception e) {
			System.out.println("unable to load the default img at " + destinationPath + defaultPictureName);
		}
		return is;
	}

	private void saveImage(User user, Validator v, String destinationPath, InputStream is) {

		String fileName = StringUtils.replace(user.getUserName().toLowerCase(), " ", "-");

		File file = new File(destinationPath + fileName + defaultImgExt);
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			BufferedImage l_original_image  = ImageIO.read(is);

			ImageIO.write(l_original_image, "PNG", out);

			byte[] imageBytes = out.toByteArray(); 

			FileUtils.writeByteArrayToFile(file, imageBytes);

		} catch (IOException e) {
			v.addError("save", "No se puedo guardar la imagen, inetente cargarla en otro momento.");
		}

	}

	private Map<String, Object> toMap(List<ValidationError> errors) {
		Map<String, Object> errorsMap = new HashMap<String, Object>();
		for(ValidationError ve : errors){
			errorsMap.put(ve.getCode(), ve.getMessage());
		}
		return errorsMap;
	}


}

		