package com.nonage.action.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.action.Action;
import com.nonage.dao.ProductDAO;
import com.nonage.dao.impl.ProductDAOImpl;
import com.nonage.dto.ProductVO;

public class AdminProductListAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String url="/admin/product/productList.jsp";
		String key=request.getParameter("key");
		String tpage=request.getParameter("tpage");
		if(key==null){
			key="";
		}
		if(tpage==null){
			tpage="1";
		}else if(tpage.equals("")){
			tpage="1";
		}
		
		request.setAttribute("key",key);
		request.setAttribute("tpage", tpage);
		
		ProductDAO productDAO=ProductDAOImpl.getInstance();
		
		ArrayList<ProductVO> productList=null;
		String paging=null;
		try{
			productList=
			productDAO.listProduct(Integer.parseInt(tpage),null, key);
			paging=productDAO.adminPageNumber(Integer.parseInt(tpage),null, key);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		request.setAttribute("productList", productList);
		request.setAttribute("paging", paging);
		request.setAttribute("productListSize", productList.size());
		
		return url;
	}

}










