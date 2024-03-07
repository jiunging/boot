package com.simple.basic.controller;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.basic.command.SimpleVO;

@RestController // 합성어 = Controller + ResponseBody
public class RestBasicController {
	
	// 보내는 데이터에 대한 타입 명시 - produces(밑에 보면 text타입으로 보내겠다 이것임)
	// 생략하게 되면 자동으로 JSON타입이 적힘
	@GetMapping(value = "/aaa", produces = "text/plain")
	public String aaa() {
		return "야 이게 rest API냐?"; // 데이터를 담음 - 요청이 온 곳으로 돌아간다.
	}
	
	
	///////////////////////////////////////////////////////////
	// 데이터를 보내는 방법
	@GetMapping(value = "/bbb", produces = "application/json")
	public SimpleVO bbb() {
		return new SimpleVO("aaa123", 20, "naver", "서울시"); // 자동으로 JSON형식으로 변환을 해서 리턴
	}
	
	@GetMapping("/getObject")
	public Map<String, Object> getObject() {
		Map<String, Object> map = new HashMap<>();
		map.put("total", 1);
		map.put("data", new SimpleVO("bbb123", 30, "naver", "서울"));
		return map;
	}
	
	// 리스트 반환
	@GetMapping("/getObject2")
	public List<SimpleVO> getObject2() {
		List<SimpleVO> list = new ArrayList<>();
		list.add(new SimpleVO("bbb123", 30, "naver", "서울"));
		list.add(new SimpleVO("ccc123", 40, "naver", "부산"));
		return list;
	}
	
	////////////////////////////////////////////////////////////
	// 데이터를 받는 방법
	
	// get => 쿼리스트링 or URL파라미터를 사용해서 데이터를 넘겨준다.
	// getData?id=aaa123&age=20
	
//	@GetMapping("/getData")
//	public String getData(@RequestParam("id") String id,
//						  @RequestParam("age") int age) {
//		
//		System.out.println(id + ", " + age);
//		return "success";
//	}
	@CrossOrigin("*")
	@GetMapping("/getData")
	public String getData(SimpleVO vo) {
		
		System.out.println(vo);
		return "success";
	}
	
	// getData2/홍길동/1
	@GetMapping("/getData2/{name}/{age}")
	public String getData2( @PathVariable("name") String name,
							@PathVariable("age") int age) {
		
		System.out.println(name + ", " + age);
		return "success";
	}
	
	///////////////////////////////////////////////////////
	// POST방식으로 데이터를 받음
	// Form형식으로 데이터를 보내고, 받는 방법
	@PostMapping("/getForm")
	public String getForm(SimpleVO vo) {
		
		System.out.println(vo);
		return "success";
	}
	
	// JSON형식으로 데이터를 보내고, 받는 방법
	// { "id" : "홍길동", "age" : 20, "address" : "서울시"} 이런 식
	// JSON 데이터를 보내고, object형식으로 받으려면, @RequestBody가 필요함
//	@PostMapping("/getJSON")
//	public String getJSON(@RequestBody SimpleVO vo) {
//		System.out.println(vo);
//		
//		return "success";
//	}
	
//	// 위랑 똑같은건데, map만 바꿈
//	@PostMapping("/getJSON")
//	public String getJSON(@RequestBody Map<String, Object> map) {
//		
//		System.out.println(map);
//		
//		return "success";
//	}
	
	// CrossOrigin 사용 (얘도 허용하겠다 이건가..?)
//	@CrossOrigin("http://localhost:3000")
	@CrossOrigin("*")
	@PostMapping("/getJSON")
	public String getJSON(@RequestBody Map<String, Object> map) {
		
		System.out.println(map);
		
		return "success";
	}
	
	//////////////////////////////////////////////////////
	// consumes -> 반드시 이 타입으로 보내라(명시)
	// produces -> 내가 이 타입으로 줄게(명시)
	// 클라이언트에서 text를 보내면 실패한다.
	// consumes에서 json을 선언하면, 보낼 때도 json으로 보내야함
	@PostMapping(value = "/getResult", consumes = "application/json")
	public String getResult(@RequestBody String str) {
		
		System.out.println(str);
		return "success";
	}
	
	///////////////////////////////////////////////////////
	// 응답문서 상세하게 작성하기 ResponseEntity<보내는타입> 이게 근본이라는데
	@PostMapping("/createResponse")
	public ResponseEntity<SimpleVO> createResponse() {
		
		// 데이터
		SimpleVO vo = new SimpleVO("aaa123", 20, "naver", null);
		
		// 헤더
		org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
		header.add("Authorization", "my json web token"); // 권한 - 토큰
		header.add("Content-Type", "application/json"); // 내가 보내는 컨텐츠 타입
		header.add("Access-Control-Allow-Origin", "*"); // 서버가 다르더라고 요청을 허용함
		
		
		return new ResponseEntity<>(vo, header, HttpStatus.OK);
		
		
		
		// return new ResponseEntity<>(new SimpleVO("aaa123", 20, "naver", "asdf"), HttpStatus.OK);
	}
	
	

	

	
//	1.
//	클라이언트 fetch
//	요청주소 : /api/v1/getData
//	메서드 : get
//	클라이언트에서 보낼데이터는 : num, name
//	서버에서 보낼데이터는 : SimpleVO
//	받을 수 있는 restAPI를 생성, 클라이언트에서는 fetch로 호출
	
	@CrossOrigin("*")
	@GetMapping("/api/v1/getData")
	public ResponseEntity<SimpleVO> getData(@RequestParam("num") int num,
						  @RequestParam("name") String name) {
		
		System.out.println(num + "," + name);
		
		return new ResponseEntity<SimpleVO>(new SimpleVO("홍길동", 10, "naver", "서울시"), HttpStatus.OK);
	}
//  
//   2.
//	 클라이언트 fetch요청
//	 요청주소 : /api/v1/getInfo
//	 메서드 : post
//	 클라이언트에서 보낼데이터는 : num, name
//	 서버에서 보낼데이터는 : 리스트<SimpleVO>
//   받을 수 있는 restAPI를 생성, 클라이언트에서는 fetch로 호출
//	 ResponseEntity로 응답
	
	@PostMapping("/api/v1/getInfo")
	public ResponseEntity<List<SimpleVO>> getInfo(@RequestBody Map<String, Object> map) {
		
		List<SimpleVO> list = new ArrayList<>();
		list.add(new SimpleVO("csdaasd123", 1234567, "sadasdsa", "123"));
		list.add(new SimpleVO("aaa123", 123, "namvesdf", "123"));
		
		System.out.println(map);

		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	

	
	
}
