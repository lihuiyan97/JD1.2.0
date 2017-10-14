package com.service.inter;

import java.util.List;

import com.page.PageInfo;
import com.vo.Category;
import com.vo.Category2;

public interface Category2Service {

	//���
	public int addCategory2(Category2 category) throws Exception;
	//��ѯһ����Ʒ�����µĶ�����Ʒ��������
	public int getCategory2SumByCategory1(int cid) throws Exception;
	//��ѯ�ܹ��ж�������¼
	public int getTotalRecordCount2() throws Exception;
	//������
	public List<Category2> getAllCategorys2() throws Exception;
	//��ѯ���к��ҳ
	public List<Category2> getAllByPage(PageInfo pageInfo) throws Exception;
	//����������ѯ �ܹ��ж�������¼
	public int getTotalRecordCount2(Category2 category) throws Exception;
	//����������ѯ Ȼ���ҳ
	public List<Category2> getPageByQuery(Category2 category,PageInfo pageInfo) throws Exception;
	//ɾ������������Ʒ����
	public int deleteCategory2ById(String cid) throws Exception;
	//��1
	public Category2 getCategory2ById(String cid) throws Exception;
	//�޸�
	public int updateCategory2(Category2 category) throws Exception;
	//����һ����Ʒ���� ��ѯ����Ķ�����Ʒ����
	public List<Category2> getAllCategory2ByCategory1(int cid) throws Exception;
}
