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
		// 3.����ͨ��
		String sql = "insert into category2 values(seq_category2.nextval,?,?,?)";

		PreparedStatement pstmt = null;

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, category.getCname2());
			pstmt.setString(2, category.getCdesc2());
			pstmt.setInt(3, category.getFid());
			// 4.ִ�в����ؽ����
			count = pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("����ͨ���������Ʒ����ʧ��");
			e.printStackTrace();

			throw new Exception("������Ʒ�������ʧ��");
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public int getTotalRecordCount2(String sql) throws Exception {
		int totalRecordCount = -1;

		ResultSet rs = null;
		Statement stmt = null;
		// 3.����ͨ��
		try {
			stmt = conn.createStatement();
			// 4.ִ�в����ؽ����
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				totalRecordCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("����ͨ�����ѯ�����ʧ��");
			e.printStackTrace();

			throw new Exception("��ѯ�ܹ��ж�������¼ʧ��!");

		} finally {
			// 5.�ر�
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
		// 3.����ͨ��
		try {
			stmt = conn.createStatement();
			// 4.ִ�в����ؽ����
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
			System.out.println("����ͨ�����ѯ�����ʧ��");
			e.printStackTrace();

			throw new Exception("��ѯһ����Ʒ����ʧ��!");

		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(rs, stmt, conn);
		}

		return list;
	}

	@Override
	public int deleteCategory2(Category2 category) throws Exception {
		int count = 0;
		// 3.����ͨ��
		String sql = "delete from category2 where cid=?";
		// �����һ��Ԥ�����ͨ�� �൱��IOͨ�� ��������������sql���
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, category.getCid());

			// 4.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ����ִ�г���DQL�������е���� DML ���ص�����Ӱ�������
											// DCL��DDL��� ����ֵ��0

			if (count >= 1) {
				System.out.println("ɾ����Ʒ����ɹ�!");
			} else {
				System.out.println("û��ɾ���κ���Ʒ����!");
			}
		} catch (SQLException e) {
			System.out.println("����ͨ����ɾ����Ʒ����ʧ��");
			e.printStackTrace();
			throw new Exception("ɾ��������Ʒ����ʧ��!");
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public int updateCategory2(Category2 category) throws Exception {
		int count = 0;
		// 3.����ͨ��
		String sql = "update category2 set cname2=?,cdesc2=? where cid=?";
		// �����һ��Ԥ�����ͨ�� �൱��IOͨ�� ��������������sql���
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, category.getCname2());
			pstmt.setString(2, category.getCdesc2());
			pstmt.setInt(3, category.getCid());
			// 4.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ����ִ�г���DQL�������е���� DML ���ص�����Ӱ�������
											// DCL��DDL��� ����ֵ��0

			if (count >= 1) {
				System.out.println("�޸���Ʒ����ɹ�!");
			} else {
				System.out.println("û���޸��κ���Ʒ����!");
			}
		} catch (SQLException e) {
			System.out.println("����ͨ�����޸���Ʒ����ʧ��");
			e.printStackTrace();
			throw new Exception("�޸Ķ�����Ʒ����ʧ��!");
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public Category2 getCategory2ById(int id) throws Exception {
		Category2 category = new Category2();

		// 3.����ͨ��
		String sql = "select * from category2 where cid=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);
			// 4.ִ�в����ؽ����
			rs = pstmt.executeQuery();

			while (rs.next()) {
				category.setCid(rs.getInt("cid"));
				category.setCname2(rs.getString("cname2"));
				category.setCdesc2(rs.getString("cdesc2"));
			}
		} catch (SQLException e) {
			System.out.println("����ͨ�����ѯ������Ʒ����ʧ��");
			e.printStackTrace();
			throw new Exception("��ѯ����������Ʒ����ʧ��");
		} finally {
			// 5.�ر�
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

		// 3.����ͨ��
		try {
			stmt = conn.createStatement();
			// 4.ִ�в����ؽ����
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
			System.out.println("����ͨ�����ѯ�����ʧ��");
			e.printStackTrace();

			throw new Exception("��ѯ�ܹ��ж�������¼ʧ��!");

		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(rs, stmt, conn);
		}

		return list;
	}
}
