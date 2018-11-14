package com.nonage.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.dao.ProductDAO;
import com.nonage.dao.impl.ProductDAOImpl;
import com.nonage.dto.ProductVO;

public class IndexAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String url="/main.jsp";
		
		ProductDAO productDao=ProductDAOImpl.getInstance();
		
		List<ProductVO> newProductList=null;
		List<ProductVO> bestProductList=null;
		
		
		try {
			newProductList=productDao.listNewProduct();
			bestProductList=productDao.listBestProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("newProductList", newProductList);
		request.setAttribute("bestProductList", bestProductList);
		
		return url;
	}

}
