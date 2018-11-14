package com.nonage.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.MemberDAO;
import com.nonage.dao.impl.MemberDAOImpl;
import com.nonage.dto.MemberVO;

public class LoginAction implements Action {
	MemberDAO memberDAO = MemberDAOImpl.getInstance();
	public void setMemberDAO(MemberDAO memberDAO){
		this.memberDAO=memberDAO;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url="redirect:index.do";
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		HttpSession session = request.getSession();
		
		MemberVO member=null;
		
		try {
			member=memberDAO.getMember(id);
		} catch (SQLException e) {			
			e.printStackTrace();
			url="/error/exception.jsp";
		}
		
		if(member!=null){
			if(member.getPwd().equals(pwd)){
				session.setAttribute("loginUser",member);				
			}else{
				url="/member/login_fail.jsp";
			}
		}
		
		return url;
	}

}









