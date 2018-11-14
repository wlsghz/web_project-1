package com.nonage.action.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.action.Action;
import com.nonage.dao.MemberDAO;
import com.nonage.dao.impl.MemberDAOImpl;
import com.nonage.dto.MemberVO;

public class AdminMemberListAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String url="/admin/member/memberList.jsp";
		
		String key="";
		if(request.getParameter("key")!=null){
			key=request.getParameter("key");
		}
		
		MemberDAO memberDao=MemberDAOImpl.getInstance();
		List<MemberVO> memberList=null;
		try {
			memberList=memberDao.listMember(key);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("memberList",memberList);
		
		return url;
	}

}






