package com.coding404.myweb.util;

import lombok.Data;

@Data
public class Criteria {
	
	private int page; // 페이지 번호
	private int amount; // 데이터 개수
	///////////////////////////////////////
	// 검색에 필요한 키워드 추가
	private String searchName; // 상품 이름
	private String searchContent; // 상품 내용
	private String searchPrice; // 상품가격 정렬방식
	private String startDate;
	private String endDate;
	/////////////////////////////////////////
	
	
	
	public Criteria() {
		this.page = 1;
		this.amount = 10;
	}
	
	public Criteria(int page, int amount) {
		super();
		this.page = page;
		this.amount = amount;
	}
	
	// sql에 전달될 페이지 start
	// page가 1이면 0 ~ 10, 2면 10 ~ 20, 3이면 20 ~ 30인듯? 확실하진 않음
	public int getPageStart() {
		return (page - 1) * amount;
		
	}
	
	

}
