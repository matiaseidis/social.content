package com.mati.demo.model.validator.content;

import com.mati.demo.model.base.Model;
import com.mati.demo.model.content.type.Event;

public class EventValidator extends ContentValidator<Event> {

	public EventValidator(Event content, Model model) {
		super(content, model);
	}

	@Override
	protected void performValidation() {
		Event e = (Event)getContent();
		
		if(e.getStart() == null){
			addError("start", "Debe ingresar una fecha de inicio");
		}
		
	}

}
