<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="com.narola.hotelbooking.Utility.AdminURLConstant"%>
<%@page import="com.narola.hotelbooking.Utility.Constant"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="../layouts/stylesheets.jsp">
  	<jsp:param value="List of Cities" name="title"/>
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
	          <h1>List of Cities </h1>
	           
	          </div><!-- /.col -->
	          <div class="col-sm-6">
	            <ol class="breadcrumb float-sm-right">
	              <a href="<%=request.getContextPath() + AdminURLConstant.ADD_CITYPAGE_URL%>" class="btn btn-primary">Add City</a> </ol>
	          </div><!-- /.col -->
	          </div>
	        <!-- /.row -->
	      </div><!-- /.container-fluid -->
	    </div>
	    <!-- /.content-header -->
	
	    <!-- Main content -->
	    <section class="content">
	    <div class="container-fluid px-1 py-5 mx-auto">
			<!-- <div class="rowd-flex ">
				<div class=" col-12 "> -->	
					
					
				 <form id="frm-example"  method="POST"> 
				  	<p align="right"><button id="save" class="btn btn-primary" type="submit">Save</button></p>
					<table id="example" class="table table-striped table-bordered" >
						<thead>
							<tr>
								<th>ID</th>
								<th>State</th>
								<th>City</th>
								<th>Image</th>
								<th>Popular</th>
								<th>Action</th>							
							</tr>
						</thead>
						<tbody>
						<c:set var="i" value="0" />
						<c:forEach items="${cities}" var="city">
						<tr>
							<td><c:out value="${i=i+1}"/></td>
							<td>${city.stateName}</td>
							<td>${city.cityName }</td>
							<td><img alt="no image found" height="100px" width="100px" src="<%=Constant.CITY_FOLDER_PARENTPATH%>${city.image}"> </td>							
							<td>
							<c:if test="${city.popular == 0 }">
								<input type="checkbox" name="checklist" onclick="checkvalue(this)" value="${city.id }">
							</c:if>
							<c:if test="${city.popular == 1 }">
								<input type="checkbox" name="checklist" onclick="checkvalue(this)" value="${city.id }" checked>
							</c:if>
							</td>
							<td>
								<a href="<%=request.getContextPath() + AdminURLConstant.EDIT_CITYPAGE_URL%>?id=${city.id}">Update</a>
								<a href="<%=request.getContextPath() + AdminURLConstant.DELETE_CITY_URL%>?id=${city.id}" onclick="return confirm('Are you sure you want to delete this Room?');">Delete</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<th>ID</th>
							<th>State</th>
							<th>City</th>
							<th>Image</th>
							<th>Popular</th>
							<th>Action</th>
						</tr>
					</tfoot>
				</table>
				 </form>
				<!-- </div>
			</div> -->			
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

var uncheckedid=[];
var checkedid=[];

	$(document).ready(function() {
		$('#example').DataTable();
		$('#frm-example').on('submit', function(e){
			// Prevent actual form submission
		    e.preventDefault();
		    
		 // register event for all checkboxes
		   console.log("unchecked "+uncheckedid);
		    console.log("checked "+checkedid);
		    if(checkedid.length != 0 || uncheckedid.length !=0){
		    	$.ajax({
		              type: "get",
		              url: "popularcities",
		              data: "checkedids="+checkedid+"&uncheckedids="+uncheckedid,
		              success: function(msg){
		            	  console.log(msg);
		            	 
		             }
				});
		    }
		    else{
		    	/* alert("both null"); */
		    }
		    
		    
		});
		
	});
	function checkvalue(element) {
		
		if(element.checked){
			checkedid.push(element.value)
		}
		else{
			
			uncheckedid.push(element.value);
		}
		
	}
</script>
</body>
</html>