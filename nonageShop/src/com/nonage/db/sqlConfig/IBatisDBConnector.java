package com.nonage.db.sqlConfig;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public final class IBatisDBConnector {
	
	private static SqlMapClient client;
	
	static{
		try{
		String resource="com/nonage/db/sqlConfig/SqlMapConfig.xml";
		Reader reader=Resources.getResourceAsReader(resource);
		client=SqlMapClientBuilder.buildSqlMapClient(reader);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static SqlMapClient getSqlMapClient(){
		return client;
	}
}





