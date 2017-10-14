package com.dao.inter;

import java.util.List;

import com.vo.Category;
import com.vo.Category2;

public interface Category2Dao {

	//1.添加
	public int addCategory2(Category2 category) throws Exception;
	
	//2.根据sql查询 总共有多少条记录
	public int getTotalRecordCount2(String sql) throws Exception;
	
	//3.按SQL语句查
	public List<Category2> getPageByQuery(String sql) throws Exception;
	
	//4.删除
	public int deleteCategory2(Category2 category) throws Exception;
	
	//5.查1
	public Category2 getCategory2ById(int id) throws Exception;
			
	//6.修改
	public int updateCategory2(Category2 category) throws Exception;
	
	//7. 根据 一级商品种类 查找二级商品
	public List<Category2> getAllCategory2ByCategory1(String sql) throws Exception;
}
