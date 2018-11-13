package com.nonage.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapSession;
import com.nonage.dto.CartVO;

public interface CartDAO {
	void insertCart(CartVO cartVO) throws SQLException;
	List<CartVO> listCart(String userId) throws SQLException;
	void deleteCart(int cseq) throws SQLException;	
	CartVO getCart(int cseq) throws SQLException;
	
	void updateResult(int cseq)throws SQLException;
	void updateResult(SqlMapSession session,int cseq)throws SQLException;
	
}
