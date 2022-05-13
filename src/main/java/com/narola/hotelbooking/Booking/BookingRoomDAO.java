package com.narola.hotelbooking.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Room.Room;
import com.narola.hotelbooking.Room.RoomDAO;
import com.narola.hotelbooking.Utility.ConnectDB;

public class BookingRoomDAO {
	

	public static int inserData(int bookingid,int roomid,int roomqty) throws DatabaseException {
		return inserData(bookingid, roomid, roomqty, null);
	}
	public static int inserData(int bookingid,int roomid,int roomqty,Connection connection) throws DatabaseException {
		if(connection == null) {
			connection = ConnectDB.getConnection();
		}
		PreparedStatement ps =null;
		try {
			String query = "INSERT INTO "
					+ "bookingroom (bookingid,roomid,roomqty) "
					+ "VALUES (?,?,?)";
			 ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, bookingid);
			ps.setInt(2, roomid);
			ps.setInt(3, roomqty);
			ps.executeUpdate();
			ResultSet generatedid=ps.getGeneratedKeys();
			if(generatedid.next()) {
				return generatedid.getInt(1);
			}
		} catch (DatabaseException e) {			
			throw e;
		} catch (SQLException e) {						
			throw new DatabaseException("Exception while inserting bookingroom details : "+ e.getMessage(), e);
		}finally {
			ConnectDB.closeResource(ps);
		}
		return 0;

	}
	
	public static List<Room> getBookedRooms(int bookingid){
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Room> roomList = null;
		try {
			ps = ConnectDB.getConnection().prepareStatement("select roomid,roomqty from bookingroom where bookingid = ?" );
			ps.setInt(1, bookingid);
			 rs = ps.executeQuery();
			roomList = new ArrayList();
			while (rs.next()) {
				Room room =new Room();
				room = RoomDAO.viewRoom(rs.getInt(1));
				room.setQty(rs.getInt(2));
				roomList.add(room);
			}
			return roomList;
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Exception while getting single booking : "+e.getMessage(),e);
		}finally {
			ConnectDB.closeResource(ps,rs);
		}
	}

}
