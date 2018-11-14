package com.nonage.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.action.Action;

public class AdminProductWriteFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String url="/admin/product/productWrite.jsp";
		String kindList[]
				={"Heals", "Boots", "Sandals", "Slippers","Shcakers","OnSales"};
		request.setAttribute("kindList", kindList);
		return url;
	}
	

}
