package com.nonage.action.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.action.Action;
import com.nonage.dao.AdminOrderDAO;
import com.nonage.dao.impl.AdminOrderDAOImpl;

public class AdminOrderSaveAction implements Action {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      String url = "redirect:orderList.do";
      
      String[] resultArr = request.getParameterValues("result");
      
      for(String oseq:resultArr){
        System.out.println(oseq);
        /*OrderDAO orderDAO = OrderDAO_JDBC.getInstance();*/
        AdminOrderDAO orderDAO = AdminOrderDAOImpl.getInstance();
        try {
			orderDAO.updateOrderResult(oseq);
		} catch (SQLException e) {		
			e.printStackTrace();
		}
      }
      return url;
    }
}
