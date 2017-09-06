package org.zhy.java.spring4.chapter5.code509;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhy.java.spring4.chapter5.code508.Spittle;
import org.zhy.java.spring4.chapter5.code508.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
	private SpittleRepository spittleRepository;
	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String spittles(Model model){
		//将spittle添加到model中
		model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
		return "spittles";//返回视图
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String spittles2(Map<String, Object> model){
		//将spittle添加到model中
		model.put("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
		return "spittles";//返回视图
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Spittle> spittles3(@RequestParam(value="max", defaultValue=Long.MAX_VALUE + "") long max, @RequestParam(value="count", defaultValue="20") int count){
		//Spring会默认将结果添加到model中，key为“spittleList”
		return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
	}
	
	@RequestMapping(value="/show", method=RequestMethod.GET)
	public String showSpittle(@RequestParam("spittle_id") long spittleId, Model model){
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}
}
