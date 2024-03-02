package com.coding404.myweb.topic.service;

import org.apache.ibatis.annotations.Mapper;

import com.coding404.myweb.command.TopicVO;

@Mapper
public interface TopicMapper {
	
	public int regist(TopicVO vo); // insert(글 등록)
	
}
