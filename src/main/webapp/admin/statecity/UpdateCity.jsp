<%@page import="com.narola.hotelbooking.Utility.Constant"%>
<%@page import="com.narola.hotelbooking.Utility.AdminURLConstant"%>
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
	        
	        <div align="center"> <h1 class="m-0">Edit City</h1></div>
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
							<form class="form-card" method="post" action="<%=request.getContextPath()+ AdminURLConstant.EDIT_CITYPAGE_URL %>" enctype="multipart/form-data">

								<div class="row justify-content-between text-left">
									<div class="form-group col-sm-6 flex-column d-flex">
										<label class="form-control-label px-3">State<span class="text-danger"> *</span></label> 
										<select name="stateid" required>
											<c:forEach items="${states}" var="state">												
												<c:choose>
													<c:when test="${state.id == city.stateId }">
														<option value="${state.id }" selected>${state.stateName }</option>
													</c:when>
													<c:otherwise>
														<option value="${state.id }" >${state.stateName }</option>												
													</c:otherwise>
												</c:choose>												
											</c:forEach>
											
										</select>
									</div>
								</div>
								<div class="row justify-content-between text-left">
									<div class="form-group col-sm-6 flex-column d-flex">
										<label class="form-control-label px-3">City <span class="text-danger"> *</span></label> 
										<input type="text" id="cityname" value="${city.cityName }" name="cityname" placeholder="Enter City Name" onblur="validate(3)" required>
									</div>
									<input type="hidden" name="cityid" value="${city.id }">
								</div>
								<div class="row justify-content-between text-left">
									<table>
										<tr>
											<td>
												<label class="form-control-label px-3">Upload City Image</label>
												<input type="file" id="cityimage" name="cityimage" onblur="validate(7)" />		
											</td>
											<td>
												<img alt="no image" width="100px" height="100px" src="<%=Constant.CITY_FOLDER_PARENTPATH%>${city.image}">
											</td>
										</tr>
									</table>										
								</div>
								<div class="row justify-content-between text-left">
									<table>
										<tr>
											<td>											
											<input type="hidden" name="popular" value="0" />
											<c:choose>
												<c:when test="${city.popular == 1 }">
													<input type="checkbox" value="1" id="popular1" name="popular" onblur="validate(7)" checked >
												</c:when>
												<c:otherwise>
													<input type="checkbox" value="1"  id="popular1" name="popular" onblur="validate(7)" >
												</c:otherwise>
											</c:choose>	
											</td>
											<td>
											<label class="form-control-label px-3">Is this City Most Popular for travelers in India?</label>
											</td>
										</tr>
									</table>
									
								</div>
										
								<div class="row justify-content-center">
									<div class="form-group col-sm-6">
										<input type="submit" class="btn-block btn-primary"
											name="update" value="Update" />
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