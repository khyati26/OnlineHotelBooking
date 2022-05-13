<%@page import="com.narola.hotelbooking.Utility.Constant"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.narola.hotelbooking.StateCity.CityDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page import="java.util.List" %>
<%@ page import="com.narola.hotelbooking.StateCity.City" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="layouts/stylesheets.jsp">
	<jsp:param value="Home Page" name="title" />
</jsp:include>
</head>
<body>
	<div class="tm-main-content" id="top">
		<div class="tm-top-bar-bg"></div>

		<jsp:include page="layouts/topnav.jsp"></jsp:include>

		<div class="tm-section tm-bg-img" id="tm-section-1">
			<div class="tm-bg-white ie-container-width-fix-2">
				<div class="container ie-h-align-center-fix">
					<div class="row">
						<div class="col-xs-12 ml-auto mr-auto ie-container-width-fix">
							<form action="searchhotel" method="post" class="tm-search-form tm-section-pad-2">
								<br>
								<div class="form-row tm-search-form-row">
									<div class="form-group tm-form-element tm-form-element-100">
										<i class="fa fa-map-marker fa-2x tm-form-element-icon"></i> 
										<input name="city" type="text" class="form-control" id="inputCity" placeholder="Type your destination..." required="required">
									</div>
									<div class="form-group tm-form-element tm-form-element-50">
										<i class="fa fa-calendar fa-2x tm-form-element-icon"></i> 
										<input name="check-in" type="date" id="checkin" onchange="handler(event);" class="form-control" placeholder="Check In" required="required" />
									</div>
									<div class="form-group tm-form-element tm-form-element-50">
										<i class="fa fa-calendar fa-2x tm-form-element-icon"></i> 
										<input name="check-out" type="date" id="checkout" class="form-control" placeholder="Check Out" required="required" />
									</div>
								</div>
								<br>
								<div class="form-row tm-search-form-row">
									<div class="form-group tm-form-element tm-form-element-2">
										<select name="adult" class="form-control tm-select" id="adult"	required="required">
											<option value="">Adult</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
											<option value="7">7</option>
											<option value="8">8</option>
											<option value="9">9</option>
											<option value="10">10</option>
										</select> <i class="fa fa-2x fa-user tm-form-element-icon"></i>
									</div>
									<div class="form-group tm-form-element tm-form-element-2">
										<select name="children" class="form-control tm-select" id="children" required="required">
											<option value="">Children</option>
											<option value="0">0</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
											<option value="7">7</option>
											<option value="8">8</option>
											<option value="9">9</option>
											<option value="10">10</option>
										</select> 
										<i class="fa fa-user tm-form-element-icon tm-form-element-icon-small"></i>
									</div>
									<div class="form-group tm-form-element tm-form-element-2">
										<select name="room" class="form-control tm-select" id="room" required="required">
											<option value="">Room</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
											<option value="7">7</option>
											<option value="8">8</option>
											<option value="9">9</option>
											<option value="10">10</option>
										</select> <i class="fa fa-2x fa-bed tm-form-element-icon"></i>
									</div>
									<div class="form-group tm-form-element tm-form-element-2">
										<button type="submit" class="btn btn-primary tm-btn-search">
											<big><b>Search</b></big>
											<!-- Check Availability -->
										</button>
									</div>
								</div>
								<br>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		 <div class="tm-section tm-section-pad tm-bg-gray" id="tm-section-4">
            <div class="container">
                <!-- <div class="tm-bg-white"> -->
                         <div class="tm-bg-primary tm-sidebar-pad">
                               <h3 class="tm-color-white tm-sidebar-title">Recommended Places</h3>
                               <p class="tm-color-white tm-margin-b-0 tm-font-light">Enamel pin cliche tilde, kitsch and VHS thundercats</p>
                          </div>
                    <div class="row">                               
                        <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">
                            <div class="tm-article-carousel">
                                <c:forEach items="${cities }" var="city">                                                      
	                               	 <article class="tm-bg-white mr-2 tm-carousel-item ">
	                               	    <a href="#"><br>
	                                	<center>
		                                	<c:if test="${city.image.isEmpty()!= true }">
		                                		<img src="<%=Constant.CITY_FOLDER_PARENTPATH %>${city.image}" alt="Image" width="300" height="200"> 
		                                    </c:if>
		                                	<c:if test="${city.image.isEmpty() == true }">
		                                		<img src="customerside\layouts\img\img-01.jpg" alt="Image" class="img-fluid"> 
		                                    </c:if>
	                                	</center>
	                                    <div class="tm-article-pad ">
			                               <div class="media-body tm-media-body tm-bg-gray justify-content-center">
			                                    <h4 class="text-uppercase tm-font-semibold tm-sidebar-item-title" style="color: black;">${city.cityName}</h4>
			                               </div>                    
	                                    </div> 
	                                    </a>                               
	                                </article>
                                </c:forEach>                   
                            </div>    
                        </div>
                        
                   </div>
                <!-- </div> -->
            </div>    
         </div>      
	          
	<jsp:include page="layouts/footer.jsp"></jsp:include>
	</div>
	<jsp:include page="layouts/scripts.jsp"></jsp:include>
	
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->
	<script type="text/javascript">
		function handler(e) {
			$('#checkout').attr('min', e.target.value);
		}
		$(document).ready(function() {

			var dtToday = new Date();

			var month = dtToday.getMonth() + 1;
			var day = dtToday.getDate();
			var year = dtToday.getFullYear();
			if (month < 10)
				month = '0' + month.toString();
			if (day < 10)
				day = '0' + day.toString();

			var minDate = year + '-' + month + '-' + day;

			$('#checkin').attr('min', minDate);
			$('#checkout').attr('min', minDate);
		});
	</script>

</body>
</html>