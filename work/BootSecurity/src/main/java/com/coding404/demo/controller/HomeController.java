package com.coding404.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coding404.demo.command.MemberVO;
import com.coding404.demo.member.MemberMapper;
import com.coding404.demo.security.MyUserDetails;

@Controller
public class HomeController {
	
	// 비밀번호 암호화 객체
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	MemberMapper memberMapper;
	
	@GetMapping("/hello")
	public String hello(Authentication auth) {
		
		if(auth != null) { // null이 아니면 인증객체가 존재함
			MyUserDetails details = (MyUserDetails)auth.getPrincipal();
			String username = details.getUsername();
			String password = details.getPassword();
			String role = details.getRole();
			
			System.out.println("컽느롤러 시큐리티인증객체정보" + username + ", " + password + ", " + role);
			
			
		}
		
		return "hello";
	}
	
	@GetMapping("/all")
	public String all() {
		return "all";
	}
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	// 시큐리티가 처음 적용되면, 모든 페이지는 시큐리티가 요청을 가로채서 login 페이지로 연결을 한다.
	// login페이지는 시큐리티가 제공해주는 기본 페이지로 사용되기 때문에, 별도의 설정으로 지정을 해줘야 함
	@GetMapping("/login")
	public String login(@RequestParam(value = "err", required = false) String err,
						Model model) {
		
		// failuerURL을 등록하면, 다시 컨트롤러를 태워서 처리가 가능합니다.
		if(err != null) {
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인하세요");
		}
		
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}
	
	@GetMapping("/user/mypage")
	public @ResponseBody String mypage() {
		return "REST 방식의 유저페이지";
	}
	
	@GetMapping("/admin/mypage")
	public @ResponseBody String admin() {
		return "REST 방식의 어드민페이지";
	}
	
	// 회원가입기능
	@PostMapping("/joinForm")
	public String joinForm(MemberVO vo) {
		
		String pw = bCryptPasswordEncoder.encode( vo.getPassword() );
		vo.setPassword(pw); // 암호화된 비밀번호로 처리
		// 서비스영역 생략
		// mapper로 인서트,
		memberMapper.join(vo);
		
		return "redirect:/login"; // 가입 후에는 로그인화면으로
	}
	
	// 메서드 방식으로 권한을 추가
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/test")
	public @ResponseBody String test() {
		return "ADMIN권한이 있어야 접근이 가능한 페이지~";
	}
	
	// 접근 권한 없음 핸들러
	@GetMapping("/deny")
	public @ResponseBody String deny() {
		return "응 너 권한 없어~ 수고 ㅋㅋ";
	}
	
	
	
	
	
	
	
	
	

}
