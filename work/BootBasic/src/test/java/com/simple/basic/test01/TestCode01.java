package com.simple.basic.test01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.simple.basic.command.BuilderVO;
import com.simple.basic.command.SimpleVO;


@SpringBootTest // 스프링부트 테스트
public class TestCode01 {
	
//	@Autowired
//	ApplicationContext context;
	
	@Test // 이 메서드를 실행함
	public void test01() {
		System.out.println("hello world");
//		System.out.println(context);
		
		BuilderVO vo = BuilderVO.builder().name("홍긷동").age(20).build();
		System.out.println(vo.toString());
		
//		SimpleVO vo2 = SimpleVO.builder()
//								.id("abc123")
//								.age(20)
//								.email("sdf")
//								.address("서울시")
//								.build();
//		System.out.println(vo2.toString());
		
	}
}
