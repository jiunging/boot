package com.simple.basic.test01;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.simple.basic.command.MemoVO;
import com.simple.basic.command.UsersVO;

@Mapper
public interface TestMapper {
	public String getTime();
	public ArrayList<MemoVO> joinOne(); // N : 1(메모임)
	public UsersVO joinTwo(); // 1 : N
	
}
