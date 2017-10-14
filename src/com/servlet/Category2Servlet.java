package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.page.PageInfo;
import com.service.impl.Category2ServiceImpl;
import com.service.impl.CategoryServiceImpl;
import com.service.inter.Category2Service;
import com.service.inter.CategoryService;
import com.vo.Category;
import com.vo.Category2;

public class Category2Servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 分发请求
		String action = request.getParameter("action");

		if ("getAllCategoryForAddCategory2".equals(action)) {
			this.getAllCategoryForAddCategory2(request, response);
		} else if ("add".equals(action)) {
			this.add(request, response);
		} else if ("getAll".equals(action)) {
			this.getAll(request, response);
		} else if ("getAllByPage".equals(action)) {
			this.getAllByPage(request, response);
		} else if ("getPageByQuery".equals(action)) {
			this.getPageByQuery(request, response);
		} else if ("delete".equals(action)) {
			this.delete(request, response);
		} else if ("getOneForUpdate".equals(action)) {
			this.getOneForUpdate(request, response);
		} else if ("update".equals(action)) {
			this.update(request, response);
		}else if("getMenuForFirstPage".equals(action)){	
			this.getMenuForFirstPage(request,response);
		}

	}

	public void getAllCategoryForAddCategory2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String target = "";
		// 1.填充数据
		// 2.调用业务逻辑
		CategoryService service = new CategoryServiceImpl();

		try {
			List<Category> list = service.getAllCategorys();

			request.setAttribute("list", list);

			target = "/WEB-INF/jsp/admin/category2/addCategory2.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", "查询一级商品种类失败!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		// 3.转发视图
		request.getRequestDispatcher(target).forward(request, response);
	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = "";
		// 一.填充数据
		String cname2 = request.getParameter("cname2");
		String cdesc2 = request.getParameter("cdesc2");
		String fid = request.getParameter("fid");

		// 二.调用业务逻辑
		Category2 category = new Category2();
		category.setCname2(cname2);
		category.setCdesc2(cdesc2);
		category.setFid(Integer.parseInt(fid));

		Category2Service service = new Category2ServiceImpl();
		try {
			service.addCategory2(category);
			request.setAttribute("msg", "添加二级商品种类成功");
		} catch (Exception e) {
			request.setAttribute("msg", "添加二级商品种类失败");
			e.printStackTrace();
		}
		// 三.转发视图
		target = "/WEB-INF/msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(target);

		rd.forward(request, response);
	}

	public void getAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = "";
		// 1.填充数据
		// 2.调用业务逻辑
		Category2Service service = new Category2ServiceImpl();

		try {
			List<Category2> list = service.getAllCategorys2();

			request.setAttribute("list", list);

			target = "/WEB-INF/jsp/admin/category2/categoryMain2.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", "查询二级商品种类失败!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		// 3.转发视图
		request.getRequestDispatcher(target).forward(request, response);
	}

	public void getAllByPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String target = ""; 
		// 1.填充数据
		String requestPage = request.getParameter("requestPage");

		try {

			// 查询一共多少条记录
			Category2Service service = new Category2ServiceImpl();
			
			int totalRecordCount = service.getTotalRecordCount2();

			PageInfo pageInfo = new PageInfo(Integer.parseInt(requestPage));
			pageInfo.setTotalRecordCount(totalRecordCount);

			// 2.调用业务逻辑
			Category2Service service2 = new Category2ServiceImpl();

			List<Category2> list = service2.getAllByPage(pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("pageInfo", pageInfo);
			target = "/WEB-INF/jsp/admin/category2/categoryMain2.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		// 3.转发视图
		request.getRequestDispatcher(target).forward(request, response);

	}

	public void getPageByQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String target = "";
		// 1.填充数据
		String searchCondition = request.getParameter("searchCondition");

		String requestPage = request.getParameter("requestPage");
		if (requestPage == null) {

			requestPage = "1";
		}

		Category2 category2 = new Category2();
		Category category = new Category();
		
		if (searchCondition != null && !searchCondition.equals("")) {
			category2.setCname2(searchCondition);
			category2.setCdesc2(searchCondition);
			
			category.setCname(searchCondition);
			category2.setCategory(category);
		}else{
			category2.setCname2("");
			category2.setCdesc2("");
			category.setCname("");
			category2.setCategory(category);
		}

		try {
			// 按照条件查询一共多少条记录
			Category2Service service = new Category2ServiceImpl();

			int totalRecordCount = service.getTotalRecordCount2(category2);
			PageInfo pageInfo = new PageInfo(Integer.parseInt(requestPage));
			pageInfo.setTotalRecordCount(totalRecordCount);
			// 2.调用业务逻辑
			Category2Service service2 = new Category2ServiceImpl();

			List<Category2> list = service2.getPageByQuery(category2, pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("searchCondition", searchCondition);
			request.setAttribute("pageInfo", pageInfo);
			target = "/WEB-INF/jsp/admin/category2/categoryMain2.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		// 3.转发视图
		request.getRequestDispatcher(target).forward(request, response);

	}

	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "";
		// 1.填充数据
		String cid = request.getParameter("cid");
		String searchCondition = request.getParameter("searchCondition");
		String requestPage = request.getParameter("requestPage");
		try {

			// 2.调用业务逻辑
			Category2Service service2 = new Category2ServiceImpl();

			service2.deleteCategory2ById(cid);
			request.setAttribute("msg", "删除成功!");
			request.setAttribute("searchCondition", searchCondition);
			request.setAttribute("requestPage", requestPage);
			
			this.getPageByQuery(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			target = "/WEB-INF/msg.jsp";
			e.printStackTrace();
			// 3.转发视图
			request.getRequestDispatcher(target).forward(request, response);
		}

	}

	public void getOneForUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String target = "";
		// 1.填充数据
		String cid = request.getParameter("cid");
		// 2.调用业务逻辑
		Category2Service service = new Category2ServiceImpl();

		try {
			Category2 category = service.getCategory2ById(cid);
			request.setAttribute("category", category);
			target = "/WEB-INF/jsp/admin/category2/updateCategory2.jsp";
		} catch (Exception e) {

			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		// 3.转发视图
		request.getRequestDispatcher(target).forward(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = "";
		// 1.填充数据
		String cid = request.getParameter("cid");
		String cname2 = request.getParameter("cname2");
		String cdesc2 = request.getParameter("cdesc2");
		//String fid = request.getParameter("fid");
		
		Category2 category = new Category2();
		
		category.setCid(Integer.parseInt(cid));
		category.setCname2(cname2);
		category.setCdesc2(cdesc2);
		//category.setFid(Integer.parseInt(fid));
		// 2.调用业务逻辑
		Category2Service service = new Category2ServiceImpl();
		try {
			service.updateCategory2(category);
			request.setAttribute("msg", "修改二级商品种类成功!");
			this.getPageByQuery(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
			// 3.转发视图
			request.getRequestDispatcher(target).forward(request, response);
		}

	}
	
	public void getMenuForFirstPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = "";
		//1.填充数据
		//2.调用业务逻辑
		CategoryService service = new CategoryServiceImpl();
		
		try {
			List<Category> list = service.getAllCategorys();
			
			request.setAttribute("list", list);
			
			target = "/WEB-INF/jsp/user/firstPage.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", "查询一级商品种类失败!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		//3.转发视图
		request.getRequestDispatcher(target).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
