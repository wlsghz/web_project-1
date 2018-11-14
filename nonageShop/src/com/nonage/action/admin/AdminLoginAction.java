package com.nonage.action.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.action.Action;
import com.nonage.dao.WorkerDAO;
import com.nonage.dao.impl.WorkerDAOImpl;
import com.nonage.dto.WorkerVO;

public class AdminLoginAction implements Action {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String url = "/admin/main.jsp";
    
    String msg = "";
    String workerId = request.getParameter("workerId").trim();
    String workerPwd = request.getParameter("workerPwd").trim();

    WorkerDAO workerDAO = WorkerDAOImpl.getInstance();

    WorkerVO worker=null;
	try {
		worker = workerDAO.workerCheck(workerId);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
	HttpSession session = request.getSession();
	
	if(worker!=null){
		if(workerPwd.equals(worker.getPwd())){ //로그인 성공.
			session.setAttribute("loginUser",worker);
			url = "redirect:productList.do";
		}else{//패스워드 불일치
			msg = "비밀번호를 확인하세요.";
		}
	}else{//존재하지 않는 아이디.
		 msg = "아이디를 확인하세요.";
	}	
   
    request.setAttribute("message", msg);
    
    return url;
  }
}
