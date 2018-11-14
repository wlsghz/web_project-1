package com.nonage.action.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.action.Action;
import com.nonage.dao.ProductDAO;
import com.nonage.dao.impl.ProductDAOImpl;
import com.nonage.dto.ProductVO;

public class AdminProductDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String url="/admin/product/productDetail.jsp";
		
		int pseq=Integer.parseInt(request.getParameter("pseq"));
		
		ProductDAO productDao=ProductDAOImpl.getInstance();
		ProductVO product = null;
		
		try {
			product = productDao.getProduct(pseq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("productVO", product);
		
		String tpage="1";
		if(request.getParameter("tpage")!=null) {
			tpage=request.getParameter("tpage");
		}
		
		String[] kind={"","Heals", "Boots", "Sandals", "Slippers","Shcakers","OnSales"};
		request.setAttribute("tpage", tpage);
		int index=Integer.parseInt(product.getKind().trim());
		request.setAttribute("kind", kind[index]);
		return url;
	}

}
