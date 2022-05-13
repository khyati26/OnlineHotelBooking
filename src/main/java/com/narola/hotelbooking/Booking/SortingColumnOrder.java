package com.narola.hotelbooking.Booking;

import java.util.Collections;
import java.util.HashMap;

import com.narola.hotelbooking.Utility.StatusConstants;

public class SortingColumnOrder {
	
	public static final HashMap<Integer, String> columnOrder ;
	static{
		columnOrder = new HashMap<>();
		columnOrder.put(1, "ID");
		columnOrder.put(2, "Customer Name");
		columnOrder.put(3, StatusConstants.BOOKING_CONFIRM);
		columnOrder.put(4, StatusConstants.BOOKING_NOT_CONFIRM);
		columnOrder.put(5, StatusConstants.BOOKING_CANCLE);
		columnOrder.put(6, StatusConstants.PAYMENT_SUCCESS);
		columnOrder.put(7, StatusConstants.PAYMENT_PENDING);
		columnOrder.put(8, StatusConstants.PAYMENT_IN_PROGRESS);
		columnOrder.put(9, StatusConstants.PAYMENT_FAIL);		
	};
	
	
}
