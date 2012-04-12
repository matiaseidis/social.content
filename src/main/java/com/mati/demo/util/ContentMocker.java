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
	
	String audioFile = "01.-the-width-of-a-circle.mp3";
	
	String teaser = "Ut in ipsum felis, vitae tempus mi. Proin in lobortis libero. In vitae feugiat augue. Duis in magna sed elit bibendum porttitor. In velit urna, accumsan vel pharetra tincidunt, rutrum sit amet libero. Sed adipiscing magna id risus euismod porta. Pellentesque vel augue arcu. Pellentesque malesuada dignissim tortor sollicitudin commodo. Pellentesque ut ipsum a enim eleifend congue. Fusce placerat dolor vel magna suscipit lacinia. Nam venenatis commodo lacus vitae porttitor. Nullam in lacus eget tortor commodo auctor. Curabitur consequat, turpis sed dictum consequat, risus orci semper risus, a dignissim nisl velit quis turpis. Donec facilisis condimentum urna, a dignissim libero gravida nec. Sed lectus massa, iaculis sed bibendum id, facilisis quis neque. Vivamus fermentum mauris eget purus pretium ut bibendum lectus tempus. ";
	
	String body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse commodo laoreet nisl, at euismod felis porttitor non. Vivamus laoreet tempus lorem, eu tristique dui molestie et. Mauris in eros felis. Curabitur ac odio ac nisl feugiat dapibus a vestibulum tortor. Suspendisse pretium, tortor sit amet pretium tempus, augue orci ultricies leo, eget varius purus leo at felis. Integer pretium massa et orci facilisis vestibulum. Ut rutrum nisl vel urna tristique posuere. Phasellus adipiscing elit ut leo placerat nec blandit libero pellentesque. Cras eget lectus non sem sagittis adipiscing non et enim. In ac leo in nunc accumsan facilisis. Phasellus hendrerit malesuada est in venenatis. Quisque non rutrum turpis. Morbi gravida congue dictum. Morbi lacinia pretium euismod. Pellentesque venenatis, massa sit amet dignissim malesuada, mi dui placerat purus, at tempus mi massa vitae ante. Pellentesque lorem mi, vestibulum rhoncus tincidunt non, mollis consectetur sem." +
"Ut in ipsum felis, vitae tempus mi. Proin in lobortis libero. In vitae feugiat augue. Duis in magna sed elit bibendum porttitor. In velit urna, accumsan vel pharetra tincidunt, rutrum sit amet libero. Sed adipiscing magna id risus euismod porta. Pellentesque vel augue arcu. Pellentesque malesuada dignissim tortor sollicitudin commodo. Pellentesque ut ipsum a enim eleifend congue. Fusce placerat dolor vel magna suscipit lacinia. Nam venenatis commodo lacus vitae porttitor. Nullam in lacus eget tortor commodo auctor. Curabitur consequat, turpis sed dictum consequat, risus orci semper risus, a dignissim nisl velit quis turpis. Donec facilisis condimentum urna, a dignissim libero gravida nec. Sed lectus massa, iaculis sed bibendum id, facilisis quis neque. Vivamus fermentum mauris eget purus pretium ut bibendum lectus tempus." +
"Aliquam pharetra sem vel augue euismod eleifend. Nulla at elit velit, et tincidunt risus. Proin faucibus, libero non gravida condimentum, urna dui tempus libero, ut suscipit diam neque nec diam. Proin tempor, elit in sodales porttitor, dolor purus accumsan eros, quis suscipit lectus augue id nisl. Sed ornare ligula rhoncus augue vehicula convallis. Ut ut tellus nisi. Pellentesque a lectus malesuada sem viverra eleifend. Nulla sed ipsum id erat ultricies interdum at non dolor." +
"Donec faucibus, neque a bibendum volutpat, odio augue ultrices risus, quis porta mi lacus sed urna. Cras libero nibh, accumsan eu tincidunt ac, porta in massa. Nam pulvinar scelerisque lacus, quis porta diam elementum id. Ut in nibh vel quam sodales viverra. Donec gravida mauris ac purus aliquam vel vulputate sem rhoncus. In mi turpis, faucibus sed rhoncus ac, condimentum quis libero. Proin ac sollicitudin justo. Pellentesque lobortis consequat arcu. Cras tristique enim non sem scelerisque vehicula. Aliquam tincidunt erat laoreet erat iaculis consequat." +
"Ut tincidunt dapibus justo at facilisis. Nulla vel est a nunc ornare convallis. Sed quis neque massa, at tristique ligula. Aliquam eget ipsum lacus, mattis lacinia purus. Cras rutrum mi eu magna tincidunt tempus. Integer lobortis placerat ipsum, in egestas enim semper et. Curabitur auctor lacus at mauris rhoncus fringilla. Suspendisse lacus risus, ullamcorper vitae laoreet eget, malesuada in magna. In tortor nunc, fermentum eu venenatis eget, porta vitae augue. In hac habitasse platea dictumst. Nunc dolor risus, porttitor non facilisis vitae, dapibus quis nisi. Donec massa mi, scelerisque vitae malesuada eget, dictum a odio. Nullam lobortis laoreet leo, posuere volutpat nulla pharetra et. Cras tincidunt fermentum urna non blandit. Cras at velit quam, eu bibendum nisi. ";

	final private BaseModel baseModel;
	
	int number = 20;
	
	
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
//			try {

				baseModel.getPrevayler().execute(new StartFollowingUser(u.getUserName(), admin.getUserName()));
				baseModel.getPrevayler().execute(new StartFollowingUser(admin.getUserName(), u.getUserName() ));
				for(int c = 0; i<5;i++){
					baseModel.getPrevayler().execute(new CreateContent<Video>(video(c, u), u.getUserName(), null));
					baseModel.getPrevayler().execute(new CreateContent<Audio>(audio(c, u), u.getUserName(), null));
					baseModel.getPrevayler().execute(new CreateContent<Post>(post(c, u), u.getUserName(), null));
					baseModel.getPrevayler().execute(new CreateContent<Event>(event(c, u), u.getUserName(), null));
				}

//			}catch(AuthenticationException ae){
				//TODO handle this
				
//				System.out.println(ae);
//			}
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
		content.setBody(teaser);
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
		content.setBody(teaser);
		content.setFileName(audioFile);
		return content;
	}

	private Post post(int i, User user){
		Post content = new Post();
		content.setTitle("Un post - "+i);
		content.setAuthor(user);
		content.setId(content.hashCode());
		content.setBody(body);
		content.addTag(baseModel.getModel().getTagRepository(), tag(i));
		return content;
	}

	private Event event(int i, User user){
		Event content = new Event();
		content.setTitle("Un event - "+i);
		content.setAuthor(user);
		content.setBody(teaser);
		content.setId(content.hashCode());
		
		DateTime date = new DateTime(new Date());
		date = date.plusMonths(1);
		content.setStart(date.toDate());
		content.addTag(baseModel.getModel().getTagRepository(), tag(i));
		return content;
	}

	
}
