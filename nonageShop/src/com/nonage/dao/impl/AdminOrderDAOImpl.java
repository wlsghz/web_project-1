package com.nonage.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.nonage.dao.AdminOrderDAO;
import com.nonage.db.sqlConfig.IBatisDBConnector;
import com.nonage.dto.CartVO;
import com.nonage.dto.OrderVO;

public class AdminOrderDAOImpl implements AdminOrderDAO {
	private SqlMapClient client = IBatisDBConnector.getSqlMapClient();
	private static AdminOrderDAOImpl instance = new AdminOrderDAOImpl();
	private AdminOrderDAOImpl() {}
	public static AdminOrderDAOImpl getInstance() {
		return instance;
	}

	@Override
	public int insertOrder(ArrayList<CartVO> cartList, String id)
			throws SQLException {
		int maxOseq = 0;		

		// iBatis Transaction
		Connection conn = null;
		SqlMapSession session = null;
			
			conn=client.getDataSource().getConnection();
			session=client.openSession(conn);
			session.insert("AdminOrder.insertOrder", id);			
			maxOseq = (Integer)session.queryForObject("AdminOrder.selectMaxOseq", null);
			
			for (CartVO cartVO : cartList) {				
				insertOrderDetail(cartVO, maxOseq);
				
			}
		return maxOseq;
	}

	@Override
	public void insertOrderDetail(CartVO cartVO, int maxOseq)
			throws SQLException {		

		Connection conn = null;
		SqlMapSession session = null;
		try {
			conn = client.getDataSource().getConnection();
			session = client.openSession(conn);

			conn.setAutoCommit(false);
			
			OrderVO oVo=new OrderVO();
			oVo.setOseq(maxOseq);
			oVo.setPseq(cartVO.getPseq());
			oVo.setQuantity(cartVO.getQuantity());
			
			session.insert("AdminOrder.insertOrderDetail", oVo);			
			session.update("AdminOrder.updateCartResult",cartVO.getCseq());
			
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
	}

	@Override
	public ArrayList<OrderVO> listOrderById(String id, String result, int oseq)
			throws SQLException {
		OrderVO oVo=new OrderVO();
		oVo.setId(id);
		oVo.setResult(result);
		oVo.setOseq(oseq);
		
		ArrayList<OrderVO> orderList=(ArrayList<OrderVO>)client.queryForList("AdminOrder.listOrderById",oVo);
		
		return orderList;
	}

	@Override
	public ArrayList<Integer> selectSeqOrderIng(String id) throws SQLException {
		ArrayList<Integer> oseqList = (ArrayList<Integer>)client.queryForList("AdminOrder.selectSeqOrderIng", id);
		
		return oseqList;
	}
	@Override
	public ArrayList<Integer> selectSeqOrderTotal(String id)
			throws SQLException {
		ArrayList<Integer> oseqList = (ArrayList<Integer>)client.queryForList("AdminOrder.selectSeqOrderTotal", id);
		return oseqList;
	}
	@Override
	public ArrayList<OrderVO> listOrder(String member_name) throws SQLException {
		ArrayList<OrderVO> orderList=(ArrayList<OrderVO>)client.queryForList("AdminOrder.listOrder",member_name );
		return orderList;
	}

	@Override
	public void updateOrderResult(String oseq) throws SQLException {
		client.update("AdminOrder.updateOrderResult",oseq);
	}	

}
