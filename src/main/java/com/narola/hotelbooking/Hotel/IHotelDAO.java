package com.narola.hotelbooking.Hotel;

import java.util.List;

import com.narola.hotelbooking.Exception.DatabaseException;

public interface IHotelDAO {
	int inserData(Hotel hotel) throws DatabaseException;

	void updateData(Hotel hotel) throws DatabaseException;

	void deleteHotel(int id) throws DatabaseException;

	Hotel viewHotel(int id) throws DatabaseException;

	List<Hotel> showData() throws DatabaseException;

	List<Hotel> getHotelNameandId() throws DatabaseException;

}
