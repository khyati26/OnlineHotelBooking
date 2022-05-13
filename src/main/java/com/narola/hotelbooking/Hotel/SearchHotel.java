package com.narola.hotelbooking.Hotel;

public class SearchHotel {

	private String city;
	private String checkIn;
	private String checkOut;
	private int adult;
	private int children;
	private int room;
	private int days;

	
	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkin) {
		this.checkIn = checkin;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkout) {
		this.checkOut = checkout;
	}

	public int getAdult() {
		return adult;
	}

	public void setAdult(int adult) {
		this.adult = adult;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "SearchHotel [city=" + city + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", adult=" + adult
				+ ", children=" + children + ", room=" + room + "]";
	}

	
	
}
