package com.nonage.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.CartDAO;
import com.nonage.dao.impl.CartDAOImpl;
import com.nonage.dto.CartVO;
import com.nonage.dto.MemberVO;

public class CartInsertAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/cart/cartInsertSuccess.jsp";

		HttpSession session = request.getSession();

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		CartVO cart = new CartVO();
		cart.setId(loginUser.getId());
		cart.setPseq(Integer.parseInt(request.getParameter("pseq")));
		cart.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		CartDAO cartDao = CartDAOImpl.getInstance();
		try {
			cartDao.insertCart(cart);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return url;
	}

}
