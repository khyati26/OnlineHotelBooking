package com.narola.hotelbooking.Booking;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListBookingServlet
 */
public class ListBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static List<Booking> bookingList=null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bookingList = BookingDAO.showallbooking();
		request.setAttribute("bookingdata", bookingList);
		System.out.println(SortingColumnOrder.columnOrder);
		request.setAttribute("ColumnOrder", SortingColumnOrder.columnOrder);
		RequestDispatcher rd=request.getRequestDispatcher("admin/booking/ShowAllBooking.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}