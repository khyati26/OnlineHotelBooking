package com.narola.hotelbooking.Room.dao;

import java.util.List;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Room.model.Room;

public interface IRoomDAO {

	int inserData(Room room) throws DatabaseException;

	List<Room> showHotelWiseRoom(int hotelid) throws DatabaseException;

	List<Room> showData() throws DatabaseException;

	boolean deleteRoom(int id) throws DatabaseException;

	Room viewRoom(int id) throws DatabaseException;

	void updateData(Room h) throws DatabaseException;

}
