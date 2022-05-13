package com.narola.hotelbooking.Room;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.narola.hotelbooking.Exception.ValidationErrorMsg;
import com.narola.hotelbooking.Hotel.HotelValidation;
import com.narola.hotelbooking.Utility.AdminURLConstant;

/**
 * Servlet Filter implementation class RoomValidationFilter
 */
public class RoomValidationFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		response.setContentType("text/html");
		if (request.getParameter("insert") != null) {

			Room r = new Room();
			r.setName(request.getParameter("name"));
			r.setHotelID(Integer.parseInt(request.getParameter("hotelid")));
			r.setPrice(Double.valueOf(request.getParameter("price")));
			r.setInclusions(request.getParameter("inclusions"));
			r.setMaxcapacity(Integer.valueOf(request.getParameter("maxcapacity")));
			r.setDescription(request.getParameter("description"));

			try {
				RoomValidation.roomfieldvalidation(r);
				chain.doFilter(request, response);
			} catch (ValidationErrorMsg e) {
				request.setAttribute("room", r);
				request.setAttribute("errMsg", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("admin/room/AddHotelWiseRoom.jsp");
				rd.forward(request, response);
			}

		} else if (request.getParameter("update") != null) {

 			Room h = new Room();
			h.setId(Integer.parseInt(request.getParameter("id")));
			h.setName(request.getParameter("name"));
			h.setHotelID(Integer.parseInt(request.getParameter("hotelid")));
			h.setPrice(Double.valueOf(request.getParameter("price")));
			h.setInclusions(request.getParameter("inclusions"));
			h.setMaxcapacity(Integer.parseInt(request.getParameter("maxcapacity")));
			h.setDescription(request.getParameter("description"));
			Collection<String> images=new ArrayList();
			h.setImages(images);
			
			try {
				RoomValidation.roomfieldvalidation(h);
				chain.doFilter(request, response);
			} catch (ValidationErrorMsg e) {
				request.setAttribute("room", h);
				request.setAttribute("errMsg", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("admin/room/UpdateHotelWiseRoom.jsp");
				rd.forward(request, response);				
			}

		} else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	

}
