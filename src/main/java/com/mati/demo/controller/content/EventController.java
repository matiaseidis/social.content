package com.mati.demo.controller.content;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Event;
import com.mati.demo.model.user.User;
import com.mati.demo.model.validator.content.ContentValidator;
import com.mati.demo.model.validator.content.EventValidator;

@Controller
@RequestMapping("content/event")
public class EventController extends ContentController<Event>{

	@Override
	protected ContentValidator<Event> getValidator(Event content, Model model) {
		return new EventValidator(content, model);
	}

	@Override
	protected List<Event> listContent(User user) {
		return user.getEvents();
	}

	@Override
	protected Event createEntity() {
		return new Event();
	}
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	    dateFormat.setLenient(false);
//
//	    // true passed to CustomDateEditor constructor means convert empty String to null
//	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//	}
	
	@InitBinder
	public void allowEmptyDateBinding( WebDataBinder binder ){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(true);
	    // Allow for null values in date fields.
	    binder.registerCustomEditor( Date.class, new CustomDateEditor( dateFormat, true ));
	    // tell spring to set empty values as null instead of empty string.
	    binder.registerCustomEditor( String.class, new StringTrimmerEditor( true ));
	}

}
