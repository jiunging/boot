package com.coding404.myweb.controller;

import java.io.File;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.product.service.ProductService;

@RestController
public class AjaxController {
	
	@Autowired
	private ProductService productService;
	
	// 업로드 경로
	@Value("${project.upload.path}")
	private String uploadPath;
	
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
	
	@GetMapping("/display/{filepath}/{uuid}/{filename}")
	public ResponseEntity<byte[]> display(@PathVariable("filepath") String filepath,
										  @PathVariable("uuid") String uuid,
										  @PathVariable("filename") String filename) {
		
		ResponseEntity<byte[]> entity = null;
		
		try {
			// 로컬에 있는 파일데이터 byte 정보
			String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
			File file = new File(savePath);
			
			// 데이터
			byte[] arr = FileCopyUtils.copyToByteArray(file);
			// 헤더정보
			org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
			header.add("Content-type", Files.probeContentType(file.toPath()));
			entity = new ResponseEntity<>(arr, header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entity;
	}
	
	// 다운로드
	@GetMapping("/download/{filepath}/{uuid}/{filename}")
	public ResponseEntity<byte[]> download(@PathVariable("filepath") String filepath,
										  @PathVariable("uuid") String uuid,
										  @PathVariable("filename") String filename) {
		
		ResponseEntity<byte[]> entity = null;
		
		try {
			// 로컬에 있는 파일데이터 byte 정보
			String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
			File file = new File(savePath);
			
			// 데이터
			byte[] arr = FileCopyUtils.copyToByteArray(file);
			// 헤더정보
			org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
			
			
			// 다운로드는 다른점이, 이거 차이임
			// Content-Disposition: attachment; filename="filename.jpg"
			header.add("Content-Disposition", "attachment; filename=" + filename);
			
			entity = new ResponseEntity<>(arr, header, HttpStatus.OK);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entity;
	}
	
	

}
