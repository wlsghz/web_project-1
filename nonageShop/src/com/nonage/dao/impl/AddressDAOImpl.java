package com.nonage.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nonage.dao.AddressDAO;
import com.nonage.db.sqlConfig.IBatisDBConnector;
import com.nonage.dto.AddressVO;

public class AddressDAOImpl implements AddressDAO{
	private SqlMapClient client = IBatisDBConnector.getSqlMapClient();
	
	private static AddressDAOImpl instance = new AddressDAOImpl();
	private AddressDAOImpl() {}
	public static AddressDAOImpl getInstance() {
		return instance;
	}
	
	@Override
	public List<AddressVO> selectAddressByDong(String dong) throws SQLException{
		List<AddressVO> addressList=
		(List<AddressVO>)client.queryForList("Address.selectAddressByDong",dong);
		return addressList;

	}

}
