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
import com.narola.hotelbooking.Utility.UserURLConstant;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// get the session, add argument `true` to create a session if one is not yet created.
		HttpSession session = request.getSession(true);		
		Customer customer= CustomerDAO.getUserData(request.getParameter("email1"));		
		session.setAttribute("user", customer);
		session.setMaxInactiveInterval(20*60);
		RequestDispatcher rd = request.getRequestDispatcher("/");
		rd.forward(request, response);
//		response.setContentType("text/html;charset=UTF-8");
//        response.getWriter().write("True");		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("errormsg", "error");
		HttpSession session = request.getSession(true);		
//		session.set
		Customer customer= CustomerDAO.getUserData(request.getParameter("email1"));		
		session.setAttribute("user", customer);
		
		RequestDispatcher rd = request.getRequestDispatcher("/");
		rd.forward(request, response);
	}

}
