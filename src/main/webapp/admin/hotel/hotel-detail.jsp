<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 
<div class="row justify-content-between text-left">
	<div class="form-group col-sm-6 flex-column d-flex">
		<label class="form-control-label px-3">Hotel Name<span class="text-danger"> *</span></label> 
		<input type="text" id="name" name="name" placeholder="Enter Hotel name" value="ABC Hotel" onblur="validate(1)" required>
	</div>
	<div class="form-group col-sm-6 flex-column d-flex">
		<label class="form-control-label px-3">Email ID<span class="text-danger"> *</span></label> 
		<input type="text" id="email" name="email" placeholder="Enter Email ID" value="abc@gmail.com"
			onblur="validate(2)" required>
	</div>
</div>
<div class="row text-left">
	<div class="form-group col-sm-6 ">
        <label>State</label>
        <select class="form-control select2" style="width: 100%;" id="stateid" name="stateid" onchange="getcities(this.value)" required>
        	<option  disabled="disabled" selected="selected" value="">-- Select State --</option>
        	<c:forEach items="${states}" var="state">
        	    <option value="${state.id }">${state.stateName}</option>
			</c:forEach>

        </select>
    </div>
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
	<div class="form-group col-sm-6 flex-column d-flex">
		<label class="form-control-label px-3">City<span class="text-danger"> *</span></label> 
		<select class="form-control select2" style="width: 100%;" name="cityid" id="cityid" required>
        </select>
	</div>
</div>

<div class="row justify-content-between text-left">
	<div class="form-group col-12 flex-column d-flex">
		<label class="form-control-label px-3">Hotel's Full Address<span class="text-danger"> *</span></label>
		<textarea name="address" id="address" onblur="validate(5)" required>&lt;p&gt;Surat , Gujarat.&lt;/p&gt;</textarea>
		
	</div>
</div>

<div class="row justify-content-between text-left">
	<div class="form-group col-sm-6 flex-column d-flex">
		<label class="form-control-label px-3">Hotel Star Ratings(e.g.4 star,5 star)<span class="text-danger"> *</span></label> 
		<input type="number" id="rating" name="rating" max="5" min="1" placeholder="" value="3" onblur="validate(6)" required>
	</div>	
</div>

<div class="row justify-content-between text-left">
	<div class="form-group col-12 flex-column d-flex">
		<label class="form-control-label px-3">Amenities and Services of Hotel<span class="text-danger"> *</span></label>
		<textarea name="service" id="service" onblur="validate(7)" required>&lt;p&gt;room service , doctor on call ,swimming pool&lt;/p&gt;</textarea>
		
	</div>
</div>

<div class="row justify-content-between text-left">
	<div class="form-group col-12 flex-column d-flex">
		<label class="form-control-label px-3">Hotel Booking Cancellation Policy<span class="text-danger"> *</span></label> 
		<textarea name="policy" id="policy" onblur="validate(8)" required>&lt;p&gt;50% refund&lt;/p&gt;</textarea>
	</div>
</div>
