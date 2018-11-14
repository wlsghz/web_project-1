package com.nonage.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.dao.MemberDAO;
import com.nonage.dao.impl.MemberDAOImpl;
import com.nonage.dto.MemberVO;

public class JoinAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String url="redirect:loginForm.do";
		
		MemberVO member=new MemberVO();
		
		member.setId(request.getParameter("id"));
		member.setPwd(request.getParameter("pwd"));
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email"));
		member.setZipNum(request.getParameter("zipNum"));
		member.setAddress(request.getParameter("addr1")+" "
						+request.getParameter("addr2"));
		member.setPhone(request.getParameter("phone"));
		
		MemberDAO memberDao=MemberDAOImpl.getInstance();
		try {
			memberDao.insertMember(member);
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		
		request.setAttribute("id", member.getId());
		
		return url;
	}

}
