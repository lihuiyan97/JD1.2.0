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

	// 组合Connection对象
	private Connection conn;

	public ProductDaoImpl() {
		conn = ConnOracle.getConnection();
	}

	
	@Override
	public int addProduct(Product product) throws Exception{
		//刚刚插入的商品处于"下架"状态 所以onsale 的值为0    1 表示上架   0 表示下架
		String sql = "insert into product(pid,pname,price,pingjiasum,productSum,dianpuName,pdesc,onsale,cid) values(seq_product.nextval,?,?,0,?,?,?,0,?)";
		PreparedStatement pstmt = null;
		// 三.建立通道
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getPname());
			pstmt.setDouble(2, product.getPrice());
			pstmt.setInt(3, product.getProductSum());
			pstmt.setString(4, product.getDianpuName());
			pstmt.setString(5, product.getPdesc());
			pstmt.setInt(6, product.getCid());
			// 四.执行并返回结果集
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("建立通道失败");
			e.printStackTrace();
			throw new Exception("添加商品失败");
		} finally {
			// 五.关闭
			ConnOracle.closeConnection(null, pstmt, conn);

		}
		
		return count;

	}

	@Override
	public int deleteProduct(Product product) throws Exception{
		String sql = "delete from product where cid=?";
		PreparedStatement pstmt = null;
		// 三.建立通道
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product.getCid());

			// 四.执行并返回结果集
			count = pstmt.executeUpdate();// 执行dml 或 ddl语句的 返回受影响的行数
			

		} catch (SQLException e) {
			System.out.println("建立通道失败");
			e.printStackTrace();
			throw new Exception("删除商品失败!");
		} finally {
			// 五.关闭
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	// 三.修改
	public int updateProduct(Product product) throws Exception {
		String sql = "update product set cname=?,cdesc=? where cid=?";
		PreparedStatement pstmt = null;
		// 三.建立通道
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getPname());
			pstmt.setString(2, product.getPdesc());
			pstmt.setInt(3, product.getCid());

			// 四.执行并返回结果集
			count = pstmt.executeUpdate();// 执行dml 或 ddl语句的 返回受影响的行数
			
		} catch (SQLException e) {
			System.out.println("建立通道失败");
			e.printStackTrace();
			throw new Exception("修改商品失败!");
		} finally {
			// 五.关闭
			ConnOracle.closeConnection(null, pstmt, conn);

		}

		return count;
	}

	// 四.查1
	public Product getProductById(Integer pid) throws Exception{
		Product product = new Product();

		String sql = "select * from product where cid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 三.建立通道
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);

			// 四.执行并返回结果集
			rs = pstmt.executeQuery();
			while (rs.next()) {
				product.setPid(rs.getInt(1));
				product.setPname(rs.getString(2));
				product.setPdesc(rs.getString(3));
			}

		} catch (SQLException e) {
			System.out.println("建立通道失败");
			e.printStackTrace();
			throw new Exception("查询单一商品失败!");
		} finally {
			// 五.关闭
			ConnOracle.closeConnection(rs, pstmt, conn);

		}

		return product;
	}

	// 五.按SQL语句查
	public List<Product> getPageByQuery(String sql) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;

		List<Product> list = new ArrayList<Product>();

		Product product = null;

		// 三.建立通道
		try {
			stmt = conn.createStatement();
			// 四.执行并返回结果集
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				product = new Product();

				product.setCid(rs.getInt(1));
				product.setPname(rs.getString(2));
				product.setPdesc(rs.getString(3));

				list.add(product);
			}

		} catch (SQLException e) {
			System.out.println("建立通道失败!");
			e.printStackTrace();
			throw new Exception("查询商品失败!");
		} finally {
			// 五.关闭
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
			System.out.println("建立通道失败");
			e.printStackTrace();
			throw new Exception("查询商品数量失败!");
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
		p.setDianpuName("京东Apple产品专营店");
		p.setPdesc("苹果最新手机");
		p.setCid(62);
		ProductDaoImpl dao = new ProductDaoImpl();
		dao.addProduct(p);
	}


	

}
