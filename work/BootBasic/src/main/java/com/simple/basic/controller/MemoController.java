package com.simple.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simple.basic.command.MemoVO;
import com.simple.basic.mapper.MemoCode01;

@Controller
@RequestMapping("/memo")
public class MemoController {

	@Autowired
	@Qualifier("MemoMapper")
	private MemoCode01 memoCode01;
	
	@GetMapping("/memoWrite")
	public String memoWrite() {
		
		return "memo/memoWrite";
	}
	
	@PostMapping("/memoForm")
	public String memoForm(MemoVO vo) {
		
		MemoVO vo1 = new MemoVO(vo.getMno(), vo.getMemo(), vo.getPhone(), vo.getPw(), vo.getSecret());
		System.out.println("엄");
		System.out.println("여기까지는?");
		memoCode01.insertMemo(vo1);
		
		System.out.println("일단 여기까지 도달");
		
		return "redirect:/memo/memoList";
	}
	
	
	@GetMapping("/memoList")
	public String memoList(Model model) {
		
		model.addAttribute("vo", memoCode01.selectMemo());
		
		System.out.println("컨트롤러 다시 도달");
		System.out.println(memoCode01.selectMemo());
		
		return "memo/memoList";
	}
	
	
}
