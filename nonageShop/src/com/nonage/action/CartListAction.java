package com.nonage.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.CartDAO;
import com.nonage.dao.impl.CartDAOImpl;
import com.nonage.dto.CartVO;
import com.nonage.dto.MemberVO;

public class CartListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/mypage/cartList.jsp";

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		CartDAO cartDao = CartDAOImpl.getInstance();
		List<CartVO> cartList = null;
		try {
			cartList = cartDao.listCart(loginUser.getId());
			int totalPrice = 0;
			for (CartVO cart : cartList) {
				totalPrice += cart.getPrice2() * cart.getQuantity();
			}
			request.setAttribute("cartList", cartList);
			request.setAttribute("totalPrice", totalPrice);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return url;
	}

}
