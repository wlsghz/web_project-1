package com.nonage.dao;

import java.sql.SQLException;
import java.util.List;

import com.nonage.dto.AddressVO;

public interface AddressDAO {
	
	public List<AddressVO> selectAddressByDong(String dong)
					throws SQLException;
}
