package org.zhy.java.spring4.chapter5.code507;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller//声明一个控制器
@RequestMapping({"/","/homepage"})//映射到类级别上
public class HomeController {
	@RequestMapping(method=RequestMethod.GET)//处理对“/”的GET请求
	public String home(){
		return "home";//视图名为home
	}
}
