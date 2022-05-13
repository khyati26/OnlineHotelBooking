<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="../layouts/stylesheets.jsp">
  	<jsp:param value="Add City" name="title"/>
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
	        
	        <div align="center"> <h1 class="m-0">Add City</h1></div>
	      </div><!-- /.container-fluid -->
	    </div>
	    <!-- /.content-header -->
	
	    <!-- Main content -->
	    <section class="content">
		    <div class="container-fluid ">
				<div class="rowd-flex ">
					<div class=" col-12 ">
						<div class="card">
						
							<%
								String errMsg = (String) request.getAttribute("errMsg");
									
								if (errMsg != null && !errMsg.trim().isEmpty()) {
							%>
								<span style="color:red"><li><%=errMsg%></li></span>
							<%
								}
							%>	
							<form class="form-card" method="post" action="addcity" enctype="multipart/form-data">

								<div class="row justify-content-between text-left">
									<div class="form-group col-sm-6 flex-column d-flex">
										<label class="form-control-label px-3">State<span class="text-danger"> *</span></label> 
										<select name="stateid" required>
											<option>-- Select State --</option>
											<c:forEach items="${states}" var="state">
												<option value="${state.id }">${state.stateName }</option>
											</c:forEach>
											
										</select>
									</div>
								</div>
								<div class="row justify-content-between text-left">
									<div class="form-group col-sm-6 flex-column d-flex">
										<label class="form-control-label px-3">City <span class="text-danger"> *</span></label> 
										<input type="text" id="cityname" name="cityname" placeholder="Enter City Name" onblur="validate(3)" required>
									</div>
									
								</div>
								<div class="row justify-content-between text-left">
									<div class="form-group col-12 flex-column d-flex">
										<label class="form-control-label px-3">Upload City Image<span class="text-danger"> *</span></label>
										<input type="file" id="cityimage" name="cityimage" onblur="validate(7)" required />		
									</div>
								</div>
								<div class="row justify-content-between text-left">
									<table>
										<tr>
											<td>
											<input type="checkbox" value="1"  id="popular" name="popular" onblur="validate(7)" >
											<input type="hidden" name="popular" value="0" />											</td>
											<td>
											<label class="form-control-label px-3">Is this City Most Popular for travelers in India?</label>
											</td>
										</tr>
									</table>
									
								</div>
										
								<div class="row justify-content-center">
									<div class="form-group col-sm-6">
										<input type="submit" class="btn-block btn-primary"
											name="insert" value="Save" />
									</div>
								</div>
							</form>
						</div>
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
	
	<jsp:include page="../layouts/scripts.jsp"></jsp:include>
		
		
	
</body>
</html>