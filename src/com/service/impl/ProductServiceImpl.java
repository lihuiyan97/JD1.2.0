package com.service.impl;

import com.dao.impl.ProductDaoImpl;
import com.dao.inter.ProductDao;
import com.service.inter.ProductService;
import com.vo.Product;

public class ProductServiceImpl implements ProductService{

	private ProductDao dao;
	
	public ProductServiceImpl(){
		
		dao = new ProductDaoImpl();	
	}
	
	public int addProduct(Product product) throws Exception{
		
		int count = dao.addProduct(product);
		return count;
	}
}
