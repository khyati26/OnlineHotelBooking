package com.narola.hotelbooking.Hotel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.narola.hotelbooking.Exception.ValidationErrorMsg;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.Utility.ConnectDB;

/**
 * Servlet Filter implementation class addhotelfilter
 */
public class validationfilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		response.setContentType("text/html");
		if (request.getParameter("insert") != null) {

			Hotel hotel = new Hotel();
			hotel.setName(request.getParameter("name"));
			hotel.setCity(request.getParameter("city"));
			hotel.setState(request.getParameter("state"));
			hotel.setAddress(request.getParameter("address"));
			hotel.setRating(Integer.parseInt(request.getParameter("rating")));
			hotel.setEmailId(request.getParameter("email"));
			hotel.setServices(request.getParameter("service"));
			hotel.setPolicy(request.getParameter("policy"));

			try {
				HotelValidation.hotelfieldvalidation(hotel);
				chain.doFilter(request, response);
			} catch (ValidationErrorMsg e) {
				request.setAttribute("errMsg", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("AddHotel.jsp");
				rd.forward(request, response);
			}
		} else if (request.getParameter("update") != null) {
			Hotel hotel = new Hotel();
			hotel.setId(Integer.parseInt(request.getParameter("id")));
			hotel.setName(request.getParameter("name"));
			hotel.setCity(request.getParameter("city"));
			hotel.setState(request.getParameter("state"));
			hotel.setAddress(request.getParameter("address"));
			hotel.setRating(Integer.parseInt(request.getParameter("rating")));
			hotel.setEmailId(request.getParameter("email"));
			hotel.setServices(request.getParameter("service"));
			hotel.setPolicy(request.getParameter("policy"));
			
			try {
				HotelValidation.hotelfieldvalidation(hotel);
				chain.doFilter(request, response);
			} catch (ValidationErrorMsg e) {
				request.setAttribute("hotel", hotel);
				request.setAttribute("errMsg", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("EditHotel.jsp");
				rd.forward(request, response);				
			}
		}

		else {
			chain.doFilter(request, response);
		}


	}


}
