<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.hotelbooking.Room.model.Room"%>
<%@page import="com.narola.hotelbooking.Utility.Constant"%>
<%@ page import="java.io.File"%>
<%@page import="com.narola.hotelbooking.Utility.UserURLConstant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="layouts/stylesheets.jsp">
	<jsp:param value="Room" name="title" />
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
.bd-example {
    padding: 1.5rem;
    margin-right: 0;
    margin-left: 0;
    border-width: 0.2rem;
}
</style>
</head>
<body>
	<%
		Room room = (Room) request.getAttribute("room");	
	%>
	<jsp:include page="layouts/topnav.jsp"></jsp:include><br>
	<div style="width: 100%; height: 120px; background: #EE5057"></div>
	<br>
	<h3 align="center"><%=room.getName() %> Room</h3>
	<br>
	<div class="slider_section">
		<div class="container">
			<h6>&#8377; <%=room.getPrice() %> x 1 night per room</h6>
			<h6>Max <%=room.getMaxcapacity() %> persons can sleep in 1 room</h6>
			<div class="row">
				<div class="col-md-12">
					<div class="full">
						<div id="main_slider" class="carousel vert slide" data-ride="carousel" data-interval="5000">
							<div class="carousel-inner">
								<%
	                             	File imageDir = new File(getServletContext().getRealPath("/") + Constant.ROOM_FOLDER_PARENTPATH + room.getId());
	          					  	for(File imageFile : imageDir.listFiles()){
									 	String imageFileName = imageFile.getName();
									 	if(imageFileName.contains("main")){
								  %>
								<div class="carousel-item active">
									<div class="row">
										<div class="col-md-12">
											<div class="slider_image">
												<img class="img-responsive"	src="<%=Constant.ROOM_FOLDER_PARENTPATH%><%=room.getId() %>\\<%=imageFileName%>"
													alt="#" style="width: 100%; height: 400px;" />
											</div>
										</div>
									</div>
								</div>
								<%
										}else{	
								 %>
								<div class="carousel-item">
									<div class="row">
										<div class="col-md-12">
											<div class="slider_image">
												<img class="img-responsive" src="<%=Constant.ROOM_FOLDER_PARENTPATH%><%=room.getId() %>\\<%=imageFileName%>"
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
				Room Description:
			</h4>
			<ul>
				<ol><%=room.getDescription() %></ol>
			</ul>
			<br>
			<h4 class="text-uppercase tm-font-semibold tm-sidebar-item-title">
			Inclusion:</h4>
			<ul>
				<ol><%=room.getInclusions() %></ol>
			</ul>
			<div class="bd-example">
				<a class="btn btn-primary" style="background: #007bff; border-color: #007bff;" href="<%=request.getContextPath() + UserURLConstant.AVAILABLE_ROOMS_URL%>?hotelid=<%=room.getHotelID() %>" >
					Back
				</a>
				<!-- <button class="btn btn-primary" style="background: #007bff; border-color: #007bff;">Back</button> -->     <!-- <button class="btn btn-primary">Book Now</button> -->
			</div>
			
		</div>
		<jsp:include page="layouts/footer.jsp"></jsp:include>
	</div>
	<jsp:include page="layouts/scripts.jsp"></jsp:include>

	<script type="text/javascript">
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