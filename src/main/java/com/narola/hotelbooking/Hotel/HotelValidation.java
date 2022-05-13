package com.narola.hotelbooking.Hotel;

import com.narola.hotelbooking.Exception.ValidationErrorMsg;

public class HotelValidation {
	public static boolean namevalidation(String name) {
		if (name.trim().matches("^[a-zA-z\\s]+$"))
			return true;
		else
			return false;
	}

	public static boolean emailvalidation(String email) {
		if (email.trim().matches("^(.+)@(.+)$")) {
			System.out.println("email true");
			return true;
		} else {
			System.out.println("email false");
			return false;
		}
	}
	
	public static void hotelfieldvalidation(Hotel h) throws ValidationErrorMsg {
		if(!h.getName().matches("^[a-zA-z\\s]+$"))
			throw new ValidationErrorMsg("Name should be alphabets only");
		if(!h.getEmailId().matches("^(.+)@(.+)$"))
			throw new ValidationErrorMsg("Email should be in proper formate");
		
	}
}
