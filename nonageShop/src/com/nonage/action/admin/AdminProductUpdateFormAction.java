package com.nonage.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.action.Action;
import com.nonage.dao.ProductDAO;
import com.nonage.dao.impl.ProductDAOImpl;
import com.nonage.dto.ProductVO;

public class AdminProductUpdateFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String url="/admin/product/productUpdate.jsp";
		int pseq=Integer.parseInt(request.getParameter("pseq").trim());
		
		ProductDAO productDao = ProductDAOImpl.getInstance();
		ProductVO product = null;
		
		try {
			product=productDao.getProduct(pseq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("productVO", product);
		
		String tpage="1";
		if(request.getParameter("tpage")!=null) {
			tpage=request.getParameter("tpage");
		}
		
		String[] kindList={"Heals", "Boots", "Sandals", "Slippers","Shcakers","OnSales"};
		request.setAttribute("tpage", tpage);
		int index=Integer.parseInt(product.getKind().trim());
		request.setAttribute("kindList", kindList);
		return url;
	}

}
