package com.nonage.action.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.action.Action;
import com.nonage.dao.QnaDAO;
import com.nonage.dao.impl.QnaDAOImpl;
import com.nonage.dto.QnaVO;

public class AdminQnaDetailAction implements Action {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String url = "/admin/qna/qnaDetail.jsp";

    int qseq = Integer.parseInt(request.getParameter("qseq").trim());

    /*QnaDAO qnaDAO = QnaDAO_JDBC.getInstance();*/
    QnaDAO qnaDAO = QnaDAOImpl.getInstance();
    QnaVO qnaVO=null;
	try {
		qnaVO = qnaDAO.getQna(qseq);
	} catch (NumberFormatException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}

    request.setAttribute("qnaVO", qnaVO);
        
    return url;
  }
}
