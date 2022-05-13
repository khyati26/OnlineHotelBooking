<%@page import="com.narola.hotelbooking.Utility.UserURLConstant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.io.File"%>
<%@page import="com.narola.hotelbooking.Hotel.Hotel"%>
<%@page import="com.narola.hotelbooking.Utility.Constant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="layouts/stylesheets.jsp">
	<jsp:param value="Available Rooms" name="title" />
</jsp:include>

<style type="text/css">

/* Next & previous buttons */
.prev1, .next1 {
	cursor: pointer;
	position: absolute;
	top: 50%;
	bottom: 30%;
	width: auto;
	padding: 16px;
	margin-top: -22px;
	color: white;
	font-weight: bold;
	font-size: 18px;
	transition: 0.6s ease;
	border-radius: 0 3px 3px 0;
	user-select: none;
}

.roomname {
	font-size: 14px;
	font-weight: bolder;
}
</style>
</head>
<body>

	<jsp:include page="layouts/topnav.jsp"></jsp:include><br>
	<div style="width: 100%; height: 120px; background: #EE5057"></div>
	<br>
	<h3 align="center">${hotel.name}</h3>
	<br>
	<%
		Hotel h = (Hotel) request.getAttribute("hotel");
	%>
	<!-- start slider section -->
	<div class="slider_section">
		<div class="container">
			<h6>${hotel.rating}state hotel</h6>
			<h6>
				<i class="fa fa-map-marker "></i> ${hotel.address}
			</h6>

			<div class="row">
				<div class="col-md-12">
					<div class="full">
						<div id="main_slider" class="carousel vert slide" data-ride="carousel" data-interval="5000">
							<div class="carousel-inner">
								<%
								File imageDir = new File(getServletContext().getRealPath("/") + Constant.HOTEL_FOLDER_PARENTPATH + h.getId());
								for (File imageFile : imageDir.listFiles()) {
									String imageFileName = imageFile.getName();
									if (imageFileName.contains("main")) {
								%>
								<div class="carousel-item active">
									<div class="row">
										<div class="col-md-12">
											<div class="slider_image">
												<img class="img-responsive" src="<%=Constant.HOTEL_FOLDER_PARENTPATH%><%=h.getId()%>\\<%=imageFileName%>"
													alt="#" style="width: 100%; height: 400px;" />
											</div>
										</div>
									</div>
								</div>
								<%
								} else {
								%>
								<div class="carousel-item">
									<div class="row">
										<div class="col-md-12">
											<div class="slider_image">
												<img class="img-responsive" src="<%=Constant.HOTEL_FOLDER_PARENTPATH%><%=h.getId()%>\\<%=imageFileName%>"
													alt="#" style="width: 100%; height: 400px;" />
											</div>
										</div>
									</div>
								</div>
								<%
								}
								}
								%>
							</div>
							<a style="background: black;" class="carousel-control-prev prev1" href="#main_slider" role="button" data-slide="prev"> 
								<i class="fa fa-angle-left"></i>
							</a>
							<a style="background: black;" class="carousel-control-next next1" href="#main_slider" role="button" data-slide="next"> 
								<i class="fa fa-angle-right"></i>
							</a>
						</div>
					</div>
				</div>
			</div>

			<br>
			<h4 class="text-uppercase tm-font-semibold tm-sidebar-item-title">
				Amenities and Services:</h4>
			<ul>
				<c:forTokens items="${hotel.services }" delims="," var="service">
					<li><h6>${service}</h6></li>
				</c:forTokens>
			</ul>
			<br>
			<div class="row">
				<div class="col-6">
					<h6>Email ID: ${hotel.emailId }</h6>
				</div>
				<div class="col-6" align="right">
					<h6>${sessionScope.searchdetails.adult } Adults
						${sessionScope.searchdetails.children } Children
						${sessionScope.searchdetails.room } Room</h6>
					<h6>Best for ${sessionScope.searchdetails.days } night</h6>
				</div>
			</div>

			<hr>
			<table border="1" style="width: 100%; border-color: white; border-bottom: 5px solid #5bbaff;">				
				<thead align="center" style="color: white; background: #EE5057; height: 50px;">
					<th>Room type</th>
					<th>Max Parsons</th>
					<th>Price</th>
					<th>Inclusions</th>
					<th>Select rooms</th>
					<th></th>
				</thead>
				<tbody align="center" style="height: 100px">
					<c:forEach items="${roomlist }" var="room" begin="0" varStatus="roomloop">
						<tr>
							<td>
								<a href="<%= request.getContextPath() + UserURLConstant.ROOM_VIEW_URL %>?id=${room.id}" class="roomname" style="color: blue; text-decoration: underline;">
									${room.name }
								</a>
							</td>
							<td>${room.maxcapacity }</td>
							<td id="price${room.id}">&#8377; ${room.price }</td>
							<td>${room.inclusions }${room.availableroom}</td>
							<td>
								<select name="qty" id="qty${roomloop.index}" onchange="bookroom(this,${room.id },${roomloop.index})">
									<option selected="selected" value="0">0</option>
									<c:choose>
										<c:when test="${room.availableroom > sessionScope.searchdetails.room}">
											<c:forEach begin="1" end="${sessionScope.searchdetails.room }" varStatus="loop">
												<option value="${loop.index }">${loop.index }</option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach begin="1" end="${room.availableroom }" varStatus="loop">
												<option value="${loop.index }">${loop.index }</option>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</select>
							</td>
							<c:if test="${roomloop.index == 0 }">
								<td rowspan="${roomlist.size() }">
									<h6 id="totprice"></h6>
									<form method="post" action="<%=request.getContextPath()+ UserURLConstant.BOOKING_DETAILS_URL%>">
										<input type="hidden" name="hotelid" value="${hotel.id }">
										<input type="hidden" name="roomid" id="roomid"> 
										<input type="hidden" name="totprice2" id="totprice2">
										<button type="submit"  class="btn btn-primary" >
											<b>Book Now</b>
										</button>
									</form>
								</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot align="center" >
					<th></th><th></th><th></th><th></th>
					<th><button class="btn btn-info" type="reset" id="reset1" >Clear</button></th>
				</tfoot>				
			</table>
			<br>
			<br>
		</div>
		<jsp:include page="layouts/footer.jsp"></jsp:include>
	</div>
	<!-- end slider section -->

<jsp:include page="layouts/scripts.jsp"></jsp:include>

<script type="text/javascript">
	var selectids = [];
	var roomqtymap= new Map();	
	$("#reset1").click(function(){
		roomqtymap.clear();
		console.log(roomqtymap);
		selectids.length = 0;
		console.log(selectids);
		document.getElementById("totprice").innerHTML="";
		for (let i = 0; i < ${roomlist.size()}; i++) 
		{
			$("#qty"+i).val("0");
			$("#qty"+i).prop('disabled', false);
			$("#qty"+i+" option").each(function(){
				$(this).attr("disabled",false);
			});
		}
		 $('#btnbook').prop('disabled', true);
	});
		
	function bookroom(e,roomid,selectid) {
		var price = document.getElementById("price"+roomid).innerHTML;						
		//$("#roomid").val(roomid);
		//document.getElementById("totprice").innerHTML = "&#8377; " + e.value * price.substring(2) + "<br>" + e.value + " rooms";
		$("#roomqty").val(e.value);			
		var  sum  = Number(e.value);
		roomqtymap.set(roomid,e.value);
		$("#roomid").val(JSON.stringify(Array.from(roomqtymap.entries())));
		var totalprice= Number(0);
		var totalqty = Number(0);
		roomqtymap.forEach(function(value,key){
			var price = document.getElementById("price"+key).innerHTML;
			totalprice = totalprice + (Number(value) * Number(price.substring(2)));
			totalqty = totalqty + Number(value);			
		});
		$("#totprice2").val(totalprice);
		document.getElementById("totprice").innerHTML = "&#8377; " + totalprice + "<br>" + totalqty + " rooms";	
		
		selectids.push(selectid);
		console.log(roomqtymap);
		console.log(selectids);
		var requiredrooms = ${sessionScope.searchdetails.room};
				
		for (let i = 0; i < ${roomlist.size()}; i++) 
		{
			if(parseInt($("#qty"+i).val()) != 0){
				if(selectid != i){
					sum = sum + parseInt($("#qty"+i).val());
					/* roomqtymap.set(roomid,e.value);
					selectids.push(i); */
				}

				console.log("i= "+i+" selectid= "+selectid);				
			}
			if(selectid != i){
				$("#qty"+i+" option").each(function(){
					if((Number($(this).val()) + sum) > requiredrooms){
						$(this).attr("disabled","disabled");
					}
				});	
			}
			if(sum == requiredrooms){
				console.log("break" + selectids);
				 $('#btnbook').prop('disabled', false);
				break;
			}
		}		    
		for(let i = 0; i < ${roomlist.size()}; i++){
			if(!selectids.includes(i) && sum == requiredrooms){
				$("#qty"+i).prop('disabled', 'disabled');
			}
			else{
				$("#qty"+i).prop('disabled', false);
			}
		}
	console.log(roomqtymap);
	}	
		
	$(document).ready(function() {
		$("#sidebar").mCustomScrollbar({
			theme : "minimal"
		});
		
		$('#dismiss, .overlay').on('click', function() {
			$('#sidebar').removeClass('active');
			$('.overlay').removeClass('active');
		});

		$('#sidebarCollapse').on('click', function() {
			$('#sidebar').addClass('active');
			$('.overlay').addClass('active');
			$('.collapse.in').toggleClass('in');
			$('a[aria-expanded=true]').attr('aria-expanded', 'false');
		});						
	});
</script>
</body>
</html>

