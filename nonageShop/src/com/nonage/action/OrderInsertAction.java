package com.nonage.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.nonage.dao.CartDAO;
import com.nonage.dao.OrderDAO;
import com.nonage.dao.impl.CartDAOImpl;
import com.nonage.dao.impl.OrderDAOImpl;
import com.nonage.db.sqlConfig.IBatisDBConnector;
import com.nonage.dto.CartVO;
import com.nonage.dto.MemberVO;

public class OrderInsertAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/order/orderSuccess.jsp";

		SqlMapClient client = IBatisDBConnector.getSqlMapClient();
		OrderDAO orderDAO = OrderDAOImpl.getInstance();

		Connection conn = null;
		SqlMapSession session = null;

		String[] cseqArr = request.getParameterValues("cseq");
		CartDAO cartDao = CartDAOImpl.getInstance();
		OrderDAO orderDao = OrderDAOImpl.getInstance();
		
		MemberVO loginUser = (MemberVO)request.getSession().getAttribute("loginUser");		
		String id=loginUser.getId();
		
		List<CartVO> cartList = new ArrayList<CartVO>();

		try {
			conn = client.getDataSource().getConnection();
			conn.setAutoCommit(false);
			session = client.openSession();

			for (String cseq : cseqArr) {
				CartVO cart = null;
				cart = cartDao.getCart(Integer.parseInt(cseq));
				cartList.add(cart);
			}
			
			int oseq=orderDAO.getOseqNextValue(session);
			orderDAO.insertOrder(session, cartList, id, oseq);
			for(CartVO cartVO : cartList){
				orderDAO.insertOrderDetail(session, cartVO, oseq);
				cartDao.updateResult(session, cartVO.getCseq());
			}			
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			url="/error/exception.jsp";
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
				session.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return url;
	}

}
