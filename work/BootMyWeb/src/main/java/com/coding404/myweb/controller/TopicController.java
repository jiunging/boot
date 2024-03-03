package com.coding404.myweb.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.topic.service.TopicService;

@Controller
@RequestMapping("/practice1")
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@GetMapping("/topicDetail")
	public String detail(@RequestParam("topic_num") String topic_num,
						 Model model) {
		
		TopicVO vo = topicService.getDetail(topic_num);
		model.addAttribute("vo", vo);
		System.out.println(vo.toString());
		
		return "practice1/topicDetail";
	}
	
	@GetMapping("/topicListAll")
	public String listAll(Model model) {
		ArrayList<TopicVO> list = topicService.getList();
		model.addAttribute("list", list);
		
		
		return "practice1/topicListAll";
	}
	
	@GetMapping("/topicListMe")
	public String listMe(Model model) {
		ArrayList<TopicVO> list = topicService.getMyList();
		model.addAttribute("list", list);
		
		return "practice1/topicListMe";
	}
	
	@GetMapping("/topicModify")
	public String Modify(@RequestParam("myTopic_num") String myTopic_num,
						 Model model) {
		
		// 세부내역 재활용
		TopicVO vo = topicService.getDetail(myTopic_num);
		model.addAttribute("vo", vo);
		
		return "practice1/topicModify";
	}
	
	// 수정하기
	@PostMapping("/topicModifyForm")
	public String modifyForm(TopicVO vo, RedirectAttributes ra) {
		int result = topicService.update(vo);
		
		if(result == 1) {
			ra.addAttribute("msg", "등록 성공");
		} else {

			ra.addAttribute("msg", "등록 실패....");
		}
		
		
		return "redirect:/practice1/topicListMe";
	}
	
	// 삭제하기
	@GetMapping("/topicDeleteForm")
	public String deleteForm(@RequestParam("myTopic_num") int topic_num,
							RedirectAttributes ra) {
		int result = topicService.delete(topic_num);
		
		if(result == 1) {
			ra.addAttribute("msg", "삭제 성공");
		} else {
			ra.addAttribute("msg", "삭제 실패....");
		}
		
		return "redirect:/practice1/topicListMe";
	}
	
	@GetMapping("/topicReg")
	public String reg() {
		return "practice1/topicReg";
	}
	
	@PostMapping("/topicForm")
	public String regForm(TopicVO vo, RedirectAttributes ra) {
		System.out.println(vo);
		
		int result = topicService.regist(vo);
		
		if(result == 1) {
			ra.addAttribute("msg", "등록 성공");
		} else {
			ra.addAttribute("msg", "등록 실패....");
		}
		
		return "redirect:/practice1/topicListAll";
	}
	
	// 제목 검색 기능
	@GetMapping("/topicListTitleSearch")
	public String titleSearch(@RequestParam("title") String title,
							  @RequestParam("id") String id,
							  Model model) {
		System.out.println(title);
		System.out.println(id);
		
		if(title == "") {
			ArrayList<TopicVO> list = topicService.searchId(id);
			model.addAttribute("list", list);
		} else if(id == "") {
			ArrayList<TopicVO> list = topicService.searchTitle(title);
			model.addAttribute("list", list);
		} else {
			return "redirect:/practice1/topicListAll";
		}
		
		
		return "practice1/topicListAll";
	}
	
	
	
}
