package com.nonage.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dto.MemberVO;

public class LoginCheckFilter implements Filter {

	String[] exUrl;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpReq = (HttpServletRequest) request;
		String requestUrl = httpReq.getRequestURI().replace(httpReq.getContextPath(), "");

		boolean loginCheck = true;
		if (!requestUrl.replace("/", "").isEmpty()) {
			if (requestUrl.contains("admin")) {
				if(requestUrl.indexOf("login")>-1 || requestUrl.contains("resources")){
					loginCheck=false;
				}
			} else {
				for (String exUrl : this.exUrl) {
					if (requestUrl.indexOf(exUrl) > -1) {
						loginCheck = false;
						System.out.println(requestUrl);
						break;
					}
				}
			}			
		} else {
			loginCheck = false;
		}

		if (loginCheck) {
			loginCheck(httpReq, (HttpServletResponse) response, chain);
		} else {
			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		String initParam = fConfig.getInitParameter("exclude");
		String[] exUrl = initParam.split(",");
		this.exUrl = exUrl;
	}

	private void loginCheck(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser != null) {
			chain.doFilter(request, response);
		} else {
			// response.sendRedirect("loginForm.do");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('로그인이 필요합니다.');");
			out.print("location.href='loginForm.do';");
			out.print("</script>");
		}
	}

}
