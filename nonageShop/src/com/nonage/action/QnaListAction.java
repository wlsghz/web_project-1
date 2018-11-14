package com.nonage.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.QnaDAO;
import com.nonage.dao.impl.QnaDAOImpl;
import com.nonage.dto.MemberVO;
import com.nonage.dto.QnaVO;

public class QnaListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/qna/qnaList.jsp";

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		QnaDAO qnaDao = QnaDAOImpl.getInstance();

		ArrayList<QnaVO> qnaList = null;

		try {
			qnaList = qnaDao.listQna(loginUser.getId());
		} catch (SQLException e) {
			url="/error/exception.jsp";
			e.printStackTrace();
		}
		request.setAttribute("qnaList", qnaList);

		return url;
	}

}
