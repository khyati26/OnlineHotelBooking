package com.narola.hotelbooking.Authentication;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.narola.hotelbooking.Customer.Customer;
import com.narola.hotelbooking.Customer.CustomerDAO;

/**
 * Servlet implementation class AdminLoginServlet
 */
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// get the session, add argument `true` to create a session if one is not yet created.
		HttpSession session = request.getSession(true);		
		Customer customer= CustomerDAO.getUserData(request.getParameter("email"));		
		session.setAttribute("user", customer);
		session.setMaxInactiveInterval(30*60);
		response.sendRedirect(request.getContextPath()+"/admindashboard");
	}

}
