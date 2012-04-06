package com.mati.demo.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.Comment;
import com.mati.demo.model.content.Content;
import com.mati.demo.model.content.type.Event;
import com.mati.demo.model.relationships.Relation;
import com.mati.demo.model.relationships.RelationType;
import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;
import com.mati.demo.prevalence.transaction.content.AddRelation;
import com.mati.demo.prevalence.transaction.content.RemoveRelation;
import com.mati.demo.prevalence.transaction.content.comment.CreateComment;
import com.mati.demo.prevalence.transaction.tag.StartFollowingTag;
import com.mati.demo.prevalence.transaction.tag.StopFollowingTag;
import com.mati.demo.prevalence.transaction.user.StartFollowingUser;
import com.mati.demo.prevalence.transaction.user.StopFollowingUser;

@Controller
@RequestMapping("ajax")
public class AjaxController {
	
	@Autowired @Setter @Getter private BaseModel baseModel;
	
	@Setter @Getter private int usersFixedTotal;
	@Setter @Getter private int contentFixedTotal;
	@Setter @Getter private int tagsFixedTotal;
	
	@Setter @Getter private int longFixedTotal;
	@Setter @Getter private int imgSize;

	private int total(int total, int fixed, int maxAllowed){
		int tot = (total <= fixed) ? total : fixed; 
		
		return (tot <= maxAllowed) ? tot : maxAllowed;
	}
	
	private int prev(int page){
		return (page > 0) ? page -1 : -1;
	}
	
	private int next(int page, int total, int listSize){
		return ((page * total) + total) < listSize ? page + 1 : 0;
	}
	

	@RequestMapping(value="searchBox", method=RequestMethod.GET)
	public ModelAndView searchBox(ModelAndView m){
		m.setViewName("search/box");
		return m;
	}
	
	@RequestMapping(value="relationsBox", method=RequestMethod.GET)
	public ModelAndView autoBox(ModelAndView m){
		m.setViewName("search/autocomplete");
		return m;
	}
	
	@RequestMapping(value="relations/add", method=RequestMethod.POST)
//	public @ResponseBody List<Relation> addRelations(@RequestParam int contentId, @RequestParam String relations, ModelAndView m){
	public ModelAndView addRelations(@RequestParam int contentId, @RequestParam String relations, ModelAndView m){
		
		Content c = getBaseModel().getModel().loadContentById(contentId);
		
		String[] rel = relations.split("R");
		
		for(String relationString : rel){
			if(StringUtils.isNotEmpty(relationString)){
				String[] elements = relationString.split("_");
				int id = Integer.valueOf(elements[0]);
				int type = Integer.valueOf(elements[1]);
				Content related = getBaseModel().getModel().loadContentById(id);
				if(related != null && related.getId() != c.getId()){
					Relation r = new Relation();
					r.setType(RelationType.get(type));
					r.setRelated(related);
					r.setAuthor(getBaseModel().getModel().getLoggedInUser());
					getBaseModel().getPrevayler().execute(new AddRelation(contentId, r));
				}
			}
		}
		m.addObject("content", c);
		m.setViewName("/content/relations-box");
		return m;
	}
	
	@RequestMapping(value="relation/remove", method=RequestMethod.POST)
	public @ResponseBody String removeRelation(@RequestParam int relatedId, @RequestParam int relationId, ModelAndView m){
		
		Content c = getBaseModel().getModel().loadContentById(relatedId);
		Relation r = null;
		if(c == null){
			// TODO handle
		}
		for(Relation rel : c.getRelations()){
			if(rel.getId() == relationId){
				r = rel;
				break;
			}
		}
		if(r == null){
			// TODO handle
		}
		getBaseModel().getPrevayler().execute(new RemoveRelation(c,r));
		return "La relacion ha sido eliminada";
	}
	
	@RequestMapping(value="relations/list", method=RequestMethod.GET)
	public @ResponseBody List<AutoCompleteItem> listRelationTypes(ModelAndView m){
		
		List<AutoCompleteItem> result = new ArrayList<AutoCompleteItem>();
		for(RelationType rt : RelationType.values()){
			AutoCompleteItem item = new AutoCompleteItem();
			item.setId(rt.getId());
			item.setValue(rt.getDescription());
			result.add(item);
		}
		return result;
	}
	
	@RequestMapping(value="comment/{id}", method=RequestMethod.POST)
	public ModelAndView comment(@ModelAttribute Comment comment, @PathVariable int id, ModelAndView m){
		Content content = baseModel.getModel().loadContentById(id);
		
		m.setViewName("content/comments-box");
		m.addObject("content", content);
		m.addObject("imgSize", imgSize);
		if(content == null || comment == null || StringUtils.isEmpty(comment.getBody())){
			m.addObject("message", "No puede agregar comentarios vacios");
			return m;
		} 
		
		baseModel.getPrevayler().execute(new CreateComment(comment, id, baseModel.getModel().getLoggedInUser().getUserName()));
		return m;
	}
	
	
	@RequestMapping(value="followedEvents/{page}/{total}", method=RequestMethod.GET)
	public ModelAndView followedEvents(@PathVariable Integer page, @PathVariable Integer total, ModelAndView m){

		int totalFollowedEvents = getBaseModel().getModel().getLoggedInUser().getFollowedEvents().size();

		total = total(total, contentFixedTotal, totalFollowedEvents);
		
		List<Event> result = getBaseModel().getModel().getLoggedInUser().getFollowedEvents(total, page);
		
		setPagination(m, "Proximos eventos de gente que seguis", result, prev(page), next(page, total, totalFollowedEvents), "followedEvents", page, total, totalFollowedEvents, imgSize);

		m.setViewName("paginated/events");
		return m;
	}
	
	
	@RequestMapping(value="followedContent/{page}/{total}", method=RequestMethod.GET)
	public ModelAndView followedContent(@PathVariable int page, @PathVariable int total, ModelAndView m){
		/*
		 * TODO parameterize this
		 * limit max value returned
		 */
		int totalFollowedContent = getBaseModel().getModel().getLoggedInUser().getFollowedNonEventContent().size();

		total = total(total, contentFixedTotal, totalFollowedContent);
		
		List<Content> result = getBaseModel().getModel().getLoggedInUser().getFollowedNonEventContent(total, page);
		
		setPagination(m, "Contenido de gente que seguis", result, prev(page), next(page, total, totalFollowedContent), "followedContent", page, total, totalFollowedContent, imgSize);

		m.setViewName("paginated/content");
		return m;
	}
	
	@RequestMapping(value="allContent/{page}/{total}", method=RequestMethod.GET)
	public ModelAndView allContent(@PathVariable Integer page, @PathVariable Integer total, ModelAndView m){

		int totalContent = getBaseModel().getModel().getContent().size();

		total = total(total, longFixedTotal, totalContent);

		List<Content> result = getBaseModel().getModel().getContent(total, page);
		
		setPagination(m, "Todo el contenido", result, prev(page), next(page, total, totalContent), "allContent", page, total, totalContent, imgSize);

		m.setViewName("paginated/content");
		return m;
	}
	
	@RequestMapping(value="followedUsers/{page}/{total}", method=RequestMethod.GET)
	public ModelAndView followedUsers(@PathVariable Integer page, @PathVariable Integer total, ModelAndView m){
		
		int totalFollowedUsers = getBaseModel().getModel().getLoggedInUser().getFollowedUsers().size();

		total = total(total, usersFixedTotal, totalFollowedUsers);
		
		List<User> result = getBaseModel().getModel().getLoggedInUser().getFollowedUsers(total, page);
		
		setPagination(m, "Usuarios que seguis", result, prev(page), next(page, total, totalFollowedUsers), "followedUsers", page, total, totalFollowedUsers, imgSize);
		
		m.setViewName("paginated/users");
		return m;
	}
	
	@RequestMapping(value="followedTags/{page}/{total}", method=RequestMethod.GET)
	public ModelAndView followedTags(@PathVariable Integer page, @PathVariable Integer total, ModelAndView m){
		
		int totalFollowedTags = getBaseModel().getModel().getLoggedInUser().getFollowedTags().size();

		total = total(total, tagsFixedTotal, totalFollowedTags);
		
		List<Tag> result = getBaseModel().getModel().getLoggedInUser().getFollowedTags(total, page);
		
		setPagination(m, "Etiquetas que seguis", result, prev(page), next(page, total, totalFollowedTags), "followedTags", page, total, totalFollowedTags, imgSize);
		
		m.setViewName("paginated/tags");
		return m;
	}
	
	@RequestMapping(value="followedBy/{page}/{total}", method=RequestMethod.GET)
	public ModelAndView followedBy(@PathVariable Integer page, @PathVariable Integer total, ModelAndView m){

		int totalFollowedBy = getBaseModel().getModel().getLoggedInUser().getFollowedBy().size();
		
		total = total(total, usersFixedTotal, totalFollowedBy);
		
		List<User> result = getBaseModel().getModel().getLoggedInUser().getFollowedBy(total, page);
		
		setPagination(m, "Usuarios que te siguen", result, prev(page), next(page, total, totalFollowedBy), "followedBy", page, total, totalFollowedBy, imgSize);
		
		m.setViewName("paginated/users");
		return m;
	}
	
	@RequestMapping(value="user/followUnfollow/{userName}", method=RequestMethod.POST)
	public ModelAndView followUnfollowUser(@PathVariable String userName, ModelAndView m){
		
		User follower = getBaseModel().getModel().getLoggedInUser();
		User followed = getBaseModel().getModel().loadUserByUsername(userName);
		
		if(follower == null || followed == null){
			return m;
		}
		
		if(follower.isFollowing(followed)){
			getBaseModel().getPrevayler().execute(new StopFollowingUser(userName, follower.getUserName()));
		} else{
			getBaseModel().getPrevayler().execute(new StartFollowingUser(userName, follower.getUserName()));
		}
		
		m.addObject("follower", follower);
		m.addObject("followed", followed);
		m.setViewName("/ajax/followUnfollowUser");
		return m;
	}
	
	/*
	 * works for follow and unfollow
	 */
	@RequestMapping(value="/tag/followUnfollow/{tagName}", method=RequestMethod.POST)
	public ModelAndView followUnfollowTag(@PathVariable String tagName, ModelAndView m){
		
		User follower = getBaseModel().getModel().getLoggedInUser();
		Tag tag = getBaseModel().getModel().loadTagByTagName(tagName);
		
		if(follower == null || tag == null){
			return m;
		}
		
		if(!follower.isFollowing(tag)){
			getBaseModel().getPrevayler().execute(new StartFollowingTag(tagName, follower.getUserName()));
		} else {
			getBaseModel().getPrevayler().execute(new StopFollowingTag(tagName, follower.getUserName()));
		}
		
		m.addObject("follower", follower);
		m.addObject("tag", tag);
		m.setViewName("/ajax/followUnfollowTag");
		return m;
		
	}
	
	@RequestMapping(value="/search/content", method = RequestMethod.GET)
	public ModelAndView searchContent(ModelAndView m, @RequestParam String pattern, HttpSession session){ 
		
		List<Content> content = null;
		
		if(StringUtils.isNotEmpty(pattern)){
			content = baseModel.getModel().searchContent(pattern); 
		}
		session.setAttribute("contentSearchResult", content);
		m.setViewName("/search/content-result");
		return m;
		
	}
	
	@RequestMapping(value="/search/autocomplete/content", method = RequestMethod.GET)
	public @ResponseBody List<AutoCompleteItem> autoSearchContent(ModelAndView m, @RequestParam String term, HttpSession session){ 
		List<AutoCompleteItem> result = new ArrayList<AutoCompleteItem>();
		
		List<Content> content = null;
		
		if(StringUtils.isNotEmpty(term)){
			content = baseModel.getModel().searchContent(term); 
		}
		for(Content c : content){
			AutoCompleteItem item = new AutoCompleteItem();
			item.setValue(c.getTitle());
			item.setId(c.getId());
			result.add(item);
		}
		return result;
	}
	
	@RequestMapping(value="/search/user", method = RequestMethod.GET)
	public ModelAndView searchUsers(ModelAndView m, @RequestParam String pattern, HttpSession session){ 
		
		List<User> users = null;
		
		if(StringUtils.isNotEmpty(pattern)){
			users = baseModel.getModel().searchUsers(pattern); 
		}
		session.setAttribute("usersSearchResult", users);
		m.setViewName("/search/user-result");
		return m;
		
	}
	
	@RequestMapping(value="/search/tag", method = RequestMethod.GET)
	public ModelAndView searchTags(ModelAndView m, @RequestParam String pattern, HttpSession session){ 
		
		List<Tag> tags = null;
		
		if(StringUtils.isNotEmpty(pattern)){
			tags = baseModel.getModel().searchTags(pattern); 
		}
		session.setAttribute("tagsSearchResult", tags);
		m.setViewName("/search/tag-result");
		return m;
		
	}
	
	private ModelAndView processForward(String methodName, int page, int total, ModelAndView m){
		ModelAndView result = new ModelAndView(); 
		Method method = null;
		Object[] o = new Object[]{page, total, m};
		try {
			method = this.getClass().getMethod(methodName, Integer.class, Integer.class, ModelAndView.class);
		} catch (SecurityException e) {e.printStackTrace();} 
		  catch (NoSuchMethodException e) {e.printStackTrace();}
		
		try {
			result = (ModelAndView)method.invoke(this, o);
		} catch (IllegalArgumentException e) {e.printStackTrace();} 
		catch (IllegalAccessException e) {e.printStackTrace();} 
		catch (InvocationTargetException e) {e.printStackTrace();}
		
		return result;
	}
	
	
	private ModelAndView setPagination(ModelAndView m, String title, List list, int prevPage, int nextPage, String updatedTagId, int page, int total, int fullTotal, int imgSize){
		m.addObject("title", title);
		m.addObject("followedList", list);
		m.addObject("prevPage", prevPage);
		m.addObject("nextPage", nextPage);
		m.addObject("updatedTagId", updatedTagId);
		m.addObject("page", page);
		m.addObject("total", (total<fullTotal) ? total : fullTotal);
		m.addObject("fullTotal", fullTotal);
		m.addObject("imgSize", imgSize);
		return m;
	}
}

class AutoCompleteItem{
	@Getter @Setter int id;
	@Getter @Setter String value;
}
