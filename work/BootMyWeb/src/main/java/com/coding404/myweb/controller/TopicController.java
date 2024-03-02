package com.coding404.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.topic.service.TopicService;

@Controller
@RequestMapping("/practice1")
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@GetMapping("/topicDetail")
	public String detail() {
		return "practice1/topicDetail";
	}
	
	@GetMapping("/topicListAll")
	public String listAll() {
		return "practice1/topicListAll";
	}
	
	@GetMapping("/topicListMe")
	public String listMe() {
		return "practice1/topicListMe";
	}
	
	@GetMapping("/topicModify")
	public String Modify() {
		return "practice1/topicModify";
	}
	
	@GetMapping("/topicReg")
	public String reg() {
		return "practice1/topicReg";
	}
	
	@PostMapping("/topicForm")
	public String regForm(TopicVO vo) {
		
		System.out.println(vo);
		
		int result = topicService.regist(vo);
		
		if(result == 1) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
		
		return "practice1/topicReg";
		
	}
	
}
