package com.coding404.myweb.product.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

public interface ProductService {
	
	public int regist(ProductVO vo); // insert 기능
	public ArrayList<ProductVO> getList(Criteria cri); // select 기능
	public int getTotal(Criteria cri); // 전체게시글 수
	public ProductVO getDetail(int prod_id); // 상세보기페이지
	public int update(ProductVO vo); // update기능
	public void delete(int prod_id); // 삭제기능
	
}
