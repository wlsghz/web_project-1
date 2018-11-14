package com.nonage.action.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.action.Action;
import com.nonage.dao.QnaDAO;
import com.nonage.dao.impl.QnaDAOImpl;
import com.nonage.dto.QnaVO;

public class AdminQnaListAction implements Action {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String url = "/admin/qna/qnaList.jsp";
    
    /*QnaDAO qnaDAO = QnaDAO_JDBC.getInstance();*/
    QnaDAO qnaDAO = QnaDAOImpl.getInstance();
    ArrayList<QnaVO> qnaList=null;
	try {
		qnaList = qnaDAO.listAllQna();
	} catch (SQLException e) {
		e.printStackTrace();
	}

    request.setAttribute("qnaList", qnaList);

    return url;
  }
}
