package com.nonage.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.QnaDAO;
import com.nonage.dao.impl.QnaDAOImpl;
import com.nonage.dto.MemberVO;
import com.nonage.dto.QnaVO;

public class QnaWriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "redirect:qnaList.do";
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		QnaVO qna = new QnaVO();
		qna.setSubject(request.getParameter("subject"));
		qna.setContent(request.getParameter("content"));
		QnaDAO qnaDao = QnaDAOImpl.getInstance();
		try {
			qnaDao.insertqna(qna, loginUser.getId());
		} catch (SQLException e) {
			url="/error/exception.jsp";
			e.printStackTrace();
		}

		return url;
	}

}
