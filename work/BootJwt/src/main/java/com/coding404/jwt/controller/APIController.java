package com.coding404.jwt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coding404.jwt.command.MemberVO;
import com.coding404.jwt.util.JWTService;

@RestController
public class APIController {
	
	//JWT - API기반의 인증정보를 처리할 때, 토큰을 발급(발행자)
//	@PostMapping("/login")
//	public ResponseEntity<Object> login(@RequestBody MemberVO vo) {
//		
//		// 사용자 정보를 받아서 ~~~ 로그인 처리~~~~
//		// ok - 로그인 성공했으면
//		// 토큰을 발행함
//		String token = JWTService.createToken(vo.getUsername());
//		
//		return new ResponseEntity<>(token , HttpStatus.OK);
//	}
	
//	@PostMapping("/api/v1/getInfo")
//	public ResponseEntity<Object> getInfo(HttpServletRequest request) {
//		
//		// 클래이언트에서 토큰을 header라는 곳에 담아 보냄
//		// 토큰을 전달받아서, 유효성을 확인한 후에, 만료인지, 통과인지 확인
//		String token = request.getHeader("Authorization");
//		System.out.println("전달된 토큰: " + token);
//		
//		// 토큰이 유효한가?
//		try {
//			boolean result = JWTService.validateToken(token);
//			System.out.println("토큰의 무결성(위조/변경) 여부: " + result);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>("토큰위조", HttpStatus.UNAUTHORIZED); // 토큰 위조
//		}
//
//		return new ResponseEntity<>("통과된 사람이면 여기에 이제 회원정보를 발급", HttpStatus.OK);
//
//	}
	
	
	
	
	
	
	
	
	

}
