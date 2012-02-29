package com.mati.demo.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.CreateUser;

public class ContextListener implements ServletContextListener {

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ADMIN_NAME = "admin";
	public static final String ADMIN_PASS = "admin";
	
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();
		WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(ctx);
		BaseModel baseModel = (BaseModel) springContext.getBean("base.model");
		/*
		 * check if admin user exists
		 */
		if(baseModel.loadUserByUsername("admin") == null){
			/*
			 * create admin user
			 */
			User user = new User();
			user.setUserName(ADMIN_NAME);
			user.setPassword(ADMIN_PASS);
			user.getRoles().add(ROLE_USER);
			user.getRoles().add(ROLE_ADMIN);
			
			baseModel.getPrevayler().execute(new CreateUser(user));
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
