package com.nonage.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.dao.AddressDAO;
import com.nonage.dao.impl.AddressDAOImpl;
import com.nonage.dto.AddressVO;

public class FindZipNumAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/member/findZipNum.jsp";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String dong = request.getParameter("dong");

		if (dong != null && !dong.trim().isEmpty()) {
			AddressDAO addressDao = AddressDAOImpl.getInstance();

			try {
				List<AddressVO> addressList = 
						addressDao.selectAddressByDong(dong);
				request.setAttribute("addressList", addressList);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return url;
	}

}
