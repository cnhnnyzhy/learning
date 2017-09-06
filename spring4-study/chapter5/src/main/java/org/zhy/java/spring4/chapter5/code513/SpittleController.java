package org.zhy.java.spring4.chapter5.code513;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/spitter")
public class SpittleController {
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegistrationForm(){
		return "registerForm";//返回视图
	}
	
	
}
