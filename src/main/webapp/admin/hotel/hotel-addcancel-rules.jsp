<div class="list_wrapper">
	<div class="row justify-content-between text-left">
		<div class="form-group col-xs-5 col-sm-5 col-md-5 flex-column d-flex">
			<label class="form-control-label px-3">Hours (cancellation rules can apply before this hours of checkin date)<span class="text-danger"> *</span>
			</label> <input type="number" id="hours" name="hours" value="48" min="0" onblur="validate(1)" required>
		</div>
		<div class="form-group col-xs-5 col-sm-5 col-md-5 flex-column d-flex">
			<label class="form-control-label px-3">Refundable money in percentage(%)<span class="text-danger"> *</span>
			</label> <input type="number" id="refund" name="refund" value="10" min="0" onblur="validate(3)" required>
		</div>
		<div class="form-group col-xs-1 col-sm-1 col-md-1">
			<button class="btn btn-primary list_add_button" type="button">+</button>
		</div>
	</div>
</div>
