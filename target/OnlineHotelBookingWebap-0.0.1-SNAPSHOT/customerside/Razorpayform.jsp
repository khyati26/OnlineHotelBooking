<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<body>
	<form method="POST" id="paymentform" action="https://api.razorpay.com/v1/checkout/embedded">
	  <input type="hidden" name="key_id" value="rzp_test_uePllaIWhdcfHF">
	  <input type="hidden" name="amount" value=${sessionScope.booking.totalAmount }>
	  <input type="hidden" name="order_id" value="${sessionScope.booking.razorOrderId}">
	  <input type="hidden" name="name" value="Level">
	  <input type="hidden" name="description" value="Online Hotel Booking">
	  <input type="hidden" name="image" value="https://cdn.razorpay.com/logos/BUVwvgaqVByGp2_large.png">
	  <input type="hidden" name="prefill[name]" value="${sessionScope.user.firstName } ${session.user.lastName}">
	  <input type="hidden" name="prefill[contact]" value="${sessionScope.user.mobile }">
	  <input type="hidden" name="prefill[email]" value="${sessionScope.user.email }">
	  <input type="hidden" name="callback_url" value="http://localhost:8080/OnlineHotelBookingWebap/paymentresponse?bookingid=${sessionScope.booking.id}">
	  <input type="hidden" name="cancel_url" value="http://localhost:8080/OnlineHotelBookingWebap/paymentresponse?bookingid=${sessionScope.booking.id}">
	</form> 

	<!-- <form method="POST" id="paymentform" action="https://api.razorpay.com/v1/checkout/embedded">
	  <input type="hidden" name="key_id" value="rzp_test_uePllaIWhdcfHF">
	  <input type="hidden" name="amount" value=1515>
	  <input type="hidden" name="order_id" value="order_JJ6stltT4MYa4N">
	  <input type="hidden" name="name" value="Level">
	  <input type="hidden" name="description" value="Online Hotel Booking">
	  <input type="hidden" name="image" value="https://cdn.razorpay.com/logos/BUVwvgaqVByGp2_large.png">
	  <input type="hidden" name="prefill[name]" value="kkk">
	  <input type="hidden" name="prefill[contact]" value="9874563210">
	  <input type="hidden" name="prefill[email]" value="djfkj@j.vdf">
	  <input type="hidden" name="callback_url" value="http://localhost:8080/OnlineHotelBookingWebap/paymentresponse?bookingid=49">
	  <input type="hidden" name="cancel_url" value="http://localhost:8080/OnlineHotelBookingWebap/paymentresponse?bookingid=49">
	  <button>Submit</button>
	</form>  -->
	<script type="text/javascript">
		document.getElementById("paymentform").submit();
	</script>
</body>
</html>