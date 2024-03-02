package com.coding404.myweb.topic.service;

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
	
}
