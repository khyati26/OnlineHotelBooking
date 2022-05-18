<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.hotelbooking.Utility.Constant"%>
<%@page import="com.narola.hotelbooking.Utility.AdminURLConstant"%>
<%@page import="com.narola.hotelbooking.Hotel.Hotel"%>
<%@ page import="java.util.List"%>

<%@page import="com.narola.hotelbooking.Room.model.Room"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="../layouts/stylesheets.jsp">
  	<jsp:param value="List of Rooms" name="title"/>
</jsp:include>

</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">	
  		<jsp:include page="../layouts/topnav.jsp"></jsp:include>
	
		<jsp:include page="../layouts/sidemenu.jsp"></jsp:include>
  		<%
  		HttpSession session1=request.getSession();
  		  			Hotel h2 = (Hotel) session1.getAttribute("hotel");
  		%>
	  <!-- Content Wrapper. Contains page content -->
	  <div class="content-wrapper">
	    <!-- Content Header (Page header) -->
	    <div class="content-header">
	      <div class="container-fluid">
	        <div class="row mb-2">
	          <div class="col-sm-6">
	          <h1>Hotel : <%=h2.getName()%></h1>
	           
	          </div><!-- /.col -->
	          <div class="col-sm-6">
	            <ol class="breadcrumb float-sm-right">
	              <a href="<%=request.getContextPath() + AdminURLConstant.ADD_ROOMPAGE_URL%>" class="btn btn-primary">Add Room</a> </ol>
	          </div><!-- /.col -->
	        </div>
	        <!-- /.row -->
	        <div class="col-sm-12"> <h1 align="center">List of Rooms</h1></div>
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
								<th>Room Type</th>
								<th>Image</th>
								<th>Price</th>
								<th>Room Qty.</th>
								<th>Max Capacity</th>
								<th>Created On</th>
								<th>Updated On</th>
							
							</tr>
						</thead>
						<tbody>
						<%
						List<Room> l = (List) request.getAttribute("rooms");
														if (!l.isEmpty()) {
															int i = 0;
															for (Room h : l) {
						%>
						<tr>
							<td><%=++i%></td>
							<td><%=h.getName()%></td>
							<td><img alt="no image found" height="100px" width="100px"
							 src="<%=Constant.ROOM_FOLDER_PARENTPATH%><%=h.getId()%>\<%=h.getImage()%>"> </td>
							
							<!-- <td>h.getH().getname()</td> -->
							<td><%=h.getPrice()%></td>
 							<td><%=h.getQty()%></td> 
							<td><%=h.getMaxcapacity()%></td>
<%-- 							<td><%=h.getDescription()%></td> --%>
							<td><%=h.getCreatedon()%></td>
							<td><%=h.getUpdatedon()%></td>
							<td><a
								href="<%=request.getContextPath() + AdminURLConstant.VIEW_ROOMPAGE_URL%>?id=<%=h.getId()%>">View</a>
								<a
								href="<%=request.getContextPath() + AdminURLConstant.EDIT_ROOMPAGE_URL%>?id=<%=h.getId()%>">Update</a>
								<a
								href="<%=request.getContextPath() + AdminURLConstant.DELETE_ROOM_URL%>?id=<%=h.getId()%>" onclick="return confirm('Are you sure you want to delete this Room?');">Delete</a></td>
						</tr>
						<%
						}
						} 
						else
						{
						%>
						<tr><td colspan="11">No Data Found</td></tr>
						<%
						}
						%>
					</tbody><tfoot>
									<tr>
										<th>ID</th>
										<th>Hotel Name</th>
										<th>Image</th>
										<th>City</th>
										<th>State</th>
										<th>Hotel Star Ratings</th>
										<th>Created On</th>
										<th>Updated On</th>
									
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


