package com.nonage.viewer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolver {
	
	public static void view(HttpServletRequest request, 
						    HttpServletResponse response,
						    String url) 
								throws ServletException, IOException{
		
		String prefix="/WEB-INF/views";
		
		if(url.contains("redirect:")){
			url=url.replace("redirect:", "");
			response.sendRedirect(url);
		}else{
			url=prefix+url;
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
	
}





