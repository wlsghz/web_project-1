package com.nonage.dao.impl;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nonage.dao.WorkerDAO;
import com.nonage.db.sqlConfig.IBatisDBConnector;
import com.nonage.dto.WorkerVO;

public class WorkerDAOImpl implements WorkerDAO {
	private SqlMapClient client = IBatisDBConnector.getSqlMapClient();
	private static WorkerDAOImpl instance = new WorkerDAOImpl();

	private WorkerDAOImpl() {
	}

	public static WorkerDAOImpl getInstance() {
		return instance;
	}

	
	public WorkerVO workerCheck(String userid) throws SQLException {		
		WorkerVO worker = (WorkerVO) client.queryForObject("Worker.workerCheck", userid); // 디비 저장된 비밀번호
		
		return worker;
	}

}// WorkerDAO

