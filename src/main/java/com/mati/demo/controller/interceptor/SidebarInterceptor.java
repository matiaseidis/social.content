package com.mati.demo.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

public class SidebarInterceptor extends HandlerInterceptorAdapter{

	@Autowired @Setter @Getter private BaseModel baseModel;	
	
	@Getter @Setter String serverBasePath;
	@Getter @Setter String userPictureFolder;

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView m) throws Exception {
	
		String userPictureURI = "http://"+serverBasePath+userPictureFolder+"/";
		String userDefaultImgFileName = "default";
		String userPictureExt = ".png";
		
		request.setAttribute("userDefaultImgFileName", userDefaultImgFileName);
		request.setAttribute("userPictureURI", userPictureURI);
		request.setAttribute("userPictureExt", userPictureExt);

		super.postHandle(request, response, handler, m);

	}
}
