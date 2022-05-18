<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.hotelbooking.Hotel.Hotel"%>
<%@page import="com.narola.hotelbooking.Hotel.HotelDAO"%>
<%@page import="com.narola.hotelbooking.Room.model.Room"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="../layouts/stylesheets.jsp">
  	<jsp:param value="Add Room" name="title"/>
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
	          <h1>Hotel : <%=h2.getName() %></h1>
	           
	          </div><!-- /.col -->
	         
	        </div><!-- /.row -->
	        <div align="center"> <h1 class="m-0">Add Room</h1></div>
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
								Room r= (Room)request.getAttribute("room");
								System.out.println(r.getName()==null?"true "+r.getName():"false");
								String errMsg = (String) request.getAttribute("errMsg");
									
								if (errMsg != null && !errMsg.trim().isEmpty()) {
							%>
								<span style="color:red"><li><%=errMsg%></li></span>
							<%
								}
							%>	
							<form class="form-card" method="post" action="addroompage" enctype="multipart/form-data">

								<div class="row justify-content-between text-left">
									<div class="form-group col-sm-6 flex-column d-flex">
										<label class="form-control-label px-3">Room Type<span
											class="text-danger"> *</span></label> <input type="text" id="name"
											name="name" placeholder="Enter Room Type name"
											value='<%= r.getName() == null ? "" : r.getName() %>' onblur="validate(1)" required>
									</div>
									<input type="hidden" name="hotelid" value="<%=h2.getId()%>">
									<div class="form-group col-sm-6 flex-column d-flex">
										<label class="form-control-label px-3">Room Total Quantity <span class="text-danger"> *</span></label>
										<input type="number" name="qty" min="1" />
									</div> 
								</div>
								<div class="row justify-content-between text-left">
									<div class="form-group col-sm-6 flex-column d-flex">
										<label class="form-control-label px-3">Price<span
											class="text-danger"> *</span></label> <input type="number" id="price"
											step="any" name="price" placeholder="Enter Room Price"
											value="1500.0" onblur="validate(3)" required>
									</div>
									<div class="form-group col-sm-6 flex-column d-flex">
										<label class="form-control-label px-3">Max persons
											accommodate in room<span class="text-danger"> *</span>
										</label> <input type="number" id="maxcapacity" min="1" max="10"
											name="maxcapacity"
											placeholder="Enter Maximum persons can accommodate in a room"
											onblur="validate(4)" required value="2">
									</div>
								</div>
								<div class="row justify-content-between text-left">
									<div class="form-group col-12 flex-column d-flex">
										<label class="form-control-label px-3">Inclusions with
											room<span class="text-danger"> *</span>
										</label>
										<textarea id="inclusions" name="inclusions" rows="5" cols="10"
											onblur="validate(7)" required></textarea>
		
									</div>
								</div>
								<div class="row justify-content-between text-left">
									<div class="form-group col-12 flex-column d-flex">
										<label class="form-control-label px-3">Description of
											room<span class="text-danger"> *</span>
										</label>
										<textarea id="description" name="description" rows="5" cols="10"
											onblur="validate(7)" required>room size in sq.fit, bed size (double bed or single bed)</textarea>
									</div>
								</div>
								<div class="row justify-content-between text-left">
									<div class="form-group col-12 flex-column d-flex">
										<label class="form-control-label px-3">Room Main Image, which is going to display<span
											class="text-danger"> *</span></label> 
										<input type="file" id="photo" name="roommainphoto"  required>
									</div>
								</div>
								<div class="row justify-content-between text-left">
									<div class="form-group col-12 flex-column d-flex">
										<label class="form-control-label px-3">Room's additional Images(can select multiple images)<span class="text-danger"> *</span></label> 
										<input type="file" id="photo" name="roomphotos" multiple="multiple" >
									</div>
								</div>
		
								<div class="row justify-content-center">
									<div class="form-group col-sm-6">
										<button type="submit" class="btn-block btn-primary"
											name="insert">Save</button>
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


