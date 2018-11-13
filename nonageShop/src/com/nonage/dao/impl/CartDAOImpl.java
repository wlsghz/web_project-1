package com.nonage.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.nonage.dao.CartDAO;
import com.nonage.db.sqlConfig.IBatisDBConnector;
import com.nonage.dto.CartVO;

public class CartDAOImpl implements CartDAO{
	
	private SqlMapClient client = IBatisDBConnector.getSqlMapClient();
	
	private static CartDAOImpl instance = new CartDAOImpl();
	private CartDAOImpl() {}
	public static CartDAOImpl getInstance() {
		return instance;
	}
	
	@Override
	public void insertCart(CartVO cartVO) throws SQLException {
		client.insert("Cart.insertCart", cartVO);
		
	}

	@Override
	public List<CartVO> listCart(String userId) throws SQLException {
		List<CartVO> cartList = new ArrayList<CartVO>();
		cartList = (List<CartVO>) client.queryForList("Cart.listCart", userId);
		return cartList;
	}

	@Override
	public void deleteCart(int cseq) throws SQLException {
		client.delete("Cart.deleteCart",cseq);
		
	}

	@Override
	public CartVO getCart(int cseq) throws SQLException {
		CartVO cartVO=null;
		cartVO=(CartVO)client.queryForObject("Cart.getCart", cseq);
		return cartVO;
	}
	@Override
	public void updateResult(int cseq) throws SQLException {
		client.update("Cart.updateCartResult", cseq);
		
	}
	@Override
	public void updateResult(SqlMapSession session, int cseq) throws SQLException {
		session.update("Cart.updateCartResult", cseq);
		
	}

}
