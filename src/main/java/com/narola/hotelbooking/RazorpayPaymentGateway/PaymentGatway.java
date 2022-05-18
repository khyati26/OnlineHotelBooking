package com.narola.hotelbooking.RazorpayPaymentGateway;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.narola.hotelbooking.Booking.Booking;
import com.narola.hotelbooking.Booking.BookingDAOMySQL;
import com.narola.hotelbooking.Booking.IBookingDAO;
import com.narola.hotelbooking.Utility.StatusConstants;

public class PaymentGatway {

	public static final String  URL = "https://api.razorpay.com/v1/orders";
	public static final String KEY_ID="rzp_test_uePllaIWhdcfHF";
	public static final String KEY_SECRET = "bfDdcirvf4jZWEcvIaAfvmn8";
	
	public static int createOrder(Booking booking) throws IOException {
		
		IBookingDAO bookingDAO = new BookingDAOMySQL();
		
		ObjectMapper mapper = new ObjectMapper();
		
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

			String idSec = KEY_ID+":"+KEY_SECRET;
			
			String authHeadVal = "Basic "+Base64.getEncoder().encodeToString(idSec.getBytes());
//					cnpwX3Rlc3RfdWVQbGxhSVdoZGNmSEY6YmZEZGNpcnZmNGpaV0VjdklhQWZ2bW44";

			HttpPost httpPost = new HttpPost(URL);
			httpPost.addHeader("content-type", "application/json");
			httpPost.addHeader("Authorization", authHeadVal);

//			int amount=(int)booking.getTotalAmount();
//			int amount = 1000;
//			String jsonstr = "{\r\n" 
//			+ "  \"amount\": " + amount + " ,\r\n" 
//			+ "  \"currency\": \"INR\",\r\n"
//			+ "  \"receipt\": \"Receipt no. 12\"\r\n" + "}";
			OrderEntity orderEntity = new OrderEntity();
			orderEntity.setAmount((int)booking.getTotalAmount());
			orderEntity.setCurrency("INR");
			orderEntity.setReceipt("booking no. "+booking.getId());
			
			String jsonstr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderEntity);
			HttpEntity httpEntity = new StringEntity(jsonstr);
			httpPost.setEntity(httpEntity);

			try (CloseableHttpResponse response1 = httpclient.execute(httpPost)) {

				HttpEntity entity1 = response1.getEntity();

				Map<String, String> responcemap = new HashMap<>();
				responcemap = mapper.readValue(EntityUtils.toByteArray(entity1), Map.class);
				if (responcemap.containsKey("error")) {
					System.out.println("error");
				}
				else {
					booking.setPaymentStatus(StatusConstants.PAYMENT_IN_PROGRESS);
					booking.setPaymentMode(StatusConstants.PAYMENT_MODE_ONLINE);
					booking.setRazorOrderId(responcemap.get("id"));
					bookingDAO.updateData(booking);
//					session.setAttribute("booking", booking);
//					request.setAttribute("orderid", responcemap.get("id"));
//					request.getRequestDispatcher("/customerside/Razorpayform.jsp").forward(request, response);
				}

				return response1.getCode();
			}
		}
	}
}
