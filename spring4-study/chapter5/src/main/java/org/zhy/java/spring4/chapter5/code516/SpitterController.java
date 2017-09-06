package org.zhy.java.spring4.chapter5.code516;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
	
	private SpitterRepository spitterRepository;
	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		this.spitterRepository = spitterRepository;
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegistrationForm(){
		return "registerForm";//返回视图
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String processRegisration(Spitter spitter){
		spitterRepository.save(spitter);
		return "redirect:/spitter/" + spitter.getUserName();//返回视图
	}
	
	
}
