<%@page import="com.narola.hotelbooking.Utility.AdminURLConstant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Home Page</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" />
 
    
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<style>
a, a:hover{
  color:#333
}
</style>
</head>
<body>
<%-- Server Version: <%= application.getServerInfo() %><br>
Servlet Version: <%= application.getMajorVersion() %>.<%= application.getMinorVersion() %>
JSP Version: <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %> <br>
<br>
<a href="<%=request.getContextPath()%>/displayallhotels">Show List of Hotels</a>
<br><br> --%>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
Login
</button>

<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal2">
Sign up
</button>

<!-- Modal 1 -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Login</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      	<span style='color:red' id="errormsg1"></span>
  			<br>
        <form method="post" id="myform1" >
        	<div class="form-group  flex-column d-flex">
				<label class="form-control-label px-3">Email ID<span class="text-danger"> *</span></label> 
				<input type="email" id="email1" name="email1"  required>
			</div>
			<div class="form-group  flex-column d-flex"> 
				<!-- <input type="password" id="password1" name="password1"  required> -->
				<label class="form-control-label px-3">Password <span class="text-danger"> *</span></label>
    				<div class="input-group "> 
				      <span class="input-group-text" ><i class="bi bi-eye-slash" id="togglePassword1" style=" cursor: pointer;"></i></span>
				      <input type="password" class="form-control"  name="password1" id="password1"  aria-describedby="inputGroupPrepend2" required>
				   </div> 
			</div>
        </form>
      </div>
      <div class="modal-footer">
      <!-- <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
        <button type="submit" class="btn btn-primary" id="loginbtn">Login me</button>
      </div>
    </div>
  </div>
</div>
  
<!-- Modal 2 -->
<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Sign up</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
     <div class="modal-body">
  			<span style='color:red' id="errormsg2"></span>
  			<br><br>
   		<form method="post" id="myform2" action="registration">
      
        	<div class="row justify-content-between text-left">
						
	        	<div class="form-group col-sm-6 flex-column d-flex">
					<label class="form-control-label px-3">First Name: <span class="text-danger"> *</span></label> 
					<input type="text" id="fname" name="fname"  required>
				</div>
				<div class="form-group col-sm-6 flex-column d-flex">
					<label class="form-control-label px-3">Last Name: <span class="text-danger"> *</span></label> 
					<input type="text" id="lname" name="lname"  required>
				</div>
	        </div>
	        <div class="row justify-content-between text-left">						
	        	<div class="form-group col-sm-6 flex-column d-flex">
					<label class="form-control-label px-3">Email ID: <span class="text-danger"> *</span></label> 
					<input type="email" id="email2" name="email2"  required>
				</div>
				<div class="form-group col-sm-6 flex-column d-flex">
					<label class="form-control-label px-3">Mobile no.: </label> 
					<input type="text" id="mobile" name="mobile"  >
				</div>
	        </div>
	         <div class="row justify-content-between text-left">	
	         	 <div class="form-group col-12 flex-column d-flex">
    				<label>Password</label>
    				<div class="input-group "> 
				      <span class="input-group-text" ><i class="bi bi-eye-slash" id="togglePassword2" style=" cursor: pointer;"></i></span>
				      <input type="password" class="form-control"  name="password2" id="password2"  aria-describedby="inputGroupPrepend2" required>
				   </div> 
    			
				  </div>					
	        	<!-- <div class="form-group col-12 flex-column d-flex">	        		
					<label class="form-control-label px-3">Password: <span class="text-danger"> *</span></label> 
					<p><i class="bi bi-eye-slash" id="togglePassword" style=" cursor: pointer;"></i>
					  
					<input type="password" style="width: 100%;" name="password2" id="password2" > 
				      
					 </p>
				</div> -->
	        </div>

	         <div class="row justify-content-between text-left">					
				<div class="form-group col-sm-12 flex-column d-flex">
					<label class="form-control-label px-3">Confirmed Password: <span class="text-danger"> *</span></label> 
					<input type="password" id="cpassword2" name="cpassword2"  required>
				</div>
	        </div>
 		</form> 
      
      </div>
      <div class="modal-footer">
      <!-- <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
        <!-- <button type="button" class="btn btn-primary" id="signupbtn">Sign up</button> -->
         <div class="col-12">
			<button class="btn btn-primary" id="signupbtn" type="submit">Submit form</button>
		  </div>
      </div>
     
    </div>
  </div>
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://use.fontawesome.com/b9bdbd120a.js"></script>
    
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script> 
<script type="text/javascript">

$(document).ready(function(){
	
	 
	
	$("#signupbtn").click(function(){
		var form = $("#myform2");
			form.validate({
				errorElement : 'h6',
				errorClass : 'text-danger',  
				 highlight : function(element,errorClass,validClass) {
			        $(element).closest('.form-group').addClass(errorClass);
				},
		       unhighlight : function(element,errorClass,validClass) {
				    $(element).closest('.form-group').removeClass(errorClass);
				}, 
			   rules : {
					fname : "required",
					email2 : {
						required : true,
					    email : true
						},
					lname : "required",
					password2 : "required",
					cpassword2 : "required",
			   },
			  messages : {
				fname : {required : "* First name required",},
			    lname : {required : "* Last name required",},
				email2 : {required : "* Email required",	},
			   }
		    });
		
			 if (form.valid() === true) {
			  $.ajax({
	              type: "get",
	              url: "registration", //this is my filter
	              data:"fname=" +$('#fname').val()+"&lname="+ $('#lname').val() +
	              "&mobile=" +$('#mobile').val()+"&cpass="+ $('#cpassword2').val() +
	            	  "&email=" +$('#email2').val()+"&pass="+$('#password2').val(),
	              success: function(msg){
	            	   console.log(msg);
	            	   if(msg == "True"){
	            		   $(location).attr('href','<%=request.getContextPath()%>/admindashboard');
	            	   }else{
	            		   $('#errormsg2').html("- "+msg);
	            		 /*   alert("false"); */
	            	   }
	              } 
			  });
			}

		});
	$("#loginbtn").click(function(){
		var form = $("#myform1");
		form.validate({
			errorElement : 'h6',
			errorClass : 'text-danger',  
			 highlight : function(element,errorClass,validClass) {
		        $(element).closest('.form-group').addClass(errorClass);
			},
	       unhighlight : function(element,errorClass,validClass) {
			    $(element).closest('.form-group').removeClass(errorClass);
			}, 
		   rules : {			
				email1 : {
					required : true,
				    email : true
					},		
				password1 : "required",			
		   }		 
	    });
		if (form.valid() === true) {
			 $.ajax({
	              type: "get",
	              url: "login", //this is my filter
	              data: "email=" +$('#email1').val()+"&pass="+$('#password1').val(),
	              success: function(msg){
	            	  console.log(msg);
	            	  if(msg == "True"){
	            		   $(location).attr('href','<%=request.getContextPath()%>/admindashboard');
	            	   }else{
	            	  $('#errormsg1').html("- "+msg);
	              
	            	   }
	             }
			});
		}

	});
});

const togglePassword2 = document.querySelector("#togglePassword2");
const password2 = document.querySelector("#password2");

togglePassword2.addEventListener("click", function () {
    // toggle the type attribute
    const type = password2.getAttribute("type") === "password" ? "text" : "password";
    password2.setAttribute("type", type);    
    // toggle the icon
    this.classList.toggle("bi-eye");
});

const togglePassword1 = document.querySelector("#togglePassword1");
const password1 = document.querySelector("#password1");

togglePassword1.addEventListener("click", function () {
    // toggle the type attribute
    const type = password1.getAttribute("type") === "password" ? "text" : "password";
    password1.setAttribute("type", type);    
    // toggle the icon
    this.classList.toggle("bi-eye");
});

</script>
</body>
</html>