package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dao.inter.ProductDao;
import com.util.ConnOracle;
import com.vo.Product;

public class ProductDaoImpl implements ProductDao {

	// ���Connection����
	private Connection conn;

	public ProductDaoImpl() {
		conn = ConnOracle.getConnection();
	}

	
	@Override
	public int addProduct(Product product) throws Exception{
		//�ող������Ʒ����"�¼�"״̬ ����onsale ��ֵΪ0    1 ��ʾ�ϼ�   0 ��ʾ�¼�
		String sql = "insert into product(pid,pname,price,pingjiasum,productSum,dianpuName,pdesc,onsale,cid) values(seq_product.nextval,?,?,0,?,?,?,0,?)";
		PreparedStatement pstmt = null;
		// ��.����ͨ��
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getPname());
			pstmt.setDouble(2, product.getPrice());
			pstmt.setInt(3, product.getProductSum());
			pstmt.setString(4, product.getDianpuName());
			pstmt.setString(5, product.getPdesc());
			pstmt.setInt(6, product.getCid());
			// ��.ִ�в����ؽ����
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
			throw new Exception("������Ʒʧ��");
		} finally {
			// ��.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);

		}
		
		return count;

	}

	@Override
	public int deleteProduct(Product product) throws Exception{
		String sql = "delete from product where cid=?";
		PreparedStatement pstmt = null;
		// ��.����ͨ��
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product.getCid());

			// ��.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ִ��dml �� ddl���� ������Ӱ�������
			

		} catch (SQLException e) {
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
			throw new Exception("ɾ����Ʒʧ��!");
		} finally {
			// ��.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	// ��.�޸�
	public int updateProduct(Product product) throws Exception {
		String sql = "update product set cname=?,cdesc=? where cid=?";
		PreparedStatement pstmt = null;
		// ��.����ͨ��
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getPname());
			pstmt.setString(2, product.getPdesc());
			pstmt.setInt(3, product.getCid());

			// ��.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ִ��dml �� ddl���� ������Ӱ�������
			
		} catch (SQLException e) {
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
			throw new Exception("�޸���Ʒʧ��!");
		} finally {
			// ��.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);

		}

		return count;
	}

	// ��.��1
	public Product getProductById(Integer pid) throws Exception{
		Product product = new Product();

		String sql = "select * from product where cid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ��.����ͨ��
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);

			// ��.ִ�в����ؽ����
			rs = pstmt.executeQuery();
			while (rs.next()) {
				product.setPid(rs.getInt(1));
				product.setPname(rs.getString(2));
				product.setPdesc(rs.getString(3));
			}

		} catch (SQLException e) {
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
			throw new Exception("��ѯ��һ��Ʒʧ��!");
		} finally {
			// ��.�ر�
			ConnOracle.closeConnection(rs, pstmt, conn);

		}

		return product;
	}

	// ��.��SQL����
	public List<Product> getPageByQuery(String sql) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;

		List<Product> list = new ArrayList<Product>();

		Product product = null;

		// ��.����ͨ��
		try {
			stmt = conn.createStatement();
			// ��.ִ�в����ؽ����
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				product = new Product();

				product.setCid(rs.getInt(1));
				product.setPname(rs.getString(2));
				product.setPdesc(rs.getString(3));

				list.add(product);
			}

		} catch (SQLException e) {
			System.out.println("����ͨ��ʧ��!");
			e.printStackTrace();
			throw new Exception("��ѯ��Ʒʧ��!");
		} finally {
			// ��.�ر�
			ConnOracle.closeConnection(rs, stmt, conn);
		}

		return list;
	}

	
	
	public int getTotalRecordSum(String sql) throws Exception{
		int totalRecordSum = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				totalRecordSum = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
			throw new Exception("��ѯ��Ʒ����ʧ��!");
		} finally {
		
			ConnOracle.closeConnection(rs, pstmt, conn);
		}

		return totalRecordSum;
	}
	
	public static void main(String[] args) throws Exception {
		Product p = new Product();
		p.setPname("iphone6s");
		p.setPrice(5288);
		p.setProductSum(10);
		p.setDianpuName("����Apple��ƷרӪ��");
		p.setPdesc("ƻ�������ֻ�");
		p.setCid(62);
		ProductDaoImpl dao = new ProductDaoImpl();
		dao.addProduct(p);
	}


	

}