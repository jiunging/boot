package com.coding404.myweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.product.service.ProductService;

@RestController
public class AjaxController {
	
	@Autowired
	private ProductService productService;
	
	// 등록화면에 대분류를 가져나가는 get데이터 조회
	@GetMapping("/getCategory")
	public ResponseEntity<ArrayList<CategoryVO>> getCategory() {
		// 이것 또한 restapi임
		return new ResponseEntity<>(productService.getCategory(), HttpStatus.OK);
	}
	
	// 중분류 or 소분류를 가져나가는 get데이터 조회
	@GetMapping("/getCategoryChild/{group_id}/{category_lv}/{category_detail_lv}")
	public ResponseEntity<ArrayList<CategoryVO>> getCategoryChild(@PathVariable("group_id") String group_id,
																  @PathVariable("category_lv") Integer category_lv,
																  @PathVariable("category_detail_lv") Integer category_detail_lv) {
		
		CategoryVO vo = CategoryVO.builder().group_id(group_id)
							.category_lv(category_lv)
							.category_detail_lv(category_detail_lv)
							.build();
		
		return new ResponseEntity<>(productService.getCategoryChild(vo), HttpStatus.OK);
	}
	
	

}
