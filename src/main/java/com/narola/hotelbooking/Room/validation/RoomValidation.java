package com.narola.hotelbooking.Room.validation;

import com.narola.hotelbooking.Exception.ValidationErrorMsg;
import com.narola.hotelbooking.Hotel.Hotel;
import com.narola.hotelbooking.Room.model.Room;

public class RoomValidation {
	
	public static void roomfieldvalidation(Room h) throws ValidationErrorMsg {
		if(!h.getName().trim().matches("^[a-zA-z\\s]+$"))
			throw new ValidationErrorMsg("Name should be alphabets only");
//		if(!h.getEmailid().matches("^(.+)@(.+)$"))
//			throw new ValidationErrorMsg("Email should be in proper formate");
		
	}
}
