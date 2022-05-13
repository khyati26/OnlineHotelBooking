package com.narola.hotelbooking.Authentication;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class LoginValidationFilter
 */
public class LoginValidationFilter implements Filter {

    public LoginValidationFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		
		if(UserDAO.findUser(request.getParameter("email") , request.getParameter("pass") ))
		{
			chain.doFilter(request, response);			
		}
		else {
			String errormsg = "Invalid emailid/password";
			response.setContentType("text/plain"); 
			response.getWriter().write(errormsg);
		}			
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
