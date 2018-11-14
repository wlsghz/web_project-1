package com.nonage.action.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.action.Action;
import com.nonage.dao.AdminOrderDAO;
import com.nonage.dao.impl.AdminOrderDAOImpl;
import com.nonage.dto.OrderVO;

public class AdminOrderListAction implements Action {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String url = "/admin/order/orderList.jsp";
    String key = "";
    if (request.getParameter("key") != null) {
      key = request.getParameter("key");
    }

    /*OrderDAO orderDAO = OrderDAO_JDBC.getInstance();*/
    AdminOrderDAO orderDAO = AdminOrderDAOImpl.getInstance();
    ArrayList<OrderVO> orderList=null;
	try {
		orderList = orderDAO.listOrder(key);
	} catch (SQLException e) {	
		e.printStackTrace();
	}

    request.setAttribute("orderList", orderList);

    return url;
  }
  
}
