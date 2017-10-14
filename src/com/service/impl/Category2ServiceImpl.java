package com.service.impl;

import java.util.List;

import com.dao.impl.Category2DaoImpl;
import com.dao.inter.Category2Dao;
import com.page.PageInfo;
import com.service.inter.Category2Service;
import com.vo.Category;
import com.vo.Category2;

public class Category2ServiceImpl implements Category2Service {

	private Category2Dao dao;

	public Category2ServiceImpl() {
		dao = new Category2DaoImpl();
	}

	@Override
	public int addCategory2(Category2 category) throws Exception {

		int count = dao.addCategory2(category);
		return count;
	}

	// 查询一级商品种类下的二级商品种类数量
	@Override
	public int getCategory2SumByCategory1(int fid) throws Exception {

		int count = 0;
		String sql = "select count(*) from category2 where fid=" + fid;
		count = dao.getTotalRecordCount2(sql);
		return count;
	}

	// 查询总共有多少条记录
	public int getTotalRecordCount2() throws Exception {
		int totalRecordCount = -1;

		String sql = "select count(*) from category2";
		totalRecordCount = dao.getTotalRecordCount2(sql);
		return totalRecordCount;
	}

	@Override
	public List<Category2> getAllCategorys2() throws Exception {
		List<Category2> list = null;

		String sql = "select * from category2";
		list = dao.getPageByQuery(sql);
		return list;
	}

	// 查询所有后分页
	public List<Category2> getAllByPage(PageInfo pageInfo) throws Exception {

		String sql = "SELECT * FROM (SELECT t.*,ROWNUM r FROM (SELECT * FROM category2 s INNER JOIN CATEGORY f ON(s.fid=f.cid) WHERE 1=1) t)  WHERE r>="
				+ pageInfo.getBegin() + " and r<=" + pageInfo.getEnd();
		System.out.println(sql);

		List<Category2> list = null;

		list = dao.getPageByQuery(sql);
		return list;
	}

	// 根据条件查询 然后分页
	public List<Category2> getPageByQuery(Category2 category, PageInfo pageInfo)
			throws Exception {

		List<Category2> list = null;

		// String sql =
		// "SELECT * FROM
		// (SELECT t.*,ROWNUM r FROM (SELECT * FROM category2 s INNER JOIN
		// CATEGORY f ON(s.fid=f.cid) WHERE 1=1) t
		// WHERE t.cname2='手机' OR t.cdesc2 LIKE '%111%' OR t.cname='手机数码') WHERE
		// r>=6 AND r<=10"

		StringBuffer sb = new StringBuffer(
				"SELECT * FROM (SELECT t.*,ROWNUM r FROM (SELECT * FROM category2 s INNER JOIN CATEGORY f ON(s.fid=f.cid) WHERE 1=1) t");

		String cname2 = category.getCname2();
		String cdesc2 = category.getCdesc2();
		String cname = category.getCategory().getCname();

		if (cname2 != null && !cname2.trim().equals("")) {

			sb.append(" WHERE t.cname2='");
			sb.append(cname2);
			sb.append("'");
		}

		if (cdesc2 != null && !cdesc2.trim().equals("")) {
			sb.append(" OR t.cdesc2 LIKE '%");
			sb.append(cdesc2);
			sb.append("%'");
		}

		if (cname != null && !cname.trim().equals("")) {
			sb.append(" OR t.cname='");
			sb.append(cname);
			sb.append("'");
		}

		sb.append(") where r>=");
		sb.append(pageInfo.getBegin());
		sb.append(" and r<=");
		sb.append(pageInfo.getEnd());

		String sql = sb.toString();
		System.out.println(sql);

		list = dao.getPageByQuery(sql);
		return list;
	}

	// 根据条件查询 总共有多少条记录
	public int getTotalRecordCount2(Category2 category) throws Exception {

		int totalRecordCount = -1;

		StringBuffer sb = new StringBuffer(
				"select count(*) from category2 where 1=1");

		String cname2 = category.getCname2();
		String cdesc2 = category.getCdesc2();

		if (cname2 != null && !cname2.trim().equals("")) {

			sb.append(" and cname2='");
			sb.append(cname2);
			sb.append("'");
		}

		if (cdesc2 != null && !cdesc2.trim().equals("")) {
			sb.append(" or cdesc2 like '%");
			sb.append(cdesc2);
			sb.append("%'");
		}

		String sql = sb.toString();
		totalRecordCount = dao.getTotalRecordCount2(sql);
		return totalRecordCount;
	}

	// 删除单个一级商品种类
	public int deleteCategory2ById(String cid) throws Exception {

		Category2 category = new Category2();
		category.setCid(Integer.parseInt(cid));
		int count = dao.deleteCategory2(category);
		return count;
	}

	// 查1
	public Category2 getCategory2ById(String cid) throws Exception {

		Category2 category = null;

		category = dao.getCategory2ById(Integer.parseInt(cid));
		return category;
	}

	// 修改
	public int updateCategory2(Category2 category) throws Exception {
		int count = dao.updateCategory2(category);
		return count;
	}

	@Override
	public List<Category2> getAllCategory2ByCategory1(int cid) throws Exception {
		List<Category2> list = null;

		String sql = "select * from category2 where fid=" + cid;

		list = dao.getAllCategory2ByCategory1(sql);

		return list;
	}
}
