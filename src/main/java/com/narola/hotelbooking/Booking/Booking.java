package com.narola.hotelbooking.Booking;

import java.util.Comparator;
import java.util.List;

import com.narola.hotelbooking.Customer.Customer;
import com.narola.hotelbooking.Hotel.Hotel;
import com.narola.hotelbooking.Room.Room;

public class Booking implements Comparator {

	private int id;
	private int customerId;
	private int hotelId;
	private int totalRoomQty;
	private int adults;
	private int children;
	private double totalAmount;
	private String checkIn;
	private String checkOut;
	private String paymentMode;
	private String paymentStatus;
	private String bookingStatus;
	private String razorPaymentId;
	private String razorOrderId;
	private String failedReason;
	private String errorJson;
	private String createdOn;
	private String updatedOn;
	private List<Room> roomList;
	private Customer customer;
	private Hotel hotel;
	
	

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRazorPaymentId() {
		return razorPaymentId;
	}

	public void setRazorPaymentId(String razorPaymentId) {
		this.razorPaymentId = razorPaymentId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public int getTotalRoomQty() {
		return totalRoomQty;
	}

	public void setTotalRoomQty(int totalRoomQty) {
		this.totalRoomQty = totalRoomQty;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalamount) {
		this.totalAmount = totalamount;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getRazorOrderId() {
		return razorOrderId;
	}

	public void setRazorOrderId(String razorOrderId) {
		this.razorOrderId = razorOrderId;
	}

	public String getFailedReason() {
		return failedReason;
	}

	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}

	public String getErrorJson() {
		return errorJson;
	}

	public void setErrorJson(String errorJson) {
		this.errorJson = errorJson;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public List<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}


	

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
