package com.nonage.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.dao.ProductDAO;
import com.nonage.dao.impl.ProductDAOImpl;
import com.nonage.dto.ProductVO;

public class ProductKindAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url="/product/productKind.jsp";
		
		String kind=request.getParameter("kind");
		int tpage = Integer.parseInt(request.getParameter("tpage"));
		String product_name=request.getParameter("name");
		
		ProductDAO productDao=ProductDAOImpl.getInstance();
		ArrayList<ProductVO> productKindList=null;
		String paging=null;
		try {
			productKindList=productDao.listProduct(tpage,kind,product_name);
			paging=productDao.pageNumber(tpage,kind, product_name);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		request.setAttribute("productKindList", productKindList);
		request.setAttribute("paging",paging);
		
		return url;
	}

}







