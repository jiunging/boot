package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter, setter, toString 오버라이딩 자동생성
@AllArgsConstructor // 멤버변수를 받는 생성자 자동생성
@NoArgsConstructor // 기본생성자
@Builder
public class SimpleVO {
	private String id;
	private int age;
	private String email;
	private String address;

}
