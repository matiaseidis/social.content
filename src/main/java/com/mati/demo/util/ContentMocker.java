package com.mati.demo.util;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.security.core.AuthenticationException;

import com.mati.demo.model.content.type.Audio;
import com.mati.demo.model.content.type.Event;
import com.mati.demo.model.content.type.Post;
import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.CreateContent;
import com.mati.demo.prevalence.transaction.user.CreateUser;
import com.mati.demo.prevalence.transaction.user.StartFollowingUser;

public class ContentMocker {
	
	final private static String ROLE_ADMIN = "ROLE_ADMIN";
	final private static String ADMIN_NAME = "admin";
	final private static String ADMIN_PASS = "admin";

	final private BaseModel baseModel;
	
	int number = 200;
	
	
	public ContentMocker(BaseModel baseModel/*, AuthenticationProviderImpl authenticationProvider*/){
		this.baseModel = baseModel;
	}

	public void mock(HttpSession session) {

		/*
		 * create admin user
		 */
		User admin = new User();
		admin.setUserName(ADMIN_NAME);
		admin.setPassword(ADMIN_PASS);

		admin.getRoles().add(ROLE_ADMIN);

		baseModel.getPrevayler().execute(new CreateUser(admin));

		String userName = "user-";
		for(int i = 0; i < number; i++){
			User u = new User();
			u.setUserName(userName+i);
			u.setPassword(userName+i);

			baseModel.getPrevayler().execute(new CreateUser(u));
			try {

//				baseModel.getPrevayler().execute(new StartFollowingUser(u.getUserName(), admin.getUserName()));
//				baseModel.getPrevayler().execute(new StartFollowingUser(admin.getUserName(), u.getUserName() ));
				for(int c = 0; i<5;i++){
					baseModel.getPrevayler().execute(new CreateContent<Video>(video(c, u), u.getUserName(), null));
					baseModel.getPrevayler().execute(new CreateContent<Audio>(audio(c, u), u.getUserName(), null));
					baseModel.getPrevayler().execute(new CreateContent<Post>(post(c, u), u.getUserName(), null));
					baseModel.getPrevayler().execute(new CreateContent<Event>(event(c, u), u.getUserName(), null));
				}

			}catch(AuthenticationException ae){
				//TODO handle this
				
				System.out.println(ae);
			}
		}
	}

	/******** content mocking ******/

	private Video video(int i, User user){
		Video content = new Video();
		content.setTitle("Un video - "+i);
		content.setAuthor(user);
		content.setId(content.hashCode());
		content.setUrl("http://youtu.be/_Ej4H3rSUIw");
		content.addTag(baseModel.getModel().getTagRepository(), tag(i));
		return content;
	}
	
	private Tag tag(int i) {
		Tag tag = new Tag();
		tag.setTagName("tag mock " + i);
		return tag;
	}

	private Audio audio(int i, User user){
		Audio content = new Audio();
		content.setTitle("Un audio - "+i);
		content.setAuthor(user);
		content.setId(content.hashCode());
		content.addTag(baseModel.getModel().getTagRepository(), tag(i));
		return content;
	}

	private Post post(int i, User user){
		Post content = new Post();
		content.setTitle("Un post - "+i);
		content.setAuthor(user);
		content.setId(content.hashCode());
		content.setBody("body body body body body body body ");
		content.addTag(baseModel.getModel().getTagRepository(), tag(i));
		return content;
	}

	private Event event(int i, User user){
		Event content = new Event();
		content.setTitle("Un event - "+i);
		content.setAuthor(user);
		content.setId(content.hashCode());
		
		DateTime date = new DateTime(new Date());
		date = date.plusMonths(1);
		content.setStart(date.toDate());
		content.addTag(baseModel.getModel().getTagRepository(), tag(i));
		return content;
	}

	
}
