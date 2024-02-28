package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoVO {
	private int mno;
	private String memo;
	private String phone;
	private String pw;
	private String secret;

}
