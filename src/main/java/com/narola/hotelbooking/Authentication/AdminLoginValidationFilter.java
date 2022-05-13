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
 * Servlet Filter implementation class AdminLoginValidationFilter
 */
public class AdminLoginValidationFilter implements Filter {

	public AdminLoginValidationFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (UserDAO.findAdminUser(request.getParameter("email"), request.getParameter("pass"))) {
			chain.doFilter(request, response);			
		} else {
			String errormsg = "Invalid emailid/password";
			request.setAttribute("errormsg", errormsg);
			RequestDispatcher rd = request.getRequestDispatcher("admin/loginpage.jsp");
			rd.forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
