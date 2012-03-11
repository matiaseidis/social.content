package com.mati.demo.controller.profile;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.tag.Tag;
import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

@Controller
public class ProfileController {

	@Resource(name="base.model") private BaseModel baseModel;

	@RequestMapping("/profile")
	public ModelAndView profile(ModelAndView m){
		
		m.addObject("users", baseModel.getModel().getUsers());
		return m;
	}


	
}
