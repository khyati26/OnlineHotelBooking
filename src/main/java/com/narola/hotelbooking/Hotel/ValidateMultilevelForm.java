package com.narola.hotelbooking.Hotel;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.narola.hotelbooking.Exception.ValidationErrorMsg;

/**
 * Servlet Filter implementation class Validate_multilevel_form
 */
public class ValidateMultilevelForm implements Filter {

    public ValidateMultilevelForm() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 
		Hotel hotel = new Hotel();
		hotel.setName(request.getParameter("name"));
		hotel.setAddress(request.getParameter("address"));
		hotel.setEmailId(request.getParameter("email"));
		hotel.setServices(request.getParameter("service"));
		hotel.setPolicy(request.getParameter("policy"));

		try {
			HotelValidation.hotelfieldvalidation(hotel);
			chain.doFilter(request, response);
		} catch (ValidationErrorMsg e) {
			request.setAttribute("errMsg", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("admin/hotel/AddHotel.jsp");
			rd.forward(request, response);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
