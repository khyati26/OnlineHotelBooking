<%@page import="com.narola.hotelbooking.Utility.UserURLConstant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.hotelbooking.Utility.Constant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="layouts/stylesheets.jsp">
  	<jsp:param value="List Of Hotels" name="title"/>
</jsp:include>
<style>
.card-horizontal {
  display: flex;
  flex: 1 1 auto;
}
form label{
  font-weight:bold;
  color: white;
}
</style>
</head>
<body>
<div class="tm-main-content" id="top">   
	      
	<jsp:include page="layouts/topnav.jsp"></jsp:include>
	<br> 
	<div style=" width: 100%; height: 120px;background: #EE5057">  </div>
	<br>
	<h3 align="center">${searchdetails.city}</h3>
			<br>
	<div class="tm-section tm-section-pad tm-bg-gray" id="tm-section-4">
			
    	<!-- <div class="container"> -->
        	<div class="row ">
        	    <div class="col jumbotron" style="background-color:#f0ad4e">
			     	<form action="<%=request.getContextPath() + UserURLConstant.SEARCH_HOTEL_URL%>" method="post">
					  <div class="mb-3">
					    <label for="exampleInputEmail1" class="form-label">City</label>
					    <input name="city" type="text"  class="form-control" id="inputCity" placeholder="Type your destination..." required>
                        
					  </div>
					  <div class="mb-3">
					    <label for="exampleInputPassword1" class="form-label">Check In</label>
					    <input name="check-in" type="date" class="form-control" id="inputCheckIn" placeholder="Check In" required="required">
                      </div>
                      <div class="mb-3">
					    <label for="exampleInputPassword1" class="form-label">Check Out</label>
					    <input name="check-out" type="date" class="form-control" id="inputCheckOut" placeholder="Check Out" required="required">
                      </div>
                      <div class="mb-3">
					    <select name="adult" class="form-control tm-select" id="adult" required="required">
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
                         </select>
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
                       </select>
                                                        
                      </div>
					
					  <button type="submit" class="btn btn-primary"><big><b>Search</b></big></button>
					</form>
			    </div> 
			    <div class="col-9 centered" >
			    	<c:forEach items="${hotels}" var="hotel">
			    		<div class="card">
						    <div class="card-horizontal">
						    	<c:set value="${hotel.id}\\" var="hoteldir" />
						       <div class="img-square-wrapper">
						            <img class="" src="<%=Constant.HOTEL_FOLDER_PARENTPATH%>${hoteldir}${hotel.image}" width="300" height="200" alt="Card image cap">
						       </div>
						       <div class="card-body">	
						       	  <div class="row">
						       	  	<div class="col-9">
						       	  		<h4 class="card-title">${hotel.name}</h4>
						       	  	</div>
						       	  	<div class="col-3">
						       	  		<a class="btn" style="color: white;background: #EE5057" href="<%=request.getContextPath() + UserURLConstant.AVAILABLE_ROOMS_URL%>?hotelid=${hotel.id}" >
						       	  			see availability 
						       	  		</a>
						          	</div>
						       	  </div>
						       	  	<tr>
						       	  		<td><p class="card-text">${hotel.services}</p></td>
						       	  	</tr>
						       	  	<tr>
						       	  		<td><h5>${hotel.rating } Stare Hotel</h5></td>
						       	  	</tr>
						       	  </table>			
						          
						       </div>
							</div>
						 
					</div>
					<br>					
			    	</c:forEach>
			    </div>
        	</div>
       <!--  </div> -->
    </div>
            
	

	
	<jsp:include page="layouts/footer.jsp"></jsp:include>
</div>
	<jsp:include page="layouts/scripts.jsp"></jsp:include>	
</body>
</html>