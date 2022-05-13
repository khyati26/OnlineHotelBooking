<div class="row justify-content-between text-left">
	<div class="form-group col-sm-6 flex-column d-flex">
		<label class="form-control-label px-3">Room Type<span class="text-danger"> *</span></label> 
		<input type="text" id="name" name="roomname" placeholder="Enter Room Type name" value="Luxury Room" onblur="validate(1)" required>
	</div>
	<div class="form-group col-sm-6 flex-column d-flex">
		<label class="form-control-label px-3">Room Total Quantity <span class="text-danger"> *</span></label>
		<input type="number" name="qty" min="1" />
	</div> 
</div>

<div class="row justify-content-between text-left">
	<div class="form-group col-sm-6 flex-column d-flex">
		<label class="form-control-label px-3">Price<span	class="text-danger"> *</span></label> 
		<input type="number" id="price"	step="any" name="price" placeholder="Enter Room Price" value="1500.0" onblur="validate(3)" required>
	</div>
	<div class="form-group col-sm-6 flex-column d-flex">
		<label class="form-control-label px-3">Max persons accommodate in room<span class="text-danger"> *</span></label> 
		<input type="number" id="maxcapacity" min="1" max="10"	name="maxcapacity"	placeholder="Enter Maximum persons can accommodate in a room"
			onblur="validate(4)" required value="2">
	</div>
</div>

<div class="row justify-content-between text-left">
	<div class="form-group col-12 flex-column d-flex">
		<label class="form-control-label px-3">Inclusions with room<span class="text-danger"> *</span></label>
		<!-- <textarea id="inclusions" name="inclusions" rows="5" cols="10"	onblur="validate(7)" required>all things</textarea> -->
		<textarea name="inclusions" id="inclusions" onblur="validate(7)" required>&lt;p&gt;Free Lunch,room service,TV,AC,Dressing table &lt;/p&gt;</textarea>
		
	</div>
</div>
<div class="row justify-content-between text-left">
	<div class="form-group col-12 flex-column d-flex">
		<label class="form-control-label px-3">Description of room<span	class="text-danger"> *</span></label>
		<!-- <textarea id="description" name="description" rows="5" cols="10" onblur="validate(7)" required>room size in sq.fit, bed size (double bed or single bed)</textarea> -->
		<textarea name="description" id="description" onblur="validate(7)" required>&lt;p&gt;room size in sq.fit, bed size (double bed or single bed) &lt;/p&gt;</textarea>
		
	</div>
</div>

<div class="row justify-content-between text-left">
	<div class="form-group col-12 flex-column d-flex">
		<label class="form-control-label px-3">Room Main Image, which is going to display<span class="text-danger"> *</span></label> 
		<input type="file" id="photo" name="roommainphoto" >
	</div>
</div>
<div class="row justify-content-between text-left">
	<div class="form-group col-12 flex-column d-flex">
		<label class="form-control-label px-3">Room's additional Images(can select multiple images)<span class="text-danger"> *</span></label> 
		<input type="file" id="photo" name="roomphotos" multiple="multiple">
	</div>
</div>
