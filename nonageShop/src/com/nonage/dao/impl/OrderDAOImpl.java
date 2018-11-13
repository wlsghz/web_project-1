package com.nonage.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapSession;
import com.nonage.dao.OrderDAO;
import com.nonage.dto.CartVO;
import com.nonage.dto.OrderVO;

public class OrderDAOImpl implements OrderDAO{	
		
	private static OrderDAOImpl instance = new OrderDAOImpl();
	private OrderDAOImpl() {}
	public static OrderDAOImpl getInstance() {
		return instance;
	}

	@Override
	public void insertOrder(SqlMapSession session,List<CartVO> cartList, String id, int oseq) throws SQLException {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("oseq",oseq);
		paramMap.put("id",id);		
		session.insert("Order.insertOrder", paramMap);		
	}

	@Override
	public void insertOrderDetail(SqlMapSession session,CartVO cartVO, int oseq) throws SQLException {
		
		OrderVO order=new OrderVO();
		order.setOseq(oseq);
		order.setPseq(cartVO.getPseq());
		order.setQuantity(cartVO.getQuantity());
		
		session.insert("Order.insertOrderDetail", order);		
	}

	@Override
	public int getOseqNextValue(SqlMapSession session) throws SQLException {
		int oseq=(Integer)session.queryForObject("Order.getOseqNextValue",null);
		return oseq;
	}
	
	@Override
	public List<OrderVO> listOrderById(SqlMapSession session,String id, 
									   String result, int oseq) 
											   throws SQLException {
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id",id);
		paramMap.put("result",result);
		paramMap.put("oseq",oseq);
		
		List<OrderVO> orderList=
				(List<OrderVO>)session.queryForList("Order.listOrderById"
																,paramMap);
		return orderList;
	}
	
	@Override
	public List<Integer> selectSeqOrderIng(SqlMapSession session,String id) throws SQLException {
		List<Integer> oseqList = 
				(List<Integer>)session.queryForList("Order.selectSeqOrderIng", id);		
		return oseqList;
	}
	
	@Override
	public List<Integer> selectSeqOrderAll(SqlMapSession session, String id) throws SQLException {
		List<Integer> oseqList = 
				(List<Integer>)session.queryForList("Order.selectSeqOrderAll", id);		
		return oseqList;
	}

}





