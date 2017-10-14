package com.dao.inter;

import java.util.List;

import com.vo.Category;
import com.vo.Category2;

public interface Category2Dao {

	//1.���
	public int addCategory2(Category2 category) throws Exception;
	
	//2.����sql��ѯ �ܹ��ж�������¼
	public int getTotalRecordCount2(String sql) throws Exception;
	
	//3.��SQL����
	public List<Category2> getPageByQuery(String sql) throws Exception;
	
	//4.ɾ��
	public int deleteCategory2(Category2 category) throws Exception;
	
	//5.��1
	public Category2 getCategory2ById(int id) throws Exception;
			
	//6.�޸�
	public int updateCategory2(Category2 category) throws Exception;
	
	//7. ���� һ����Ʒ���� ���Ҷ�����Ʒ
	public List<Category2> getAllCategory2ByCategory1(String sql) throws Exception;
}
