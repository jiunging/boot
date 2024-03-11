package com.coding404.myweb.product.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

@Mapper
public interface ProductMapper {
	public int regist(ProductVO vo); // insert (이건 interface랑 다름, interface에다가는 list도 넣었음(이미지 첨부)
	public ArrayList<ProductVO> getList(@Param("cri") Criteria cri, @Param("user_id") String user_id); // select 기능
	public int getTotal(@Param("cri") Criteria cri, @Param("user_id") String user_id); // 전체게시글 수
	public ProductVO getDetail(int prod_id); // 상세보기페이지
	public int update(ProductVO vo); // update기능
	public void delete(int prod_id); // 삭제기능
	
	// 카테고리 관련
	public ArrayList<CategoryVO> getCategory(); // 첫 번째 카테고리
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo); // 두 번째 카테고리

	// 파일업로드 insert
	public void registFile(ProductUploadVO vo);
	
	// 이미지 조회
	public ArrayList<ProductUploadVO> getImgs(int prod_id);

}
