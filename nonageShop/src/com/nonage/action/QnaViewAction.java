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

public class QnaViewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/qna/qnaView.jsp";

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		int qseq = Integer.parseInt(request.getParameter("qseq"));

		QnaDAO qnaDao = QnaDAOImpl.getInstance();
		try {
			QnaVO qna = qnaDao.getQna(qseq);
			request.setAttribute("qnaVO", qna);
		} catch (SQLException e) {
			url="/error/exception.jsp";
			e.printStackTrace();			
		}

		return url;
	}

}
