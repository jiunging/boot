package com.coding404.myweb.topic.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding404.myweb.command.TopicVO;

@Service
public class TopicServiceImpl implements TopicService{
	
	@Autowired
	private TopicMapper topicMapper;

	@Override
	public int regist(TopicVO vo) {
		
		return topicMapper.regist(vo);
	}

	@Override
	public ArrayList<TopicVO> getList() {
		
		return topicMapper.getList();
	}

	@Override
	public ArrayList<TopicVO> searchTitle(String title) {
		
		return topicMapper.searchTitle(title);
	}

	@Override
	public ArrayList<TopicVO> searchId(String id) {
		
		return topicMapper.searchId(id);
	}

	@Override
	public TopicVO getDetail(String id) {
		
		return topicMapper.getDetail(id);
	}
	
}
