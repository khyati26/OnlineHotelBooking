<%@page import="com.narola.hotelbooking.Utility.AdminURLConstant"%>
<%@page import="com.narola.hotelbooking.Utility.Constant"%>
<%@page import="com.narola.hotelbooking.Hotel.Hotel"%>
<%@page import="com.narola.hotelbooking.Hotel.HotelDAO"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.hotelbooking.Room.model.Room"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="../layouts/stylesheets.jsp">
  	<jsp:param value="Add Room" name="title"/>
</jsp:include>

<style>

.image-area {
  position: relative;
  width: 200px;
  background: #333;
}
.image-area img{
  max-width: 100%;
  height: auto;
}
.remove-image {
display: none;
position: absolute;
top: -10px;
right: -10px;
border-radius: 10em;
padding: 2px 6px 3px;
text-decoration: none;
font: 700 21px/20px sans-serif;
background: #555;
border: 3px solid #fff;
color: #FFF;
box-shadow: 0 2px 6px rgba(0,0,0,0.5), inset 0 2px 4px rgba(0,0,0,0.3);
  text-shadow: 0 1px 2px rgba(0,0,0,0.5);
  -webkit-transition: background 0.5s;
  transition: background 0.5s;
}
.remove-image:hover {
 background: #E54E4E;
  padding: 3px 7px 5px;
  top: -11px;
right: -11px;
}
.remove-image:active {
 background: #E54E4E;
  top: -10px;
right: -11px;
}


</style>

</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">	
  		<jsp:include page="../layouts/topnav.jsp"></jsp:include>
	
		<jsp:include page="../layouts/sidemenu.jsp"></jsp:include>

					<%
					HttpSession session1=request.getSession();
											Hotel h2 = (Hotel) session1.getAttribute("hotel");
					%>
					<p  align="left">
						<strong>Hotel : <%=h2.getName()%></strong>
					</p>
				

				<%
								Room h = (Room) request.getAttribute("room");
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
	         
	        </div><!-- /.row -->
	        <div align="center"> <h1 class="m-0">Edit Room</h1></div>
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
							
							<form class="form-card" method="post" action="<%=request.getContextPath() + AdminURLConstant.EDIT_ROOMPAGE_URL%>" enctype="multipart/form-data">
								<input type="hidden" name="id" value="<%=h.getId()%>">
								<div class="row justify-content-between text-left">
									<div class="form-group col-sm-6 flex-column d-flex">
										<label class="form-control-label px-3">Room Type<span class="text-danger"> *</span></label>
										<input type="text" id="name" value="<%=h.getName()%>" name="name" onblur="validate(1)"	required>
									</div>
									<input type="hidden" name="hotelid" value="<%=h2.getId()%>">
									<div class="form-group col-sm-6 flex-column d-flex">
										<label class="form-control-label px-3">Room Total Quantity <span class="text-danger"> *</span></label>
										<input type="number" name="qty" min="1" value="<%=h.getQty()%>" />
									</div>							
								</div>
								<div class="row justify-content-between text-left">
									<div class="form-group col-sm-6 flex-column d-flex">
										<label class="form-control-label px-3">Price<span
											class="text-danger"> *</span></label> 
										<input type="number" id="price" step="any" name="price" 
										value="<%=h.getPrice()%>" onblur="validate(3)" required>
									</div>
									<div class="form-group col-sm-6 flex-column d-flex">
										<label class="form-control-label px-3">Max persons
											accommodate in room<span class="text-danger"> *</span>
										</label> <input type="number" id="maxcapacity" min="1" max="10"
											name="maxcapacity" value="<%=h.getMaxcapacity()%>"
											onblur="validate(4)" required>
									</div>
								</div>
								<div class="row justify-content-between text-left">
									<div class="form-group col-12 flex-column d-flex">
										<label class="form-control-label px-3">Inclusions with
											room<span class="text-danger"> *</span>
										</label>
										<textarea id="inclusions" name="inclusions" rows="5" cols="10"
											onblur="validate(7)" required><%=h.getInclusions()%></textarea>
		
									</div>
								</div>
								<div class="row justify-content-between text-left">
									<div class="form-group col-12 flex-column d-flex">
										<label class="form-control-label px-3">Description of
											room<span class="text-danger"> *</span>
										</label>
										<textarea id="description" name="description" rows="5" cols="10"
											onblur="validate(7)" required><%=h.getDescription()%></textarea>
									</div>
								</div>
								
								<div class="row justify-content-between text-left">
									<div class="form-group col-sm-6 flex-column d-flex">
										<label class="form-control-label px-3">Room Main Image,which is going to display<span class="text-danger">*</span></label>
										<input type="file" id="photo" name="roommainphoto">
										  <input type="hidden" name="hiddenmainphoto" value="<%=h.getImage()%>">
									</div>
									<div class="form-group col-sm-6 flex-column d-flex">
									<img alt="no image found" height="100px" width="100px"
											src="<%=Constant.ROOM_FOLDER_PARENTPATH%><%=h.getId()%>\<%=h.getImage()%>">
										
									</div>
								</div>
								<div class="row justify-content-between text-left">
									<div class="form-group col-12 flex-column d-flex">
										<label class="form-control-label px-3">Room's
											additional Images(can select multiple images)<span
											class="text-danger"> *</span>
										</label> <input type="file" id="photo" name="roomphotos" multiple="multiple">
										<input type="hidden" id="deletedfiles" name="deletedfiles" >
									</div>
								</div>
								
								<div class="row justify-content-between text-left">
									<div class="form-group col-12 flex-column d-flex" id="allimages">
										 <%
										 System.out.print(h.getImages());
										 								 for(String imageFileName : h.getImages()){
										 %>
										 
										<div class="image-area" id="<%=imageFileName%>">
										  <img src="<%=Constant.ROOM_FOLDER_PARENTPATH%><%=h.getId() %>\<%=imageFileName%>" alt="Preview" >
										   <a class="remove-image" onclick="myFunction('<%=imageFileName%>')"  style="display: inline;">&#215;</a>
										</div> <br>
										<%																
										}
										%> 					
									</div>
								</div>
								
								<div class="row justify-content-center">
									<div class="form-group col-sm-6">
										<button type="submit" class="btn-block btn-primary"
											name="update">Update</button>
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
	
<script>
function myFunction(h) {
  alert("hi"+h);
  var files=document.getElementById("deletedfiles").value;
  document.getElementById("deletedfiles").value=files+","+h;
  document.getElementById(h).style.display = "none";
  alert(files);
}
</script>

</body>
</html>