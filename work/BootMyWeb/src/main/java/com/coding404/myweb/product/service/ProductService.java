package com.coding404.myweb.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

public interface ProductService {
	
	public int regist(ProductVO vo, List<MultipartFile> list); // insert 기능(vo, 파일데이터)
	public ArrayList<ProductVO> getList(Criteria cri, String user_id); // select 기능
	public int getTotal(Criteria cri, String user_id); // 전체게시글 수
	public ProductVO getDetail(int prod_id); // 상세보기페이지
	public int update(ProductVO vo); // update기능
	public void delete(int prod_id); // 삭제기능
	
	// 카테고리 관련
	public ArrayList<CategoryVO> getCategory(); // 첫 번째 카테고리
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo); // 두 번째 카테고리
	
	// 이미지 조회
	public ArrayList<ProductUploadVO> getImgs(int prod_id);
	
	
}
