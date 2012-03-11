package com.mati.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value="register", method=RequestMethod.GET)
	public ModelAndView registerForm(ModelAndView m){
		m.addObject("user", new User());
		m.addObject("action", "register");
		m.setViewName("account/register");
		return m;
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute User user, HttpServletRequest request, ModelAndView m){
		
		Validator v= new UserValidator(user);
		boolean unavailable = v.exists(baseModel.getModel());
		if(v.validate() && !unavailable){
			try{
				baseModel.getPrevayler().execute(new CreateUser(user));
			}catch(Exception e){
				v.addError("unavailable","El nombre ya esta siendo usado por otro usuario");
				m.addObject("errors", toMap(v.getErrors()));
				m.setViewName("account/register");
				return m;
			}
			m.setViewName("welcome");
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

	private Map<String, Object> toMap(List<ValidationError> errors) {
		Map<String, Object> errorsMap = new HashMap<String, Object>();
		for(ValidationError ve : errors){
			errorsMap.put(ve.getCode(), ve.getMessage());
		}
		return errorsMap;
	}
	
	
}

		