<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
     
<%@ page import="java.io.File" %>
<%@page import="com.narola.hotelbooking.Hotel.Hotel"%>
<%@page import="com.narola.hotelbooking.Utility.Constant"%>
<%@page import="com.narola.hotelbooking.Hotel.CancellationRules"%>
<%@ page import="java.util.List" %>

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
				Hotel h = (Hotel) request.getAttribute("hotel");
		%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
							<h1>Hotel Name: <%=h.getName()%> </h1>
						<div class="col-sm-6">
						</div>
					</div>
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
										<td><li >Email ID:  <%=h.getEmailId()%></li></td>
										<td><li>Stare rating:  <%=h.getRating()%> Stare</li></td>
									</tr>
									<tr>
										<td><li>Address:  <%=h.getAddress()%></li></td>
									</tr>
									<tr>
										<td><li>Amenities and Services:  <%=h.getServices()%></li></td>
									</tr>
									
									<tr>
										<td><li>Online Booking Cancellation Policy : </li></td>
									</tr>
									<tr>
										<td><%
										List<CancellationRules> collects=h.getCancellationRulesList(); 
																				if(!collects.isEmpty()){
																					for(CancellationRules c : collects){
										%>
										
											 - If one cancels booking before <%=c.getHours()%> hours of chackin date than he will get <%=c.getRefundpercentsge()%>% of refund.
											 <br>
											 <%
											 }
											 										}else{
											 %>
											 - No Policy
											 <%
											 }
											 %></td>
									</tr>
									
								</ul>	
								</strong>								
								</table>
								<br><br>
								<h3>Images : </h3>
								 <div class="row">
								 	<%
								 	File imageDir = new File(getServletContext().getRealPath("/") + Constant.HOTEL_FOLDER_PARENTPATH + h.getId());
								 	int j=0;
								 	for(File imageFile : imageDir.listFiles()){
								 	String imageFileName = imageFile.getName();
								 	%>
										 <%-- <img alt="image" src="<%=ImagePathConstant.HOTEL_FOLDER_PARENTPATH%><%=h.getId() %>\\<%=imageFileName%>"> --%>
										 <div class="column col-6">
										      <img class="demo cursor" src="<%=Constant.HOTEL_FOLDER_PARENTPATH%><%=h.getId() %>\\<%=imageFileName%>" style="width:300px;height:300px" onclick="currentSlide(<%= ++j %>)" alt="image">
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
