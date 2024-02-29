package com.coding404.myweb.product.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding404.myweb.command.ProductVO;

@Service("productService")
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public int regist(ProductVO vo) {
		
		return productMapper.regist(vo);
	}

	@Override
	public ArrayList<ProductVO> getList() {
		
		return productMapper.getList();
	}

	@Override
	public ProductVO getDetail(int prod_id) {
		
		return productMapper.getDetail(prod_id);
	}

	@Override
	public int update(ProductVO vo) {
		
		return productMapper.update(vo);
	}

	@Override
	public void delete(int prod_id) {
		productMapper.delete(prod_id);
	}





}
