package com.narola.hotelbooking.Authentication;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.narola.hotelbooking.Customer.CustomerDAO;

public class RegistraterValidationFilter implements Filter {

    public RegistraterValidationFilter() {
        // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if( !CustomerDAO.checkUniqueEmail(request.getParameter("email").toLowerCase())) {
			String errormsg2 = "email should be unique.";
			response.setContentType("text/plain"); 
			response.getWriter().write(errormsg2);		
		}
		else if(request.getParameter("pass").compareTo(request.getParameter("cpass")) != 0) {
			String errormsg2 = "password and confirmed password should be same";
			response.setContentType("text/plain"); 
			response.getWriter().write(errormsg2);
		}
		else {		chain.doFilter(request, response);	}
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
