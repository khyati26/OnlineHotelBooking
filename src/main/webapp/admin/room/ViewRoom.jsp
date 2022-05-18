<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.hotelbooking.Room.model.Room"%>
<%@page import="com.narola.hotelbooking.Hotel.Hotel"%>
<%@ page import="java.io.File" %>
<%@page import="com.narola.hotelbooking.Utility.Constant"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="../layouts/stylesheets.jsp">
  	<jsp:param value="List of Hotels" name="title"/>
</jsp:include>

</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">	
  		<jsp:include page="../layouts/topnav.jsp"></jsp:include>
	
		<jsp:include page="../layouts/sidemenu.jsp"></jsp:include>
		
			<%
				Room h = (Room) request.getAttribute("room");
				HttpSession session1=request.getSession();
				Hotel h2 = (Hotel) session1.getAttribute("hotel");
			%>
			<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<h1>Hotel Name: <%=h2.getName()%> </h1>
					<h3 align="center">View Room</h3>	
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row" >
						<div class="col-md-12 ">
							<div class="card " >
								<strong>
								<table >
									<ul>
									<tr>
										<td><li >Room Name:  <%=h.getName()%></li></td>
										<td><li>Max Capacity:  <%=h.getMaxcapacity()%> Stare</li></td>
									</tr>
									<tr>
										<td><li>Inclusions :   <%=h.getInclusions()%></li></td>
										<td><li>Price:  <%=h.getPrice()%></li></td>
									</tr>
									<tr>
										<td><li>Description :  <%=h.getDescription()%></li></td>
										<td><li>Room Qty. : <%=h.getQty()%> </li></td>
									</tr>
									
									
								</table>
								</strong>
								<br><br>
								<h3>Images : </h3>
								 <div class="row">
								 	<%
									 	File imageDir = new File(getServletContext().getRealPath("/") + Constant.ROOM_FOLDER_PARENTPATH + h.getId());	
										int j=0;
								 		for(File imageFile : imageDir.listFiles()){
			    						String imageFileName = imageFile.getName();
								 	%>
										<div class="column col-6">
										      <img class="demo cursor" src="<%=Constant.ROOM_FOLDER_PARENTPATH%><%=h.getId() %>\\<%=imageFileName%>" style="width:300px;height:300px" onclick="currentSlide(<%= ++j %>)" alt="image">
										 	<br><br>
										</div>
									<%
										}	
									%>
  								</div>
							</div>
							<!-- /.card -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
  
  		<jsp:include page="../layouts/footer.jsp"></jsp:include>
 		
 	</div>
<!-- ./wrapper -->
	
<jsp:include page="../layouts/scripts.jsp"></jsp:include>


</body>
</html>
