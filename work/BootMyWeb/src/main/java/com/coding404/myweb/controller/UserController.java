package com.coding404.myweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
	
	// 로그인 화면
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	// 가입
	@GetMapping("/join")
	public String join() {
		return "user/join";
	}
	
	// 유저 정보
	@GetMapping("/userDetail")
	public String userDetail() {
		return "user/userDetail";
	}
	
	// 로그인
	@PostMapping("/loginForm")
	public String loginForm(@RequestParam("id") String id,
							@RequestParam("pw") String pw,
							HttpSession session) {
		
		// 로그인 처리~ 생략
		// select * from users where id = ? and pw = ?
		// null이 아니라면, 로그인 성공
		session.setAttribute("user_id", "abc1234"); // abc1234라는 아이디를 저장
		
		
		
		return "redirect:/"; // 홈화면
	}
	
	

}
