package com.nonage.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.nonage.dao.OrderDAO;
import com.nonage.dao.impl.OrderDAOImpl;
import com.nonage.db.sqlConfig.IBatisDBConnector;
import com.nonage.dto.MemberVO;
import com.nonage.dto.OrderVO;

public class MyPageAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/mypage/mypage.jsp";

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		String id = loginUser.getId();

		SqlMapClient client = IBatisDBConnector.getSqlMapClient();
		OrderDAO orderDAO = OrderDAOImpl.getInstance();

		Connection conn = null;
		SqlMapSession sqlSession = null;

		List<Integer> oseqList = null;
		List<OrderVO> orderListIng = null;

		Map<Integer, List<OrderVO>> orderGroup = new HashMap<Integer, List<OrderVO>>();
		try {
			conn = client.getDataSource().getConnection();
			conn.setAutoCommit(false);

			sqlSession = client.openSession();		

			oseqList = orderDAO.selectSeqOrderIng(sqlSession, loginUser.getId());
			for (int oseq : oseqList) {
				orderListIng = orderDAO.listOrderById(sqlSession, id, "1", oseq);
				orderGroup.put(oseq, orderListIng);
			}
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			sqlSession.close();
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("title","진행중인 주문내역");
		request.setAttribute("orderGroup", orderGroup);
		request.setAttribute("oseqList",oseqList);
		
		return url;
	}

}
