<%@page import="com.narola.hotelbooking.Utility.AdminURLConstant"%>
<%@page import="com.narola.hotelbooking.Utility.Constant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
 <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="layouts/plugins/fontawesome-free/css/all.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="layouts/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="layouts/dist/css/adminlte.min.css">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
/* form {border: 3px solid #f1f1f1;} */

input[type=email], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}
</style>

</head>
<body>
<br>
<center>	
<h2>Admin Login </h2>
<div class="login-box">
 <div class="card">
    <div class="card-body login-card-body">
   
		<form action="<%=request.getContextPath() %><%=AdminURLConstant.ADMIN_LOGIN_URL %>" method="post">
		  <div class="imgcontainer">
		    <img src="layouts/dist/img/img_avatar2.png" alt="Avatar" class="avatar">
		  </div>
			<%
				String errMsg=(String)request.getAttribute("errormsg");
				if (errMsg != null && !errMsg.trim().isEmpty()) {
			%>
				<span style="color:red"><li><%=errMsg%></li></span>
			<%
				}
			%>

		  <div class="container">
		    <label for="uname"><b>User Email ID</b></label>
		    <input type="email" placeholder="Enter Email ID" name="email" required>
		
		    <label for="psw"><b>Password</b></label>
		    <input type="password" placeholder="Enter Password" name="pass" required>
		        
		    <button type="submit">Login</button>
		    <label>
		      <input type="checkbox" checked="checked" name="remember"> Remember me
		    </label>
		  </div>
		
		 <!--  <div class="container" style="background-color:#f1f1f1"> -->
		    <!-- <button type="button" class="cancelbtn">Cancel</button> -->
		    <span class="psw">Forgot <a href="#">password?</a></span>
		  <!-- </div> -->
		</form>
	</div>
  </div>
</div>
</center>

<!-- jQuery -->
<script src="layouts/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="layouts/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="layouts/dist/js/adminlte.min.js"></script>

</body>
</html>