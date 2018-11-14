package com.nonage.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.dao.MemberDAO;
import com.nonage.dao.impl.MemberDAOImpl;

public class IdCheckFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/member/idcheck.jsp";
		String id = request.getParameter("id");

		MemberDAO memberDao = MemberDAOImpl.getInstance();
		int message = -1;

		try {
			message = memberDao.confirmID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("message", message);
		request.setAttribute("id", id);

		return url;
	}

}
