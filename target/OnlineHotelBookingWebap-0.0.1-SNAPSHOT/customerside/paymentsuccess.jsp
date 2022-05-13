<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="layouts/stylesheets.jsp">
	<jsp:param value="Available Rooms" name="title" />
</jsp:include>
<style type="text/css">
.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   text-align: center;
}
.card{
padding: 10px;
}

</style>
</head>
<body>
<jsp:include page="layouts/topnav.jsp"></jsp:include><br>
	<div style="width: 100%; height: 120px; background: #EE5057"></div>
	<br>
	
	<div class="container d-flex justify-content-sm-center">
<!--    <h1>Payment Success</h1> -->
		<div class="card bg-success" >
		  <img src="images/successimg.jpg" class="card-img-top" >
		  <div class="card-body text-center">
 			<h1 style="color: white;">Payment successful</h1>
		  </div>
		</div>
		
	</div>
	<br><br>
	<jsp:include page="layouts/footer.jsp"></jsp:include>
	
<jsp:include page="layouts/scripts.jsp"></jsp:include>
</body>
</html>