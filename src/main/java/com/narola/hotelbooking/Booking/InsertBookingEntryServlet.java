package com.narola.hotelbooking.Booking;

import java.awt.image.TileObserver;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.narola.hotelbooking.Customer.Customer;
import com.narola.hotelbooking.Hotel.SearchHotelCriteria;
import com.narola.hotelbooking.RazorpayPaymentGateway.PaymentGatway;
import com.narola.hotelbooking.Room.model.Room;
import com.narola.hotelbooking.Utility.ConnectDB;
import com.narola.hotelbooking.Utility.StatusConstants;
import com.narola.hotelbooking.Utility.UserURLConstant;

/**
 * Servlet implementation class InsertBookingEntryServlet
 */
public class InsertBookingEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		IBookingDAO bookingDAO = new BookingDAOMySQL();
		
		HttpSession session = request.getSession(true);

		Customer customer = (Customer) session.getAttribute("user");
		SearchHotelCriteria searchdetails = (SearchHotelCriteria) session.getAttribute("searchdetails");
		List<Room> roomlist = (List<Room>) session.getAttribute("roomlist");
		
		Booking booking = new Booking();
		booking.setAdults(searchdetails.getAdult());
		booking.setChildren(searchdetails.getChildren());
		booking.setCheckIn(searchdetails.getCheckIn());
		booking.setCheckOut(searchdetails.getCheckOut());
		booking.setCustomerId(customer.getId());
		booking.setHotelId(Integer.parseInt(request.getParameter("hotelid")));
		booking.setTotalAmount(Double.parseDouble(request.getParameter("totalprice")));
		booking.setPaymentMode(StatusConstants.PAYMENT_MODE_ONLINE);
		booking.setPaymentStatus(StatusConstants.PAYMENT_PENDING);
		
		int totalroomqty = 0;
		for (Room r : roomlist) {
			totalroomqty = totalroomqty + r.getAvailableroom();
		}
		booking.setTotalRoomQty(totalroomqty);

		int bookingid = bookingDAO.createBooking(booking,roomlist);
		booking.setId(bookingid);
		
		if(PaymentGatway.createOrder(booking) == 200) {
			session.setAttribute("booking", booking);
			session.setMaxInactiveInterval(10*60);
			request.getRequestDispatcher("/customerside/Razorpayform.jsp").forward(request, response);
		}
		else {
			System.out.println("error");
		}
		
	}
}
