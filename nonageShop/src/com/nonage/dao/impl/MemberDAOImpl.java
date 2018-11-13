package com.nonage.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nonage.dao.MemberDAO;
import com.nonage.db.sqlConfig.IBatisDBConnector;
import com.nonage.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	private static MemberDAOImpl instance = new MemberDAOImpl();
	private MemberDAOImpl() {}
	public static MemberDAOImpl getInstance() {
		return instance;
	}

	private SqlMapClient client = IBatisDBConnector.getSqlMapClient();

	@Override
	public int confirmID(String userid) throws SQLException {
		// id의 member 존재 1, 비존재 -1
		int result = -1;
		MemberVO member = (MemberVO) client.queryForObject("Member.confirmID", userid);
		if (member != null) {
			result = 1;
		} else {
			result = -1;
		}
		return result;
	}

	@Override
	public MemberVO getMember(String id) throws SQLException {
		MemberVO member = (MemberVO) client.queryForObject("Member.getMember", id);
		return member;
	}

	@Override
	public int insertMember(MemberVO memberVO) throws SQLException {
		// 가입 성공 1, 실패 -1
		int result = -1;
		if (client.update("Member.insertMember", memberVO) > 0) {
			result = 1;
		} else {
			result = -1;
		}
		return result;
	}

	@Override
	public List<MemberVO> listMember(String member_name) throws SQLException {
		List<MemberVO> memberList = (List<MemberVO>) client.queryForList("Member.listMember",member_name);
		return memberList;
	}

}
