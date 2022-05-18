package com.narola.hotelbooking.Booking;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchBookingServlet
 */
public class SearchBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IBookingDAO bookingDAO = new BookingDAOMySQL();
		
		System.out.println(request.getParameter("checkin") + " "+request.getParameter("checkout"));
		System.out.println(request.getParameter("bookingstatus") + " "+request.getParameter("paymentstatus"));
		
		BookingFilterParam filterParam = new BookingFilterParam();
		
		if(!request.getParameter("bookingid").isEmpty()) {
			filterParam.setBookingId(Integer.parseInt(request.getParameter("bookingid")));
		}
		else {
			if(!request.getParameter("checkin").isEmpty()) {
				filterParam.setCheckIn(request.getParameter("checkin"));
			}
			 if(!request.getParameter("checkout").isEmpty()) {
				filterParam.setCheckOut(request.getParameter("checkout"));
			}
			 if(!request.getParameter("bookingstatus").isEmpty()) {
				filterParam.setBookingStatus(request.getParameter("bookingstatus"));
				
			}
			 if(!request.getParameter("paymentstatus").isEmpty()) {
				filterParam.setPaymentStatus(request.getParameter("paymentstatus"));
			}
		}
		List<Booking> books=bookingDAO.searchBookingData(filterParam);
		request.setAttribute("bookingdata", bookingDAO.searchBookingData(filterParam));

		RequestDispatcher rd=request.getRequestDispatcher("admin/booking/ShowAllBooking.jsp");
		rd.forward(request, response);
	}

}
