package com.narola.hotelbooking.Room;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public interface IRoomService {

	void addRoom(Room room, HttpServletRequest request) throws IOException, ServletException;

	
	
}
