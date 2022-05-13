<%@page import="com.narola.hotelbooking.Utility.UserURLConstant"%>
<%@page import="com.narola.hotelbooking.Utility.AdminURLConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="tm-top-bar" id="tm-top-bar">
	<!-- Top Navbar -->
	<div class="container">
		<div class="row">
			 <nav class="navbar navbar-expand-lg narbar-light">
				<a class="navbar-brand mr-auto" href="<%=request.getContextPath()%>/home"> <img src="customerside/layouts/img/logo.png" alt="Site logo"> Level </a>
				<!-- <button type="button" id="nav-toggle" class="navbar-toggler collapsed" data-toggle="collapse"
					data-target="#mainNav" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button> -->
				<div id="mainNav" class="collapse navbar-collapse tm-bg-white">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item">
							<a class="nav-link" href="<%=request.getContextPath()%>/home">Home</a>
						</li>
						
						<li class="nav-item">
							<a class="nav-link" href="#tm-section-6">Contact Us</a>
						</li>
						<c:choose>
							<c:when test="${sessionScope.user != null }">
								<li class="nav-item">
									<a class="nav-link" href="#">${sessionScope.user.firstName }</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="nav-item">
									<a class="nav-link" data-toggle="modal" data-target="#exampleModal" >	Login </a>
								</li>
								<li class="nav-item">
									<a class="nav-link"  data-toggle="modal" data-target="#exampleModal2">Sign up</a>
								</li>
							</c:otherwise>
						</c:choose>
						
					
					</ul>
				</div>
			</nav>
		 	
		</div>
		
		
	</div>
 	<div style=" width: 100%; height: 20px;background: #EE5057">  </div>
 	
</div>

<!-- Modal 1 -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">  
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
	  			<form method="post" action="userlogin"  >
	   
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
				 <button type="submit" class="btn btn-primary" id="loginbtn">Login me</button>
	      </form>
	       
	      </div>
	      <div class="modal-footer">
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

  