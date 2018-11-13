package com.nonage.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapSession;
import com.nonage.dto.CartVO;
import com.nonage.dto.OrderVO;

public interface OrderDAO {
	
	// 사용자가 주문
	void insertOrder(SqlMapSession session, List<CartVO> cartList, String id,int oseq) throws SQLException;
	void insertOrderDetail(SqlMapSession session, CartVO cartVO, int oseq ) throws SQLException;
	
	int getOseqNextValue(SqlMapSession session) throws SQLException;
	
	// 현재 진행 중인 주문의 주문번호 조회
	List<Integer> selectSeqOrderIng(SqlMapSession session,String id) throws SQLException;
	
	// 전체 진행 중인 주문의 주문번호 조회
	List<Integer> selectSeqOrderAll(SqlMapSession session,String id) throws SQLException;
	
	// 현재 상태에 따른 주문리스트 조회
	List<OrderVO> listOrderById(SqlMapSession session,String id, String result, int oseq) 
			throws SQLException;
}



