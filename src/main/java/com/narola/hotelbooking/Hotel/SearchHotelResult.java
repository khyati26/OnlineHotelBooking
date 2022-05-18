package com.narola.hotelbooking.Hotel;

import java.util.Set;

import com.narola.hotelbooking.Room.model.Room;

public class SearchHotelResult {

	private Set<Room> roomSet;
	private Hotel hotel;

	public Set<Room> getRoomSet() {
		return roomSet;
	}

	public void setRoomSet(Set<Room> roomSet) {
		this.roomSet = roomSet;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
