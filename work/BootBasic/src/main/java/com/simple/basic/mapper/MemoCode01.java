package com.simple.basic.mapper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.basic.command.MemoVO;

@Service("MemoMapper")
public class MemoCode01 implements MemoMapper{
	@Autowired
	MemoMapper mapper;
	
	public int insertMemo(MemoVO vo) {
		
		System.out.println("Memovode01인데, vo가 도착하나?" + vo);

		int memo = mapper.insertMemo(vo);
		System.out.println(memo);
		

		
		return memo;
	}

	@Override
	public ArrayList<MemoVO> selectMemo() {
		System.out.println("select하는 중");
		
		return mapper.selectMemo();
	}
	

}
