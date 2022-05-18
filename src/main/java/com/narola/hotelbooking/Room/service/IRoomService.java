package com.narola.hotelbooking.Room.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Hotel.SearchHotelCriteria;
import com.narola.hotelbooking.Hotel.SearchHotelResult;
import com.narola.hotelbooking.Room.model.Room;

public interface IRoomService {

	void addRoom(Room room, HttpServletRequest request) throws ApplicationException;

	SearchHotelResult searchRooms(SearchHotelCriteria searchHotelCriteria) throws ApplicationException;

	void deleteRoom(int roomId) throws ApplicationException;

	List<Room> getAllRoom(int hotelId) throws ApplicationException;

	Room getRoom(int roomId) throws ApplicationException;

	Room getRoom(int roomId, HttpServletRequest request) throws ApplicationException;

	void updateRoom(Room room, HttpServletRequest request) throws ApplicationException;
}
