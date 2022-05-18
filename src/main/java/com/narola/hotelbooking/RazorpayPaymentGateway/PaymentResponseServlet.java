package com.narola.hotelbooking.RazorpayPaymentGateway;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.narola.hotelbooking.Authentication.UserDAO;
import com.narola.hotelbooking.Booking.Booking;
import com.narola.hotelbooking.Booking.BookingDAOMySQL;
import com.narola.hotelbooking.Booking.IBookingDAO;
import com.narola.hotelbooking.Customer.CustomerDAO;
import com.narola.hotelbooking.Utility.StatusConstants;

/**
 * Servlet implementation class PaymentResponseServlet
 */
public class PaymentResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IBookingDAO bookingDAO = new BookingDAOMySQL();
		
		int bookingid = Integer.parseInt(request.getParameter("bookingid"));
		Booking booking = bookingDAO.viewBooking(bookingid);
		
		HttpSession  session = request.getSession(true);
		session.setAttribute("user", CustomerDAO.getUserDataById(booking.getCustomerId()));
		
		Map<String, String[]> map=request.getParameterMap();
		if(map.containsKey("error[description]")) {
			booking.setPaymentStatus(StatusConstants.PAYMENT_FAIL);
			booking.setPaymentMode(StatusConstants.PAYMENT_MODE_ONLINE);
			booking.setBookingStatus(StatusConstants.BOOKING_NOT_CONFIRM);
			
			booking.setFailedReason(map.get("error[description]")[0]);
			ObjectMapper mapper = new ObjectMapper();				
			String jsonstr=mapper.writeValueAsString(map);
			booking.setErrorJson(jsonstr);
			bookingDAO.updateData(booking); 
			RequestDispatcher rd= request.getRequestDispatcher("/customerside/paymentfail.jsp");
			rd.forward(request, response);			
		}
		else if(map.containsKey("razorpay_order_id")) {
			
			booking.setPaymentMode(StatusConstants.PAYMENT_MODE_ONLINE);
			booking.setPaymentStatus(StatusConstants.PAYMENT_SUCCESS);
			booking.setBookingStatus(StatusConstants.BOOKING_CONFIRM);
			booking.setRazorOrderId(request.getParameter("razorpay_order_id"));
			booking.setRazorPaymentId(request.getParameter("razorpay_payment_id"));		
			booking.setFailedReason("");
			booking.setErrorJson("");
			bookingDAO.updateData(booking); 
			RequestDispatcher rd= request.getRequestDispatcher("/customerside/paymentsuccess.jsp");
			rd.forward(request, response);
		
		}
		else {
			booking.setPaymentStatus(StatusConstants.PAYMENT_PENDING);
			booking.setPaymentMode(StatusConstants.PAYMENT_MODE_ONLINE);
			booking.setBookingStatus(StatusConstants.BOOKING_NOT_CONFIRM);
			booking.setFailedReason("This payment is Cancled by user");
			booking.setErrorJson("");
			bookingDAO.updateData(booking); 
			RequestDispatcher rd= request.getRequestDispatcher("/customerside/paymentfail.jsp");
			rd.forward(request, response);			
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
