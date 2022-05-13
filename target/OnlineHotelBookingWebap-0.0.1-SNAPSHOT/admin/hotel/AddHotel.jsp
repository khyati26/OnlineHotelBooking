<%@page import="com.narola.hotelbooking.Utility.AdminURLConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../layouts/stylesheets.jsp">
	<jsp:param value="Add Hotel" name="title" />
</jsp:include>

 
<style>
#msform fieldset:not(:first-of-type) {
	display: none
}

#msform .action-button {
	width: 100px;
	background: skyblue;
	font-weight: bold;
	color: white;
	border: 0 none;
	border-radius: 0px;
	cursor: pointer;
	padding: 10px 5px;
	margin: 10px 5px
}

#msform .action-button:hover, #msform .action-button:focus {
	box-shadow: 0 0 0 2px white, 0 0 0 3px skyblue
}

#msform .action-button-previous {
	width: 100px;
	background: #616161;
	font-weight: bold;
	color: white;
	border: 0 none;
	border-radius: 0px;
	cursor: pointer;
	padding: 10px 5px;
	margin: 10px 5px
}

#msform .action-button-previous:hover, #msform .action-button-previous:focus
	{
	box-shadow: 0 0 0 2px white, 0 0 0 3px #616161
}

#progressbar {
	margin-bottom: 30px;
	overflow: hidden;
	color: gray;
}

#progressbar .active {
	color: #000000
}

#progressbar li {
	list-style-type: none;
	font-size: 12px;
	width: 25%;
	float: left;
	position: relative
}

#progressbar #hotel:before {
	font-family: FontAwesome;
	content: "\f0f7"
}

#progressbar #room:before {
	font-family: FontAwesome;
	content: "\f0f7"
}

#progressbar #cancle:before {
	font-family: FontAwesome;
	content: "\f00d"
}

#progressbar #image:before {
	font-family: FontAwesome;
	content: "\f083"
}

#progressbar #confirm:before {
	font-family: FontAwesome;
	content: "\f00c"
}

#progressbar li:before {
	width: 50px;
	height: 50px;
	line-height: 45px;
	display: block;
	font-size: 18px;
	color: #ffffff;
	background: lightgray;
	border-radius: 50%;
	margin: 0 auto 10px auto;
	padding: 2px
}

#progressbar li:after {
	content: '';
	width: 100%;
	height: 2px;
	background: lightgray;
	position: absolute;
	left: 0;
	top: 25px;
	z-index: -1
}

#progressbar li.active:before, #progressbar li.active:after {
	background: skyblue
}


</style>

</head>


<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="../layouts/topnav.jsp"></jsp:include>

		<jsp:include page="../layouts/sidemenu.jsp"></jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-12" align="center">
							<h1>Add Hotel</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<%
				String errMsg = (String) request.getAttribute("errMsg");

				if (errMsg != null && !errMsg.trim().isEmpty()) {
				%>
				<span style="color:red"><li><%=errMsg%></li></span>
				<%
				}
				%>

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row ">
						<!-- left column -->
						<div class="col-md-12 text-center">
							<!-- jquery validation -->
							<div class="card ">
								<!-- form start -->
								<!-- <form id="quickForm"> -->
								<form method="post" id="msform" class="form-card"
									action="<%=request.getContextPath() + AdminURLConstant.ADD_HOTELROOM_URL%>"
									enctype="multipart/form-data">
									<ul id="progressbar">
										<li class="active" id="hotel"><strong>Hotel
												Details</strong></li>
										<li id="cancle"><strong>Booking Cancellation
												Policy</strong></li>
										<li id="image"><strong>Upload Hotel Images</strong></li>
										<li id="room"><strong>Hotel's Room Details</strong></li>
									</ul>
									<fieldset>
										<div class="form-card">
											<h2 class="fs-title">Hotel Information</h2>
											<jsp:include page="hotel-detail.jsp"></jsp:include>
										</div>
										<input type="button" name="next" class="next action-button"
											value="Next Step" />
									</fieldset>
									<fieldset>
										<div class="form-card">
											<h2 class="fs-title">Booking Cancellation Rules</h2>
											<jsp:include page="hotel-addcancel-rules.jsp"></jsp:include>
										</div>
										<input type="button" name="previous"
											class="previous action-button-previous" value="Previous" />
										<input type="button" name="next" class="next action-button"
											value="Next Step" />
									</fieldset>
									<fieldset>
										<div class="form-card">
											<h2 class="fs-title">Hotel Images</h2>
											<jsp:include page="hotel-image-upload.jsp"></jsp:include>
										</div>
										<input type="button" name="previous"
											class="previous action-button-previous" value="Previous" />
										<input type="button" name="next" class="next action-button"
											value="Next Step" />
									</fieldset>
									<fieldset>
										<div class="form-card">
											<h2 class="fs-title">Room Information</h2>
											<jsp:include page="room-details.jsp"></jsp:include>
										</div>
										<input type="button" name="previous"
											class="previous action-button-previous" value="Previous" />
										<input type="submit" name="insert" class="next action-button"
											value="Confirm" />
									</fieldset>
								</form>
							</div>
							<!-- /.card -->
						</div>
						<!--/.col (left) -->
						<!-- right column -->
						<div class="col-md-6"></div>
						<!--/.col (right) -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<jsp:include page="../layouts/footer.jsp"></jsp:include>

	</div>
	<!-- ./wrapper -->

	<jsp:include page="../layouts/scripts.jsp"></jsp:include>



<script type="text/javascript" src="js/ckeditorBasic/ckeditor.js"></script>
<!-- Select2 -->
<script type="text/javascript" src="admin/layouts/plugins/select2/js/select2.full.min.js"></script>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
<script type="text/javascript">

CKEDITOR.replace( 'address' );
CKEDITOR.replace( 'service' );
CKEDITOR.replace( 'policy' );
CKEDITOR.replace( 'inclusions' );
CKEDITOR.replace( 'description' );

$(document).ready(function() {
		var current_fs, next_fs, previous_fs; //fieldsets
		var opacity;

		$(".next").click(function() {
			var form = $("#msform");
			form.validate({
				errorElement : 'p',
				errorClass : 'text-danger',
				highlight : function(element,errorClass,validClass) {
			        $(element).closest('.form-group').addClass(errorClass);
				},
		       unhighlight : function(element,errorClass,validClass) {
				    $(element).closest('.form-group').removeClass(errorClass);
				},
			   rules : {
					name : "required",
					email : {
						required : true,
					    email : true
						},
					cityid : "required",
					stateid : "required",
					address : "required",
					rating : "required",
					service : "required",
					policy : "required",
					/* mainphoto : "required",
					photos : "required", */
					hours: "required",
					refund: "required",
			   },
			  messages : {
				name : {required : "Hotel name required",},
			    city : {required : "city required",},
				email : {required : "Email required",	},
			   }
		    });
		if (form.valid() === true) {
			current_fs = $(this).parent();
			next_fs = $(this).parent().next();

			//Add Class Active
			$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
		    //show the next fieldset
			next_fs.show();
			//hide the current fieldset with style
			current_fs.animate({opacity : 0},
					{step : function(now) {
						// for making fielset appear animation
						opacity = 1 - now;
					    current_fs.css({
					    	'display' : 'none',
							'position' : 'relative'
							});
					    next_fs.css({
					    	'opacity' : opacity
					    	});
					    },
					    duration : 600
			 });
	     }
	 });

	$(".previous").click(function() {
		current_fs = $(this).parent();
		previous_fs = $(this).parent().prev();
	
		//Remove class active
		$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");
	
		//show the previous fieldset
		previous_fs.show();
	
		//hide the current fieldset with style
		current_fs.animate({
			opacity : 0
			}, {
				step : function(now) {
					// for making fielset appear animation
					opacity = 1 - now;
					current_fs.css({	
						'display' : 'none',					
						'position' : 'relative'					
					});				
					previous_fs.css({				
						'opacity' : opacity					
					});				
				},			
				duration : 600			
			});		
	});


	$(".submit").click(function() {
		return false;	
	});

	var x = 0; //Initial field counter						
	var list_maxField = 10; //Input fields increment limitation

							
	//Once add button is clicked							
	$('.list_add_button').click(function() {												
		//Check maximum number of input fields
		if (x < list_maxField) {													
			x++; //Increment field counter													
			var list_fieldHTML = '<div class="row justify-content-between text-left">'															
			+ '<div class="form-group col-xs-5 col-sm-5 col-md-5 flex-column d-flex">'															
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
			$(this).closest('div.row').remove(); //Remove field html											
			x--; //Decrement field counter										
		});
						
});
</script>
</body>
</html>


