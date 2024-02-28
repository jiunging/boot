package com.simple.basic.mapper;

import java.util.ArrayList;

import com.simple.basic.command.MemoVO;

public interface MemoService {
	public int insertMemo(MemoVO vo);
	public ArrayList<MemoVO> selectMemo();
}
