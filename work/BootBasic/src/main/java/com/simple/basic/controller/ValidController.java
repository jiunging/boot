package com.simple.basic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simple.basic.command.MemberVO;
import com.simple.basic.command.ValidVO;

@Controller
@RequestMapping("/valid")
public class ValidController {
	@GetMapping("/ex01")
	public String ex01() {
		return "valid/ex01";
	}
	
	// REST API에서 클라이언트에서 데이터를 전달받을 때도 사용할 수 있음
	// 풀요청
	@PostMapping("validForm")
	public String validForm(@Valid ValidVO vo, Errors errors, Model model) {
		// 유효성 검사에 실패한 목록을 Error안에 바인딩
		if(errors.hasErrors()) { // error발생된 목록이 있다면 true
			List<FieldError> list = errors.getFieldErrors(); // 유효성 검사에 실패한 목록
			for(FieldError err : list) {
				String field = err.getField(); // 유효성 검사에 실패한 변수명
				String message = err.getDefaultMessage(); // 유효성 검사에 실패한 변수 메시지
				
//				System.out.println(field); // -> 오류가 나면 name, salary, phone, email 출력됨
//				System.out.println(message); // -> '이름은 필수입니다' 출력됨
				
				// 급여가 Integer 형태인데, string값을 집어넣으면, Failed to convert 오류가 뜸
				if(err.isBindingFailure()) { // 유효성 검사에 실패가 아니라, 자바코드에 에러인 경우 true
					model.addAttribute("valid_" + field, "숫자로 입력하세요");
				} else {
					model.addAttribute("valid_" + field, message); // valid_이름, 메시지
				}
				model.addAttribute("valid_" + field, message); // valid_이름, 메시지 반환
			}
			model.addAttribute("vo", vo);
			return "valid/ex01"; // 다시 원래화면
		}
		
		System.out.println(vo.toString());
		// 데이터베이스 처리
		return "valid/ex01_result";
	}
	
	@GetMapping("quiz01")
	public String quiz01() {
		return "valid/quiz01";
	}
	
	@PostMapping("quizForm")
	public String quizForm(@Valid MemberVO vo, Errors errors, Model model) {
		if(errors.hasErrors()) { // 에러가 있다면
			List<FieldError> list = errors.getFieldErrors(); 
			for(FieldError err : list) { 
				String field = err.getField();
				String message = err.getDefaultMessage();
				
				model.addAttribute("valid_" + field, message);
			}
			model.addAttribute("vo", vo);
			return "valid/quiz01";
		}
		
		return "valid/quiz01_result";
	}
	
	@GetMapping("quiz01_result")
	public String quiz01_result() {
		return "valid/quiz01_result";
	}
	
}
