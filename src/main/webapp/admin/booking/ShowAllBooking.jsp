<%@page import="com.narola.hotelbooking.Utility.StatusConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<head>
  <jsp:include page="../layouts/stylesheets.jsp">
  	<jsp:param value="List of room Bookings " name="title"/>
</jsp:include>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
  <div class="wrapper">	
  	<jsp:include page="../layouts/topnav.jsp"></jsp:include>
	
	<jsp:include page="../layouts/sidemenu.jsp"></jsp:include>
  	<!-- Content Wrapper. Contains page content -->
	  <div class="content-wrapper">
	    <!-- Content Header (Page header) -->
	    <div class="content-header">
	      <div class="container-fluid">
	        <div class="row mb-2">
	          <div class="col-sm-6">
	            <h1 class="m-0">List of Bookings</h1>
	          </div><!-- /.col -->
	        </div><!-- /.row -->
	      </div><!-- /.container-fluid -->
	    </div>
	    <!-- /.content-header -->
	    <!-- Main content -->
	    <section class="content" style="background: white;">
	    <div class="container-fluid px-1 py-5 mx-auto">
			<div class="rowd-flex ">
			<div class="col-12 text-center">
			<form action="searchbooking" method="POST" >
				<table style="background: #D3D3D3" >
					<tr><td colspan="4"><label>Booking ID</label><input id="bookingid" type="number" name="bookingid"></td></tr>
					<tr>
						<td><label>Check-In date</label><input type="date" name="checkin"></td>
						<td><label>Check-Out date</label><input type="date" name="checkout"></td>
						<td>
							<label>Booking Status</label>
							<select name="bookingstatus">
								<option value="">--Select Booking Status--</option>
								<option><%=StatusConstants.BOOKING_CONFIRM %></option>
								<option><%=StatusConstants.BOOKING_NOT_CONFIRM %></option>
								<option><%=StatusConstants.BOOKING_CANCLE %></option>							
							</select>
						</td>
						<td>
							<label>Payment Status</label>
							<select name="paymentstatus">
								<option value="">--Select Payment Status--</option>
								<option><%=StatusConstants.PAYMENT_SUCCESS %></option>
								<option><%=StatusConstants.PAYMENT_FAIL %></option>
								<option><%=StatusConstants.PAYMENT_IN_PROGRESS %></option>
								<option><%=StatusConstants.PAYMENT_PENDING %></option>							
							</select>
						</td>
						<td><input type="submit" class="btn btn-primary" name="Search" value="Search"><br></td>
					</tr>
				</table>
			</form>
			</div>
			<br><br>
				<div class=" col-12 text-center">	
					<div align="right">
						<form id="sortingform" method="post" action="sortbooking">
							<label>Sort By : </label>
							<select name="columnid" onchange="myfunction()">
								<c:forEach items="${ColumnOrder}" var="colunm">
									<c:if test="${colunm.key > 3 }">
										<optgroup label="Bookindstatus">									
									</c:if>
									<c:if test="${colunm.key > 3 }"></c:if>
									<option value="${colunm.key }">${colunm.value}</option>	
								</c:forEach>
								<%-- <option>ID</option>
								<option>Customer Name</option>
								<option>Check-In Date</option>
								<optgroup label="Bookindstatus">
									<option><%=StatusConstants.BOOKING_CONFIRM %></option>
									<option><%=StatusConstants.BOOKING_NOT_CONFIRM %></option>
									<option><%=StatusConstants.BOOKING_CANCLE %></option>
								</optgroup>
								<optgroup label="paymentstatus">
									<option><%=StatusConstants.PAYMENT_SUCCESS %></option>
									<option><%=StatusConstants.PAYMENT_PENDING %></option>
									<option><%=StatusConstants.PAYMENT_IN_PROGRESS %></option>
									<option><%=StatusConstants.PAYMENT_FAIL %></option>
								</optgroup> --%>
							</select>
						</form>
					</div>
					<table id="example" class="table table-striped table-bordered" style="width: 100%">
						<thead>
							<tr>
								<th>ID</th>
								<th>Customer Name</th>
								<th>Destination</th>
								<th>Booked hotel & room</th>
								<th>Check-In & Check-Out
								<th>Booking Status</th>
								<th>Payment Status</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${bookingdata }" var="data" begin="0" varStatus="count">
							<tr>
								<td>${count.index + 1 }</td>
								<td>${data.customer.firstName } ${data.customer.lastName }</td>
								<td>${data.hotel.city }, ${data.hotel.state }.</td>
								<td>Hotel: ${data.hotel.name }<br>
								Room:<br>
									<c:choose>
										<c:when test="${data.roomList.size() == 1 }">
											${data.roomList[0].name } X ${data.roomList[0].qty }
										</c:when>
									    <c:otherwise>
									    	<c:forEach items="${data.roomList }" var="room">
									    		${room.name } X ${room.qty }<br>
									    	</c:forEach>											
										</c:otherwise> 
									</c:choose>
								</td>
								<td>${data.checkIn } to ${data.checkOut }</td>
								<td>${data.bookingStatus }</td>
								<td>
									<c:if test="${data.paymentStatus.equals('success')}">
										<span  class="badge badge-success"><big>Success</big></span>
									</c:if>
									<c:if test="${data.paymentStatus.equals('fail')}">
										<span class="badge badge-danger"><big>Fail</big></span>
									</c:if>
									<c:if test="${data.paymentStatus.equals('pending')}">
										<span class="badge badge-warning"><big>Pending</big></span>
									</c:if>

								</td>
							</tr>
						</c:forEach>																
						</tbody>
						<tfoot>
							<tr>
								<th>ID</th>
								<th>Customer Name</th>
								<th>Destination</th>
								<th>Booked hotel & room</th>
								<th>Booking Status</th>
								<th>Payment Status</th>									
							</tr>
						</tfoot>
					</table>
				</div>
			</div>			
	    </div>
	    </section>
	    <!-- /.content -->
	  </div>
	  <!-- /.content-wrapper -->

	   <jsp:include page="../layouts/footer.jsp"></jsp:include>
 		
 	</div>
<!-- ./wrapper -->
	<%	
		int urllength=request.getRequestURL().length();
		int urilength=request.getRequestURI().length();
		String finalurl=request.getRequestURL().substring(0,urllength-urilength);
	%>
	<jsp:include page="../layouts/scripts.jsp"></jsp:include>
	<script>
	function myfunction() {
		document.getElementById("sortingform").submit();
		<%--  window.location.href="<%=finalurl+request.getContextPath()%>/sortbooking?columnid=2"; --%> 
	}
	$(document).ready(function() {
		$('#example').DataTable({
			"searching": false,
			"lengthChange": true,
 		      "ordering": false,
 		     /*  "info": true,
		      "autoWidth": false, */
		      "responsive": true,
		});
	});
</script>
</body>
</html>