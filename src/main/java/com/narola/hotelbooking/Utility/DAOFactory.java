package com.narola.hotelbooking.Utility;

import com.narola.hotelbooking.Room.dao.IRoomDAO;
import com.narola.hotelbooking.Room.dao.RoomDAOMySQL;
import com.narola.hotelbooking.Room.dao.RoomDAOPostgres;

public class DAOFactory {

	private static DAOFactory DAO_HELPER = null;
	public static String MYSQL = "MYSQL";
	public static String POSTGRES = "POSTGRES";

	public IRoomDAO roomDAO = null;

	public static DAOFactory getInstance() {
		if (DAO_HELPER == null) {
			DAO_HELPER = new DAOFactory();
		}
		return DAO_HELPER;
	}
	
	public IRoomDAO getRoomDAO() {
		return roomDAO;
	}

	public void init(String daoType) throws Exception {
		if (daoType.equals(MYSQL)) {
			roomDAO = new RoomDAOMySQL();
		} else if (daoType.equals(POSTGRES)) {
			roomDAO = new RoomDAOPostgres();
		} else {
			throw new Exception("Type is not supported yet");
		}
	}

}
