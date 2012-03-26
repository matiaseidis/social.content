package com.mati.demo.context;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mati.demo.model.content.type.Audio;
import com.mati.demo.model.content.type.Event;
import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.CreateContent;
import com.mati.demo.prevalence.transaction.tag.CreateTag;
import com.mati.demo.prevalence.transaction.tag.StartFollowingTag;
import com.mati.demo.prevalence.transaction.user.CreateUser;
import com.mati.demo.prevalence.transaction.user.StartFollowingUser;

public class ContextListener implements ServletContextListener {

	public Logger logger = Logger.getLogger(getClass());
	

	
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("Staring web app");
	}
	

	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("Shutting down web app");
		ServletContext ctx = sce.getServletContext();
		WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(ctx);
		BaseModel baseModel = (BaseModel) springContext.getBean("base.model");
		try {
			/*
			 * take snapshot on context shutdown
			 */
			baseModel.getPrevayler().takeSnapshot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
