package com.coding404.myweb.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import lombok.Data;

@Data
public class PageVO {
	
	private int start; // 페이지네이션 시작번호
	private int end; // 페이지네이션 끝번호
	private boolean prev; // 이전 버튼 활성화 여부
	private boolean next; // 다음버튼 활성화 여부
	
	private int page; // 현재 조회하는 데이터 번호 (criteria가 가지고있음)
	private int amount; // 조회하는 데이터 개수 (criteria가 가지고있음)
	private int total; // 전체 데이터 개수
	
	private int realEnd; // 페이지 아예 마지막(진짜 끝번호)?
	
	private Criteria cri; // 페이지 기준
	private List<Integer> pageList; // 페이지 시작 ~ 끝 item
	
	// 생성자 - 계산에 필요함
	public PageVO(Criteria cri, int total) {
		// 생성될 때, 페이지 번호, 데이터 개수, 전체 게시글 수 초기값 지정
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
		this.cri = cri;
		
		// 끝페이지 계산 - 현재 조회하는 페이지에 따라 변함
		// ex) page가 11이면, 끝페이지 20
		// ex) page가 1이면, 끝페이지 10
		// ex) page 31이면, 끝페이지 40 이건 그냥 10씩 페이지 보여지는거임(글이 아님, 페이지임)
		this.end = (int)(Math.ceil( this.page / 10.0 )) * 10; // 페이지 개수 기준?
		
		// 시작페이지 계산
		// 끝페이지 - 페이지네이션 개수  + 1
		// 만약, 글 개수 210개면 this.end는 10이잖음 보통, 그럼 1개의 페이지만 남는다 이것임,
		// 20번째 페이지로 넘어갔을 때, 그 뒤로 보이는 페이지는 1개밖에 없지
		this.start = this.end - 10 + 1; 
		
		// 실제 마지막 페이지 재계산 - realEnd
		// 만약, 총 게시글이 53개라면? -> 끝페이지 계산시 10, 실제 끝번호는 6
		// 만약, 총 게시글 수가 163개라면? -> 끝페이지 계산시 20, 실제 끝번호 17
		// (int)올림(총 게시글 수 / 화면에 뿌려지는 데이터 개수)
		this.realEnd = (int)(Math.ceil(this.total / (double)this.amount));
		
		// 끝페이지 다시 결정
		// 데이터 개수 163개
		// 1~10페이지 조회 시에는, 끝페이지 번호 10, 진짜 끝번호는 17
		// 11~20페이지 조회 시에는, 끝페이지 번호 20, 진짜 끝번호는 17
		if(this.end > this.realEnd) {
			this.end = this.realEnd;
		}
		
		// 이전버튼 활성화 여부
		// start는 1, 11, 21, 31....
		this.prev = this.start > 1; // 1보다 크면 true
		
		// 다음버튼 활성화 여부
		this.next = this.realEnd > this.end;
		
		// 타임리프는 향상된 for문밖에 지원이 안 돼서, 페이지번호를 들고있는 item을 생성
		this.pageList = IntStream.rangeClosed(this.start, this.end)
						.boxed()
						.collect(Collectors.toList());
		
	}

}
