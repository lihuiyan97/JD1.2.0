package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.service.impl.Category2ServiceImpl;
import com.service.impl.CategoryServiceImpl;
import com.service.impl.ProductServiceImpl;
import com.service.inter.Category2Service;
import com.service.inter.CategoryService;
import com.service.inter.ProductService;
import com.vo.Category;
import com.vo.Category2;
import com.vo.Product;

public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// �ַ�����
		String action = request.getParameter("action");

		if ("getAllCategoryForAddProduct".equals(action)) {
			this.getAllCategoryForAddProduct(request, response);
		}else if("getCategory2ByCategory1ForAddProduct".equals(action)){
			this.getCategory2ByCategory1ForAddProduct(request,response);
		}else if("add".equals(action)){
			this.add(request,response);
		}
	}
	
	public void getAllCategoryForAddProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = "";
		
		CategoryService service = new CategoryServiceImpl();
		
		try {
			List<Category> list = service.getAllCategorys();
			
			request.setAttribute("list", list);
			
			target = "/WEB-INF/jsp/admin/product/addProduct.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", "��ѯһ����Ʒ����ʧ��!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		//3.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	public void getCategory2ByCategory1ForAddProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//���÷�������Ӧ����������  Ϊapplication/json 
		response.setContentType("application/json");
		String target = "";
		
		//1.�������
		String category1Id = request.getParameter("category1Id");
		
		//2.����ҵ���߼�
		
		Category2Service service = new Category2ServiceImpl();
		
		//3.���
		PrintWriter out = response.getWriter();
		
		try {
			List<Category2> list = service.getAllCategory2ByCategory1(Integer.parseInt(category1Id));
			
			Gson gson = new Gson();
			
			String category2ListJson = gson.toJson(list);
			
			out.println(category2ListJson);
			
			out.flush();
			
			out.close();
		
		} catch (Exception e) {
			request.setAttribute("msg", "��ѯ������Ʒ����ʧ��!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
			//3.ת����ͼ
			request.getRequestDispatcher(target).forward(request, response);
		}
		
	}
	
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = "";
		//һ.�������
		String pname = request.getParameter("pname");
		String price = request.getParameter("price");
		String productSum = request.getParameter("productSum");
		String dianpuName = request.getParameter("dianpuName");
		String pdesc = request.getParameter("pdesc");
		String cid = request.getParameter("cid");
		
		Product product = new Product();
		product.setPname(pname);
		product.setPrice(Double.parseDouble(price));
		product.setProductSum(Integer.parseInt(productSum));
		product.setDianpuName(dianpuName);
		product.setPdesc(pdesc);
		product.setCid(Integer.parseInt(cid));
		//��.����ҵ���߼�
		
		ProductService service = new ProductServiceImpl();
		try {
			service.addProduct(product);
			request.setAttribute("msg", "�����Ʒ�ɹ�");
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
		}
		//��.ת����ͼ
		target = "/WEB-INF/msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(target);
		
		rd.forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
