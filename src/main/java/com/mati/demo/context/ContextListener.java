package com.mati.demo.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.tag.CreateTag;
import com.mati.demo.prevalence.transaction.user.CreateUser;

public class ContextListener implements ServletContextListener {

	public Logger logger = Logger.getLogger(getClass());
	
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ADMIN_NAME = "admin";
	public static final String ADMIN_PASS = "admin";
	
	public void contextInitialized(ServletContextEvent sce) {
		
		logger.info("Staring web app");
		
		ServletContext ctx = sce.getServletContext();
		WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(ctx);
		BaseModel baseModel = (BaseModel) springContext.getBean("base.model");
//		UserAccountController userAccountController = (UserAccountController) springContext.getBean("user.account.controller");
		
		
		/*
		 * check if admin user exists
		 */
		if(baseModel.loadUserByUsername("admin") == null){
			/*
			 * create admin user
			 */
			User admin = new User();
			admin.setUserName(ADMIN_NAME);
			admin.setPassword(ADMIN_PASS);
//			admin.getRoles().add(ROLE_USER);
			admin.getRoles().add(ROLE_ADMIN);
			
			baseModel.getPrevayler().execute(new CreateUser(admin));
//			userAccountController.register(admin, null, null);
			
			String userName = "user-";
			for(int i = 0; i < 5; i++){
				User u = new User();
				u.setUserName(userName+i);
				u.setPassword(userName+i);
//				u.getRoles().add(ROLE_USER);
				
				baseModel.getPrevayler().execute(new CreateUser(u));
//				userAccountController.register(admin, null, null);
				
			}
		}
//		for(int i = 0; i < 5; i++){
//
//			Tag tag = new Tag("tag-"+i);
//			try{
//				baseModel.getPrevayler().execute(new CreateTag(tag));
//			}catch(RuntimeException e){
//				logger.info("pincho por tag existente");
//			}
//		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		/*
		 * take snapshot on context shutdown
		 */
		ServletContext ctx = sce.getServletContext();
		WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(ctx);
		BaseModel baseModel = (BaseModel) springContext.getBean("base.model");
		try {
			baseModel.getPrevayler().takeSnapshot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
