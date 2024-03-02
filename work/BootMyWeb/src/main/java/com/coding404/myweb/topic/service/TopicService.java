package com.coding404.myweb.topic.service;

import java.util.ArrayList;

import com.coding404.myweb.command.TopicVO;

public interface TopicService {
	
	public int regist(TopicVO vo); // insert(글 등록)
	public ArrayList<TopicVO> getList(); // 전체 글 list
	
	public ArrayList<TopicVO> searchTitle(String title); // 글 검색
	public ArrayList<TopicVO> searchId(String id); // 이름 검색
	
	public TopicVO getDetail(String id); // 상세페이지
	

}
