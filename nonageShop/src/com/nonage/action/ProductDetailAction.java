package com.nonage.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.dao.ProductDAO;
import com.nonage.dao.impl.ProductDAOImpl;
import com.nonage.dto.ProductVO;

public class ProductDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url="/product/productDetail.jsp";
		
		int pseq=Integer.parseInt(request.getParameter("pseq"));
		
		ProductDAO productDao=ProductDAOImpl.getInstance();
		ProductVO product=null;
		
		try {
			product=productDao.getProduct(pseq);
		} catch (SQLException e) {			
			e.printStackTrace();
			url="/error/exception.jsp";
		}
		
		request.setAttribute("productVO", product);
		
		return url;		
	}

}








