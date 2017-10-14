package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dao.inter.Category2Dao;
import com.util.ConnOracle;
import com.vo.Category;
import com.vo.Category2;

public class Category2DaoImpl implements Category2Dao {

	private Connection conn;

	public Category2DaoImpl() {

		conn = ConnOracle.getConnection();
	}

	@Override
	public int addCategory2(Category2 category) throws Exception {
		int count = 0;
		// 3.建立通道
		String sql = "insert into category2 values(seq_category2.nextval,?,?,?)";

		PreparedStatement pstmt = null;

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, category.getCname2());
			pstmt.setString(2, category.getCdesc2());
			pstmt.setInt(3, category.getFid());
			// 4.执行并返回结果集
			count = pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("建立通道或添加商品种类失败");
			e.printStackTrace();

			throw new Exception("二级商品种类添加失败");
		} finally {
			// 5.关闭
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public int getTotalRecordCount2(String sql) throws Exception {
		int totalRecordCount = -1;

		ResultSet rs = null;
		Statement stmt = null;
		// 3.建立通道
		try {
			stmt = conn.createStatement();
			// 4.执行并返回结果集
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				totalRecordCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("创建通道或查询结果集失败");
			e.printStackTrace();

			throw new Exception("查询总共有多少条记录失败!");

		} finally {
			// 5.关闭
			ConnOracle.closeConnection(rs, stmt, conn);
		}

		return totalRecordCount;
	}

	@Override
	public List<Category2> getPageByQuery(String sql) throws Exception {

		List<Category2> list = new ArrayList<Category2>();
		Category2 category2 = null;
		Category category = null;
		ResultSet rs = null;
		Statement stmt = null;
		// 3.建立通道
		try {
			stmt = conn.createStatement();
			// 4.执行并返回结果集
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				category2 = new Category2();
				category = new Category();

				category2.setCid(rs.getInt("cid"));
				category2.setCname2(rs.getString("cname2"));
				category2.setCdesc2(rs.getString("cdesc2"));
				category2.setFid(rs.getInt("fid"));
				
				category.setCid(rs.getInt("cid"));
				category.setCname(rs.getString("cname"));
				category.setCdesc(rs.getString("cdesc"));
				
				category2.setCategory(category);
				
				list.add(category2);

			}
		} catch (SQLException e) {
			System.out.println("创建通道或查询结果集失败");
			e.printStackTrace();

			throw new Exception("查询一级商品种类失败!");

		} finally {
			// 5.关闭
			ConnOracle.closeConnection(rs, stmt, conn);
		}

		return list;
	}

	@Override
	public int deleteCategory2(Category2 category) throws Exception {
		int count = 0;
		// 3.建立通道
		String sql = "delete from category2 where cid=?";
		// 获得了一个预编译的通道 相当于IO通道 可以用它来发送sql语句
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, category.getCid());

			// 4.执行并返回结果集
			count = pstmt.executeUpdate();// 可以执行除了DQL以外所有的语句 DML 返回的是受影响的行数
											// DCL或DDL语句 返回值是0

			if (count >= 1) {
				System.out.println("删除商品种类成功!");
			} else {
				System.out.println("没有删除任何商品种类!");
			}
		} catch (SQLException e) {
			System.out.println("建立通道或删除商品种类失败");
			e.printStackTrace();
			throw new Exception("删除二级商品种类失败!");
		} finally {
			// 5.关闭
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public int updateCategory2(Category2 category) throws Exception {
		int count = 0;
		// 3.建立通道
		String sql = "update category2 set cname2=?,cdesc2=? where cid=?";
		// 获得了一个预编译的通道 相当于IO通道 可以用它来发送sql语句
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, category.getCname2());
			pstmt.setString(2, category.getCdesc2());
			pstmt.setInt(3, category.getCid());
			// 4.执行并返回结果集
			count = pstmt.executeUpdate();// 可以执行除了DQL以外所有的语句 DML 返回的是受影响的行数
											// DCL或DDL语句 返回值是0

			if (count >= 1) {
				System.out.println("修改商品种类成功!");
			} else {
				System.out.println("没有修改任何商品种类!");
			}
		} catch (SQLException e) {
			System.out.println("建立通道或修改商品种类失败");
			e.printStackTrace();
			throw new Exception("修改二级商品种类失败!");
		} finally {
			// 5.关闭
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public Category2 getCategory2ById(int id) throws Exception {
		Category2 category = new Category2();

		// 3.建立通道
		String sql = "select * from category2 where cid=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);
			// 4.执行并返回结果集
			rs = pstmt.executeQuery();

			while (rs.next()) {
				category.setCid(rs.getInt("cid"));
				category.setCname2(rs.getString("cname2"));
				category.setCdesc2(rs.getString("cdesc2"));
			}
		} catch (SQLException e) {
			System.out.println("建立通道或查询单个商品种类失败");
			e.printStackTrace();
			throw new Exception("查询单个二级商品种类失败");
		} finally {
			// 5.关闭
			ConnOracle.closeConnection(rs, pstmt, conn);
		}

		return category;
	}

	@Override
	public List<Category2> getAllCategory2ByCategory1(String sql)
			throws Exception {
		List<Category2> list = new ArrayList<Category2>();

		ResultSet rs = null;
		Statement stmt = null;

		Category2 category = null;

		// 3.建立通道
		try {
			stmt = conn.createStatement();
			// 4.执行并返回结果集
			rs = stmt.executeQuery(sql);
			System.out.println(sql);
			while (rs.next()) {
				category = new Category2();
				category.setCid(rs.getInt("cid"));
				category.setCname2(rs.getString("cname2"));
				category.setCdesc2(rs.getString("cdesc2"));
				category.setFid(rs.getInt("fid"));
				list.add(category);
			}
		} catch (SQLException e) {
			System.out.println("创建通道或查询结果集失败");
			e.printStackTrace();

			throw new Exception("查询总共有多少条记录失败!");

		} finally {
			// 5.关闭
			ConnOracle.closeConnection(rs, stmt, conn);
		}

		return list;
	}
}
