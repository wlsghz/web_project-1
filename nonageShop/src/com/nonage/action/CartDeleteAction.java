package com.nonage.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.dao.CartDAO;
import com.nonage.dao.impl.CartDAOImpl;

public class CartDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url="redirect:cartList.do";
		
		String[] cseqArr=request.getParameterValues("cseq");
		
		for(String cseq:cseqArr){
			CartDAO cartDao=CartDAOImpl.getInstance();
			try {
				cartDao.deleteCart(Integer.parseInt(cseq));
			} catch (NumberFormatException e) {				
				e.printStackTrace();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		return url;
	}

}
