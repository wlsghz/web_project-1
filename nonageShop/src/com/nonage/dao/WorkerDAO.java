package com.nonage.dao;

import java.sql.SQLException;

import com.nonage.dto.WorkerVO;


public interface WorkerDAO {
	
	
	public WorkerVO workerCheck(String userid) throws SQLException;
	
}// WorkerDAO

