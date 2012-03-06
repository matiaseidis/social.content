package com.mati.demo.controller
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mati.demo.model.content.type.Video;
import com.mati.demo.model.user.User;

@Controller
class GroovyController{
	
	@RequestMapping("/status_groovy")
    @ResponseBody def getStatus() {
        "Hello World from groovy!"
    }



}
