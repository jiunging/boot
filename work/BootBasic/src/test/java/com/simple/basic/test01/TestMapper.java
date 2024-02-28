package com.simple.basic.test01;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
	public String getTime();
}
