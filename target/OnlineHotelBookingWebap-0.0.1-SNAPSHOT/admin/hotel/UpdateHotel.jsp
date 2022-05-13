<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.hotelbooking.Hotel.Hotel"%>
<%@page import="com.narola.hotelbooking.Hotel.CancellationRules"%>
<%@page import="com.narola.hotelbooking.Utility.Constant"%>
<%@ page import="java.io.File" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 
<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="../layouts/stylesheets.jsp">
  	<jsp:param value="Edit of Hotel" name="title"/>
</jsp:include>
	<script src="js/ckeditorBasic/ckeditor.js"></script>
	
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
				Hotel h = (Hotel) request.getAttribute("hotel");
				%>
		
   <!-- Content Wrapper. Contains page content -->
	  <div class="content-wrapper">
	    <!-- Content Header (Page header) -->
	    <div class="content-header">
	      <div class="container-fluid">
	        <div class="row mb-2">
	          <div class="col-sm-6">
	            <h1 class="m-0">Edite Hotel: <%=h.getName()%></h1>
	          </div><!-- /.col -->
	          <!-- <div class="col-sm-6">
	            - <ol class="breadcrumb float-sm-right">
	              <li class="breadcrumb-item"><a href="#">Home</a></li>
	              <li class="breadcrumb-item active">Dashboard v1</li>
	            </ol> 
	          </div> -->
	          <!-- /.col -->
	        </div><!-- /.row -->
	      </div><!-- /.container-fluid -->
	    </div>
	    <!-- /.content-header -->
	
	    <!-- Main content -->
	    <section class="content">
			<div class="container-fluid">
				<div class="row justify-content-center">
						<!-- left column -->
					<div class="col-md-12 text-center">
	           		<div class="card">
					<form class="form-card" method="post" action="<%=request.getContextPath()%>/updatepage2" enctype="multipart/form-data">
						<input type="hidden" name="id" value="<%=h.getId()%>">
						<div class="row justify-content-between text-left">
							<div class="form-group col-sm-6 flex-column d-flex">
								<label class="form-control-label px-3">Hotel Name<span
									class="text-danger"> *</span></label> <input type="text" id="name"
									value="<%=h.getName()%>" name="name"
									placeholder="Enter Hotel name" onblur="validate(1)">
							</div>
							<div class="form-group col-sm-6 flex-column d-flex">
								<label class="form-control-label px-3">Email ID<span
									class="text-danger"> *</span></label> <input type="text" id="email"
									name="email" placeholder="Enter Email ID"
									value="<%=h.getEmailId()%>" onblur="validate(3)" required>
							</div>
						</div>
						<div class="row justify-content-between text-left">
							<div class="form-group col-sm-6 flex-column d-flex">
								<label class="form-control-label px-3">State<span class="text-danger"> *</span></label> 
								
								<c:set value="<%=h.getStateId()%>" var="sid" />
								
								<select class="form-control select2" style="width: 100%;" id="stateid" name="stateid" onchange="getcities(this.value)" required>				        	
						        	<c:forEach items="${states}" var="state">
						        		<c:choose>
						        			<c:when test="${state.id == sid}" >
						        				<option value="${state.id }" selected="selected">${state.stateName}</option>
						        			</c:when>
						        			<c:otherwise>
						        				<option value="${state.id }" >${state.stateName}</option>
						        			</c:otherwise>
						        		</c:choose> 					        	    
									</c:forEach>						
						        </select>
						        <script type="text/javascript">
							    	function getcities(value) {
										
										if(value != 0){
											$.ajax({
												type:"get",
												url:"getstatewisecities",
												data:"stateid="+value,
												success: function(data){
									            	console.log(data);
									            	data= jQuery.parseJSON(data);
									            	var optionhtml='';
									            	$.each(data,function(i,item){
									            		optionhtml+="<option value="+item.id+">"+item.cityName+"</option>";
									            	}) ; 
									            	$("#cityid").html(optionhtml);
												}
											});
										}
										else{
											$("#cityid").empty();
										}
									}
							    </script>
							</div>
							<div class="form-group col-sm-6 flex-column d-flex">
								<label class="form-control-label px-3">City<span class="text-danger"> *</span></label> 
								
								<c:set value="<%=h.getCityId()%>" var="cid" />
																
								<select class="form-control select2" style="width: 100%;" name="cityid" id="cityid" required>
									<c:forEach items="${cities}" var="city">
										<c:choose>
						        			<c:when test="${city.id == cid}" >
						        				<option value="${city.id }" selected="selected">${city.cityName}</option>
						        			</c:when>
						        			<c:otherwise>
						        				<option value="${city.id }" >${city.cityName}</option>
						        			</c:otherwise>
						        		</c:choose> 
									</c:forEach>
        						</select>
							</div>
						</div>
						<div class="row justify-content-between text-left">
							<div class="form-group col-12 flex-column d-flex">
								<label class="form-control-label px-3">Hotel's Full	Address<span class="text-danger"> *</span></label>
								<textarea id="address" name="address" rows="5" cols="10" onblur="validate(5)" required><%=h.getAddress()%></textarea>
							</div>
						</div>
						<div class="row justify-content-between text-left">
							<div class="form-group col-sm-6 flex-column d-flex">
								<label class="form-control-label px-3">Hotel Star Ratings(e.g. 4 star,5 star)<span class="text-danger"> *</span></label> 
								<input type="number" id="rating" name="rating" max="5" min="1" placeholder="" onblur="validate(6)" required
									value="<%=h.getRating()%>">
							</div>

						</div>
						<div class="row justify-content-between text-left">
							<div class="form-group col-12 flex-column d-flex">
								<label class="form-control-label px-3">Amenities and Services of Hotel<span class="text-danger"> *</span></label>
								<textarea id="service" name="service" rows="5" cols="10" onblur="validate(7)" required><%=h.getServices()%></textarea>
							</div>
						</div>
						<div class="row justify-content-between text-left">
							<div class="form-group col-12 flex-column d-flex">
								<label class="form-control-label px-3">Hotel Booking Cancellation Policy<span class="text-danger"> *</span></label>
								<textarea id="policy" name="policy" rows="5" cols="10" onblur="validate(8)" required><%=h.getPolicy()%></textarea>
							</div>
						</div>
						<h4>Booking Cancellation Rules</h4><br>
						
						<div class="list_wrapper">
						
						<%
												List<CancellationRules> collects=h.getCancellationRulesList(); 
																 	if(!collects.isEmpty()){
																		for(CancellationRules c : collects){
												%>						
								<div class="row justify-content-between text-left">
									<div class="form-group col-xs-5 col-sm-5 col-md-5 flex-column d-flex">
										<input type="hidden" name="cancelid" value="<%=c.getId()%>" />
										<label class="form-control-label px-3">Hours (cancellation rules can apply before this hours of checkin date)<span class="text-danger"> *</span></label> 
										<input type="number" id="hours" name="hours" value="<%=c.getHours()%>" min="0" onblur="validate(1)" required>
									</div>
									<div class="form-group col-xs-5 col-sm-5 col-md-5 flex-column d-flex">
										<label class="form-control-label px-3">Refundable money in percentage(%)<span class="text-danger"> *</span></label> 
										<input type="number" id="refund" name="refund" value="<%=c.getRefundpercentsge()%>" min="0" onblur="validate(3)" required>
									</div>
									<%
									if(c.getId() == collects.get(0).getId()) {
									%>
									
									<div class="form-group col-xs-1 col-sm-1 col-md-1">
											<button class="btn btn-primary list_add_button" type="button">+</button>
									</div>							
									<%
																} else{
																%>
									
									<div class="form-group col-xs-1 col-sm-1 col-md-1">
											<a href="javascript:void(0);" class="list_remove_button btn btn-danger">-</a>
									</div>
									<%
									}
									%>
								</div>
						<%
						}
											}else{
						%>
								<div class="row justify-content-between text-left">
									<div class="form-group col-xs-5 col-sm-5 col-md-5 flex-column d-flex">
										<input type="hidden" name="cancelid" value="0" />
										<label class="form-control-label px-3">Hours (cancellation rules can apply before this hours of checkin date)<span class="text-danger"> *</span></label> 
										<input type="number" id="hours" name="hours" value="" min="0" onblur="validate(1)" required>
									</div>
									<div class="form-group col-xs-5 col-sm-5 col-md-5 flex-column d-flex">
										<label class="form-control-label px-3">Refundable money in percentage(%)<span class="text-danger"> *</span></label> 
										<input type="number" id="refund" name="refund" value="" min="0" onblur="validate(3)" required>
									</div>									
									<div class="form-group col-xs-1 col-sm-1 col-md-1">
										<button class="btn btn-primary list_add_button" type="button">+</button>
									</div>
								</div>
						<%
						}
						%>
							
						</div>
										
												<h4>Hotel Images</h4><br>
						<div class="row justify-content-between text-left">
							<div class="form-group col-sm-6 flex-column d-flex">
								<label class="form-control-label px-3">Hotel Main Image,which is going to display<span class="text-danger">*</span></label>
								<input type="file" id="photo" name="mainphoto">
								  <input type="hidden" name="hiddenmainphoto" value="<%=h.getImage()%>">
							</div>
							<div class="form-group col-sm-6 flex-column d-flex">
							<img alt="no image found" height="100px" width="100px"
									src="<%=Constant.HOTEL_FOLDER_PARENTPATH%><%=h.getId()%>\<%=h.getImage()%>">
								
							</div>
						</div>
						<div class="row justify-content-between text-left">
							<div class="form-group col-12 flex-column d-flex">
								<label class="form-control-label px-3">Hotel's
									additional Images(can select multiple images)<span
									class="text-danger"> *</span>
								</label> <input type="file" id="photo" name="photos" multiple="multiple">
								<input type="hidden" id="deletedfiles" name="deletedfiles" >
							</div>
						</div>
							
						<div class="row justify-content-between text-left">
							<div class="form-group col-12 flex-column d-flex" id="allimages">
								 <%
								 for(String imageFileName : h.getImages()){
								 %>
								 
								<div class="image-area" id="<%=imageFileName%>">
								  <img src="<%=Constant.HOTEL_FOLDER_PARENTPATH%><%=h.getId() %>\<%=imageFileName%>" alt="Preview" >
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
	          <!-- ./col -->
	        </div>
	        <!-- /.row -->
	      </div><!-- /.container-fluid -->
	    </section>
	    <!-- /.content -->
	  </div>
	  <!-- /.content-wrapper -->

 		
	    <jsp:include page="../layouts/footer.jsp"></jsp:include>
 		
 	</div>
<!-- ./wrapper -->
	
	<jsp:include page="../layouts/scripts.jsp"></jsp:include>
	
<script>

CKEDITOR.replace( 'policy' );
CKEDITOR.replace( 'service');
function myFunction(h) {
  alert("hi"+h);
  var files=document.getElementById("deletedfiles").value;
  document.getElementById("deletedfiles").value=files+","+h;
  document.getElementById(h).style.display = "none";
  alert(files);
}
$(document).ready(function() {
	var x = 0; //Initial field counter						
	var list_maxField = 10; //Input fields increment limitation
							
	//Once add button is clicked							
	$('.list_add_button').click(function() {												
		//Check maximum number of input fields
		if (x < list_maxField) {													
			x++; //Increment field counter													
			var list_fieldHTML = '<div class="row justify-content-between text-left">'															
			+ '<div class="form-group col-xs-5 col-sm-5 col-md-5 flex-column d-flex">'	
			+ '<input type="hidden" name="cancelid" value="0" />'
			+ '<input type="number" id="hours" name="hours" value="48" min="0" onblur="validate(1)" required>'															
			+ '</div>'
			+ '<div class="form-group col-xs-5 col-sm-5 col-md-5 flex-column d-flex">'															
			+ '<input type="number" id="refund" name="refund" value="10" min="0" onblur="validate(3)" required>'
			+ '</div>'															
			+ '<div class="form-group col-xs-1 col-sm-1 col-md-1">'															
			+ '<a href="javascript:void(0);" class="list_remove_button btn btn-danger">-</a>'															
			+ '</div>'
			+ '</div>'; //New input field html 
													
		    $('.list_wrapper').append(list_fieldHTML); //Add field html												
		}											
	});
							
		//Once remove button is clicked						
		$('.list_wrapper').on('click','.list_remove_button', function() {
			var id =$(this).closest("div.row").find("input[name='cancelid']").val();
			alert(id);
			
			$(this).closest('div.row').remove(); //Remove field html											
			x--; //Decrement field counter
			
			if(id != 0){
				var deleteids_fieldHTML='<input type="hidden" name="deletecancelid" value="'+id+'"/>';
				$('.list_wrapper').append(deleteids_fieldHTML); //Add field html
			}
													
		});
});

</script>
	
</body>
</html>