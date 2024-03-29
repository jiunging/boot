package com.simple.basic.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple.basic.command.SimpleVO;

@Controller
@RequestMapping("/view")
public class ThymeleafController {
	
//	@RequestMapping(value="/ex01", method = RequestMethod.POST)
//	@PostMapping("/ex01") // post만 허용
	@GetMapping("/ex01")
	public String ex01() {
		return "view/ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02(Model model) {
		ArrayList<SimpleVO> list = new ArrayList<>();
		for(int i = 1; i <= 10; i++) {
			SimpleVO vo = SimpleVO.builder()
					.age(i)
					.id("abc" + i)
					.email("abc@naver.com" + i)
					.address("서울시" + i)
					.build();
			list.add(vo);
		}
		model.addAttribute("list", list); // 데이터 담기
		return "view/ex02";
	}
	
	@GetMapping("/ex03")
	public String ex03(Model model) {
		ArrayList<SimpleVO> list = new ArrayList<>();
		for(int i = 1; i <= 10; i++) {
			SimpleVO vo = SimpleVO.builder()
					.age(i)
					.id("abc" + i)
					.email("abc@naver.com" + i)
					.address("서울시" + i)
					.build();
			list.add(vo);
		}
		model.addAttribute("list", list); // 데이터 담기
		return "view/ex03";
	}
	
	// 쿼리스트링 방식
	@GetMapping("/ex03_result")
	public String ex03_result(@RequestParam("id") String id,
							  @RequestParam("age") int age,
							  Model model) {
		
		System.out.println("쿼리스트링으로 넘어온 값: " + id + ", " + age);
		model.addAttribute("id", id);
		model.addAttribute("age", age);
		
		return "view/ex03_result";
	}
	
	// 쿼리파라미터 방식
	@GetMapping("/ex03_result2/{a}/{b}")
	public String ex03_result2(@PathVariable("a") String a,
							   @PathVariable("b") String b) {
		
		System.out.println("쿼리파라미터로 넘어온 값: " + a + ", " + b);
		
		return "view/ex03_result";
	}
	
	// ex04
	@GetMapping("/ex04")
	public String ex04(Model model) {
		model.addAttribute("name", "홍길동");
		model.addAttribute("vo", new SimpleVO("홍길동", 1, "naver.com", "서울"));
		
		model.addAttribute("time", new Date() );
		model.addAttribute("datetime", LocalDateTime.now() ); // 로컬날짜객체
		
		return "view/ex04";
	}
	
	///////////////////////
	// 타임리프 인클루드
	@GetMapping("/ex05")
	public String ex05() {
		return "view/ex05";
	}
	
	@GetMapping("/ex06")
	public String ex06() {
		return "view/ex06";
	}
	
	
	
	
	
	
	

}
