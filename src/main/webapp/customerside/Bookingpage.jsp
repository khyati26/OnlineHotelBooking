<%@page import="com.narola.hotelbooking.Utility.UserURLConstant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.narola.hotelbooking.Utility.Constant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="layouts/stylesheets.jsp">
	<jsp:param value="Book Rooms" name="title" />
</jsp:include>
<!-- Font Awesome -->
<link rel="stylesheet"
	href="admin/layouts/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<style>
#progressbar {
	width: 100%;
	margin-bottom: 30px;
	overflow: hidden;
	color: lightgrey;
}

#progressbar .active {
	color: #5bc0de
}

#progressbar li {
	list-style-type: none;
	font-size: 15px;
	width: 25%;
	float: left;
	position: relative;
	font-weight: 400;
	text-align: center;
}

.progressbar li:first-child:after {
	content: none;
}

#progressbar #account:before {
	font-family: FontAwesome;
	content: "\f058"
}

#progressbar #personal:before {
	font-family: FontAwesome;
	content: "2"
}

#progressbar #payment:before {
	font-family: FontAwesome;
	content: "3"
}

#progressbar li:before {
	width: 50px;
	height: 50px;
	line-height: 45px;
	display: block;
	font-size: 20px;
	color: #ffffff;
	background: lightgray;
	border-radius: 50%;
	margin: 0 auto 10px auto;
	text-align: center;
	padding: 2px
}

#progressbar li:after {
	content: '';
	width: 100%;
	height: 2px;
	background: lightgray;
	position: absolute;
	left: 0;
	top: 25px;
	z-index: -1
}

#progressbar li.active:before, #progressbar li.active:after {
	background: #5bc0de
}

.progress {
	height: 20px
}

.progress-bar {
	background-color: #5bc0de
}

.card-horizontal {
	display: flex;
	flex: 1 1 auto;
}

.mylable {
	display: block;
	font-weight: bold;
	margin-bottom: 2px;
}
</style>
</head>
<body>
	<jsp:include page="layouts/topnav.jsp"></jsp:include><br>
	<div style="width: 100%; height: 120px; background: #EE5057"></div>
	<br><br>

	<!-- start slider section -->
	<div class="slider_section">
		<div align="center" style="margin-left: 200px;">
			<!-- progressbar -->
			<ul id="progressbar">
				<li class="active" id="account"><strong>Your Selection</strong></li>
				<li id="personal" class="active"><strong>Your Details</strong></li>
				<li id="payment"><strong>Final step</strong></li>
			</ul>
		</div>
		<div class="container">
			<div class="row">
				<div class="col" style="background-color: #f7f7f7; padding: 30px;">
					<h5>Your Booking Details</h5>
					<br>
					<div class="row" style="background-color: white; padding: 10px;">
						<div class="col">
							<table>
								<tr>
									<td><lable><strong>Check-in</strong></lable></td>
									<td><lable><strong>Check-out</strong></lable></td>
								</tr>
								<tr>
									<td><h4>${sessionScope.searchdetails.checkIn  }</h4></td>
									<td><h4>${sessionScope.searchdetails.checkOut }</h4></td>
								</tr>
								<tr>
									<td>From 13:00</td>
									<td>Until 11:00</td>
								</tr>
							</table><br> 
							Total length of Stay:<br> <strong>${sessionScope.searchdetails.days }  nights</strong>
							<hr>
							<strong>Your group</strong><br>
							${sessionScope.searchdetails.adult } Adults,
							${sessionScope.searchdetails.children } Children
							<hr>
							<strong>Your selected rooms:</strong><br>
							<ul>
								<c:forEach var="room" items="${roomlist }">
									<li>${room.name}${room.availableroom } x ${room.price}</li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<br>
					<div class="row" style="padding: 10px; background-color: #ebf3ff;">
						<div class="col">
							<table style="width: 100%">
								<tr>
									<td><strong>Price</strong></td>
									<td><strong>&#8377; ${totalprice }</strong></td>
								</tr>
								<tr>
									<td>(For ${sessionScope.searchdetails.days } nights)</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="col-8 centered container">
					<div class="card" style="padding: 15px;">
						<div class="card-horizontal">
							<div class="img-square-wrapper">
								<c:set value="${hotel.id}\\" var="hoteldir" />
								<img src="<%=Constant.HOTEL_FOLDER_PARENTPATH%>${hoteldir}${hotel.image}" width="300" height="200" alt="Card image cap">
							</div>
							<div class="card-body">
								<div class="row">
									<div class="col-9">
										<h4 class="card-title">${hotel.name}</h4>
									</div>
								</div>
								<tr>
									<td><i class="fa fa-map-marker "></i> ${hotel.address}</td>
								</tr>
								<tr>
									<td><p class="card-text">${hotel.services}</p></td>
								</tr>
								<tr>
									<td><h5>${hotel.rating }Stare Hotel</h5></td>
								</tr>
								</table>
							</div>
						</div>
					</div><br>
					<div class="card" style="background-color: #ebf3ff;">
						<div class="card-header" style="background-color: #ebf3ff;">
							<h2 style="font-size: 20px; font-weight: 700;">Enter Your Details</h2>
						</div>
						<div class="card-body">
							<form>
								<div class="form-row">
									<div class="form-group col-md-6">
										<label class="mylable">First Name</label> 
										<input type="text" class="form-control" value="${sessionScope.user.firstName }" required>
									</div>
									<div class="form-group col-md-6">
										<label class="mylable">Last Name</label> 
										<input type="text" class="form-control" value="${sessionScope.user.lastName }" required>
									</div>
								</div>
								<div class="form-group">
									<label class="mylable">Email address</label> 
									<input type="email" class="form-control" aria-describedby="emailHelp" value="${sessionScope.user.email }" required> 
									<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
								</div>
								<div class="form-group">
									<label class="mylable">Mobile Number</label> 
									<input type="text" class="form-control" value="${sessionScope.user.mobile }" required>
								</div>
							</form>
						</div>
					</div>
				</div><br>
			</div><br>	<br>
			<div align="center">
				<form method="post" action="<%= request.getContextPath() + UserURLConstant.ADD_BOOKING_URL%>">
					<input type="hidden" name="hotelid" value="${hotel.id }">
					<input type="hidden" name="totalprice" value="${totalprice }" >
					<button type="submit" class="btn btn-primary" style="font-size: 12px; font-weight: bold;">Next: Final Step</button>		
				</form>
		   </div>
		</div>
	</div><br><br>
	<jsp:include page="layouts/footer.jsp"></jsp:include>

	<jsp:include page="layouts/scripts.jsp"></jsp:include>

</body>
</html>