package com.mati.demo
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class GroovyController{
	
	@RequestMapping("/status_groovy")
    @ResponseBody def getStatus() {
        "Hello World from groovy!"
    }



}
