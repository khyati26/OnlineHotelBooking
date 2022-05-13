package com.narola.hotelbooking.Authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.hotelbooking.Customer.Customer;
import com.narola.hotelbooking.Customer.CustomerDAO;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Customer customer=new Customer();
		customer.setFirstName(request.getParameter("fname"));
		customer.setLastName(request.getParameter("lname"));
		customer.setEmail(request.getParameter("email"));
		customer.setMobile(request.getParameter("mobile"));
		customer.setId(CustomerDAO.inserData(customer));
		
		User user=new User();
		user.setEmail(customer.getEmail());
		user.setPassword(request.getParameter("pass"));
		user.setCustomerId(customer.getId());
		user.setUsertype(1);
		user.setId(UserDAO.inserData(user));
		
		 response.setContentType("text/html;charset=UTF-8");
         response.getWriter().write("True");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Customer customer=new Customer();
		customer.setFirstName(request.getParameter("fname"));
		customer.setLastName(request.getParameter("lname"));
		customer.setEmail(request.getParameter("email2"));
		customer.setMobile(request.getParameter("mobile"));
		customer.setId(CustomerDAO.inserData(customer));
		
		User user=new User();
		user.setEmail(customer.getEmail());
		user.setPassword(request.getParameter("password2"));
		user.setCustomerId(customer.getId());
		user.setUsertype(1);
		user.setId(UserDAO.inserData(user));
	
		response.sendRedirect("displayallhotels");
	}

}
