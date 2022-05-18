package com.narola.hotelbooking.RazorpayPaymentGateway;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.narola.hotelbooking.Booking.Booking;
import com.narola.hotelbooking.Booking.BookingDAOMySQL;
import com.narola.hotelbooking.Booking.IBookingDAO;
import com.narola.hotelbooking.Customer.Customer;
import com.narola.hotelbooking.Hotel.SearchHotelCriteria;
import com.narola.hotelbooking.Utility.Employee;

/**
 * Servlet implementation class PaymentServlet
 */
public class PaymentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IBookingDAO bookingDAO = new BookingDAOMySQL();
		
		HttpSession session = request.getSession(true);
//		Customer customer = (Customer) session.getAttribute("user");
//		SearchHotel searchdetails = (SearchHotel) session.getAttribute("searchdetails");
		Booking booking = (Booking) session.getAttribute("booking");

		// Creating a HttpClient object
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

			String idSec = "rzp_test_uePllaIWhdcfHF:bfDdcirvf4jZWEcvIaAfvmn8";
			System.out.println(Base64.getEncoder().encodeToString(idSec.getBytes()));
			String authHeadVal = "Basic cnpwX3Rlc3RfdWVQbGxhSVdoZGNmSEY6YmZEZGNpcnZmNGpaV0VjdklhQWZ2bW44";

			HttpPost httpPost = new HttpPost("https://api.razorpay.com/v1/orders");
			httpPost.addHeader("content-type", "application/json");
			httpPost.addHeader("Authorization", authHeadVal);

			int amount=(int)booking.getTotalAmount();
//			int amount = 1000;
			String jsonstr = "{\r\n" 
			+ "  \"amount\": " + amount + " ,\r\n" 
			+ "  \"currency\": \"INR\",\r\n"
			+ "  \"receipt\": \"Receipt no. 12\"\r\n" + "}";
			
			HttpEntity httpEntity = new StringEntity(jsonstr);
			httpPost.setEntity(httpEntity);

			try (CloseableHttpResponse response1 = httpclient.execute(httpPost)) {

				System.out.println(response1.getCode() + " " + response1.getReasonPhrase());
				HttpEntity entity1 = response1.getEntity();
				// System.out.println(EntityUtils.toString(entity2));

				Map<String, String> responcemap = new HashMap<>();
				ObjectMapper mapper = new ObjectMapper();
				responcemap = mapper.readValue(EntityUtils.toByteArray(entity1), Map.class);
				if (responcemap.containsKey("error")) {
					System.out.println("error");
				}
				else {
					booking.setPaymentStatus("inprogress");
					booking.setPaymentMode("online");
					bookingDAO.updateData(booking);
					session.setAttribute("booking", booking);
					request.setAttribute("orderid", responcemap.get("id"));
					request.getRequestDispatcher("/customerside/Razorpayform.jsp").forward(request, response);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
}
