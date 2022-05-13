package com.narola.hotelbooking.Hotel;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.Local;

import com.narola.hotelbooking.Customer.Customer;
import com.narola.hotelbooking.Customer.CustomerDAO;
import com.narola.hotelbooking.Utility.UserURLConstant;

/**
 * Servlet implementation class SearchHotelServlet
 */
public class SearchHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html");
		
		SearchHotel searchhotel=new SearchHotel();
		searchhotel.setCity(request.getParameter("city"));
		searchhotel.setCheckIn(request.getParameter("check-in"));
		searchhotel.setCheckOut(request.getParameter("check-out"));
		searchhotel.setAdult(Integer.parseInt(request.getParameter("adult")));
		searchhotel.setChildren(Integer.parseInt(request.getParameter("children")));
		searchhotel.setRoom(Integer.parseInt(request.getParameter("room")));
		
		LocalDate startdate = LocalDate.parse(searchhotel.getCheckIn());
		LocalDate enddate = LocalDate.parse(searchhotel.getCheckOut());
		searchhotel.setDays((int)ChronoUnit.DAYS.between(startdate, enddate));
		
		HttpSession session = request.getSession(true);			
		session.setAttribute("searchdetails", searchhotel);
		
		
		request.setAttribute("hotels", SearchHotelDAO.getCityWiseHotels(searchhotel));
		
		RequestDispatcher rd=request.getRequestDispatcher("customerside/SearchResult_ListofHotels.jsp");
		rd.forward(request, response);
	}

}
