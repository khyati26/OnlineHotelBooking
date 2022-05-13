<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.hotelbooking.Utility.Constant"%>
<%@page import="com.narola.hotelbooking.Utility.AdminURLConstant"%>
<%@page import="com.narola.hotelbooking.Hotel.Hotel"%>
<%@ page import="java.util.List"%>



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
  
	  <!-- Content Wrapper. Contains page content -->
	  <div class="content-wrapper">
	    <!-- Content Header (Page header) -->
	    <div class="content-header">
	      <div class="container-fluid">
	        <div class="row mb-2">
	          <div class="col-sm-6">
	            <h1 class="m-0">List of Hotels</h1>
	          </div><!-- /.col -->
	          <div class="col-sm-6">
	            <ol class="breadcrumb float-sm-right">
	              <a href="<%=request.getContextPath() + AdminURLConstant.ADD_HOTELPAGE_URL%>" class="btn btn-primary">Add Hotel</a> </ol>
	          </div><!-- /.col -->
	        </div><!-- /.row -->
	      </div><!-- /.container-fluid -->
	    </div>
	    <!-- /.content-header -->
	
	    <!-- Main content -->
	    <section class="content">
	    <div class="container-fluid px-1 py-5 mx-auto">
			<div class="rowd-flex justify-content-center">
				<div class=" col-12 text-center">	
					<table id="example" class="table table-striped table-bordered" style="width: 100%">
								<thead>
									<tr>
										<th>ID</th>
										<th>Hotel Name</th>
										<th>Image</th>
										<th>State</th>
										<th>City</th>
										<th>Hotel Star Ratings</th>
										<th>Created On</th>
										<th>Updated On</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<%
									List<Hotel> l = (List) request.getAttribute("hotels");
																int i=0;
																for (Hotel h : l) {
									%>
									<tr>
										<td><%=++i%></td>
										<td><%=h.getName()%></td>
										<td><img alt="no image found" height="100px" width="100px"
										 src="<%=Constant.HOTEL_FOLDER_PARENTPATH%><%=h.getId()%>\<%=h.getImage()%>"> </td>
										<td><%=h.getState()%></td>
										<td><%=h.getCity()%></td>
										<td><%=h.getRating()%></td>
										<td><%=h.getCreatedon()%></td>
										<td><%=h.getUpdatedon()%></td>
										<td><a href="<%=request.getContextPath() + AdminURLConstant.DISPLAYALL_ROOMS_URL%>?hotelid=<%=h.getId()%>" class="btn btn-info">View Room Types</a>
										<br><a href="<%=request.getContextPath() + AdminURLConstant.VIEW_HOTELPAGE_URL%>?id=<%=h.getId()%>">View</a>
										<a href="<%=request.getContextPath() + AdminURLConstant.EDIT_HOTELPAGE_URL%>?id=<%=h.getId()%>">Update</a>
										<a href="<%=request.getContextPath() + AdminURLConstant.DELETE_HOTEL_URL %>?id=<%=h.getId()%>" onclick="return confirm('Are you sure you want to delete this Hotel?');" >Delete</a></td>
									</tr>
									<%
									}
									%>
								</tbody>
								<tfoot>
									<tr>
										<th>ID</th>
										<th>Hotel Name</th>
										<th>Image</th>
										<th>City</th>
										<th>State</th>
										<th>Hotel Star Ratings</th>
										<th>Created On</th>
										<th>Updated On</th>
										<th></th>
									</tr>
								</tfoot>
							</table>
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
	<script>
	$(document).ready(function() {
		$('#example').DataTable();
	});
</script>
</body>
</html>


