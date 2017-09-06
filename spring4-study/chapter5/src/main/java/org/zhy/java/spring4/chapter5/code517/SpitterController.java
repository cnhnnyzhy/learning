package org.zhy.java.spring4.chapter5.code517;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zhy.java.spring4.chapter5.code516.Spitter;
import org.zhy.java.spring4.chapter5.code516.SpitterRepository;

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
	
	@RequestMapping(value="/{userName}", method=RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String userName, Model model){
		Spitter spitter = spitterRepository.findByUserName(userName);
		model.addAttribute(spitter);
		return "profile";//返回视图
	}
	
	
}
