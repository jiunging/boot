package com.simple.basic.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	//@ResponseBody // 일반 컨트롤러에서 REST방식을 사용하려면
	public String home(Model model) {
		
		model.addAttribute("serverTime", new Date());
		model.addAttribute("model", "홍길동");
		
		return "hello";
	}

}
