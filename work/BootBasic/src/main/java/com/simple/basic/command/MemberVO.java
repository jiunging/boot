package com.simple.basic.command;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
	
	@Pattern(regexp = "[a-zA-Z0-9]{8,}", message = "영문자, 숫자 합해서 8자리 이상이어야 합니다")
	private String id;
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-_+=]).{8,}$", message =  "영문자, 숫자 포함 8자리 이상이어야 합니다")
	private String pw;

}
