package com.narola.hotelbooking.Hotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Hotel.Hotel;
import com.narola.hotelbooking.Room.Room;
import com.narola.hotelbooking.Utility.ConnectDB;

public class SearchHotelDAO {

	public static Set<Room> getAvailableRooms(SearchHotel searchHotel, int hotelid)
			throws DatabaseException, SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<Room> roomSet = new LinkedHashSet<>();
		List<Room> roomList = new ArrayList<>();

//		String query = "select  r.* from mydb.roomtype r where not exists (\r\n"
//				+ " select b.* from mydb.booking b where r.id=b.roomid ) and  r.hotelid = ? and r.maxcapacity <= ? ";
		String query = "select  r.* from mydb.roomtype r where not exists (\r\n"
				+ "select br.roomid from mydb.booking b inner join mydb.bookingroom br on  b.id=br.bookingid where r.id=br.roomid) \r\n"
				+ "and  r.hotelid = ? and r.maxcapacity = ? ;\r\n";
		LocalDate dateStart = LocalDate.parse(searchHotel.getCheckIn());
		LocalDate dateEnd = LocalDate.parse(searchHotel.getCheckOut());

		try {
			ConnectDB.getInstance().getConnection().setAutoCommit(false);
			ps = ConnectDB.getInstance().getConnection().prepareStatement(query );
			ps.setInt(1, hotelid);
			int totalparsons = searchHotel.getAdult() + searchHotel.getChildren();
			if ((int) totalparsons % searchHotel.getRoom() == 0) {
				System.out.println(totalparsons / searchHotel.getRoom() == 2 || totalparsons / searchHotel.getRoom() == 1 ? 2 : totalparsons / searchHotel.getRoom());
				ps.setInt(2,totalparsons / searchHotel.getRoom() == 2 || totalparsons / searchHotel.getRoom() == 1 ? 2 : totalparsons / searchHotel.getRoom() );
			} else {

				ps.setInt(2, (totalparsons / searchHotel.getRoom()) + 1 == 2 ? 3 : (totalparsons / searchHotel.getRoom()) + 1);
			}

			rs = ps.executeQuery();

			while (rs.next()) {
				Room room = new Room();
				room.setId(rs.getInt(1));
				room.setName(rs.getString(2));
				room.setHotelID(rs.getInt(3));
				room.setPrice(rs.getDouble(4));
				room.setQty(rs.getInt(5));
				room.setInclusions(rs.getString(6));
				room.setImage(rs.getString(7));
				room.setMaxcapacity(rs.getInt(8));
				room.setDescription(rs.getString(9));
				room.setCreatedon(rs.getString(10));
				room.setUpdatedon(rs.getString(11));
				room.setAvailableroom(rs.getInt(5));
				roomList.add(room);
				roomSet.add(room);
			}
			
			// + answer from this query
//			String query2="SELECT r.* ,sum(b.roomqty) as totalbookedroom  from mydb.booking b inner join mydb.roomtype r on b.roomid=r.id and b.hotelid = ? and\r\n"
//					+ " b.checkin <= ? and b.checkout >= ? group by b.roomid having sum(b.roomqty) < r.qty ;";
			String query2 = "SELECT r.* ,sum(br.roomqty) as totalbookedroom ,r.qty as roomQTY from \r\n"
					+ "mydb.booking b inner join mydb.bookingroom br on b.id=br.bookingid  \r\n"
					+ "inner join mydb.roomtype r on br.roomid = r.id\r\n"
					+ "and b.hotelid = ? and b.checkin <= ? and b.checkout >= ? group by br.roomid having sum(br.roomqty) < r.qty;\r\n";
		    ps = ConnectDB.getInstance().getConnection().prepareStatement(query2);
			ps.setInt(1, hotelid);
			 
			
			if(ChronoUnit.DAYS.between(dateStart, dateEnd)>1) {
				
				ps.setString(2, dateEnd.minusDays(1).toString());
				ps.setString(3, dateStart.plusDays(1).toString());
				rs=ps.executeQuery();
				while (rs.next()) {
					Room room = new Room();
					room.setId(rs.getInt(1));
					room.setName(rs.getString(2));
					room.setHotelID(rs.getInt(3));
					room.setPrice(rs.getDouble(4));
					room.setQty(rs.getInt(5));
					room.setInclusions(rs.getString(6));
					room.setImage(rs.getString(7));
					room.setMaxcapacity(rs.getInt(8));
					room.setDescription(rs.getString(9));
					room.setCreatedon(rs.getString(10));
					room.setUpdatedon(rs.getString(11));
					room.setAvailableroom(rs.getInt(5)-rs.getInt(12));
					roomList.add(room);
					roomSet.add(room);
				}
				
				// more than 1 night room availability check
//				String query1 =" SELECT r.* from mydb.booking b , mydb.roomtype r where b.roomid=r.id and b.hotelid = ? and\r\n"
//						+ " b.checkin not between ? and ? and b.checkout not between ? and ? group by b.roomid ; ";
				String query1 = "SELECT r.* from\r\n"
						+ " mydb.booking b , mydb.roomtype r , mydb.bookingroom br where b.id=br.bookingid and r.id=br.roomid \r\n"
						+ " and b.hotelid = ? and b.checkin not between ? and ? and b.checkout not between ? and ? group by br.roomid ; \r\n";
				ps = ConnectDB.getInstance().getConnection().prepareStatement(query1);
				ps.setInt(1, hotelid);
				ps.setString(2, searchHotel.getCheckIn());
				ps.setString(3, dateEnd.minusDays(1).toString());
				ps.setString(4, dateStart.plusDays(1).toString());
				ps.setString(5, searchHotel.getCheckOut());
				rs=ps.executeQuery();
				while (rs.next()) {
					Room room = new Room();
					room.setId(rs.getInt(1));
					room.setName(rs.getString(2));
					room.setHotelID(rs.getInt(3));
					room.setPrice(rs.getDouble(4));
					room.setQty(rs.getInt(5));
					room.setInclusions(rs.getString(6));
					room.setImage(rs.getString(7));
					room.setMaxcapacity(rs.getInt(8));
					room.setDescription(rs.getString(9));
					room.setCreatedon(rs.getString(10));
					room.setUpdatedon(rs.getString(11));
					room.setAvailableroom(rs.getInt(5));
					roomList.add(room);
					roomSet.add(room);
				}
			}else {
				
				ps.setString(2, searchHotel.getCheckIn());
				ps.setString(3, searchHotel.getCheckOut());
				rs=ps.executeQuery();
				while (rs.next()) {
					Room room = new Room();
					room.setId(rs.getInt(1));
					room.setName(rs.getString(2));
					room.setHotelID(rs.getInt(3));
					room.setPrice(rs.getDouble(4));
					room.setQty(rs.getInt(5));
					room.setInclusions(rs.getString(6));
					room.setImage(rs.getString(7));
					room.setMaxcapacity(rs.getInt(8));
					room.setDescription(rs.getString(9));
					room.setCreatedon(rs.getString(10));
					room.setUpdatedon(rs.getString(11));
					room.setAvailableroom(rs.getInt(5)-rs.getInt(12));
					roomList.add(room);
					roomSet.add(room);
				}
				
				// 1 night room availability check				
//				String query1=" SELECT r.* from mydb.booking b , mydb.roomtype r where b.roomid=r.id and b.hotelid = ? and\r\n"
//						+ " b.checkin != ? and b.checkout != ? group by b.roomid ;\r\n";
				String query1 = "SELECT r.* from \r\n"
						+ "mydb.booking b , mydb.roomtype r , mydb.bookingroom br where b.id=br.bookingid and r.id=br.roomid  \r\n"
						+ "and b.hotelid = ? and b.checkin != ? and b.checkout != ? group by br.roomid ;\r\n";
				ps = ConnectDB.getInstance().getConnection().prepareStatement(query1);
				ps.setInt(1, hotelid);
				ps.setString(2, searchHotel.getCheckIn());
				ps.setString(3, searchHotel.getCheckOut());
				rs=ps.executeQuery();
				while (rs.next()) {
					Room room = new Room();
					room.setId(rs.getInt(1));
					room.setName(rs.getString(2));
					room.setHotelID(rs.getInt(3));
					room.setPrice(rs.getDouble(4));
					room.setQty(rs.getInt(5));                               
					room.setInclusions(rs.getString(6));
					room.setImage(rs.getString(7));
					room.setMaxcapacity(rs.getInt(8));
					room.setDescription(rs.getString(9));
					room.setCreatedon(rs.getString(10));
					room.setUpdatedon(rs.getString(11));
					room.setAvailableroom(rs.getInt(5));
					roomList.add(room);
					roomSet.add(room);
				}				
			}

			ConnectDB.getInstance().getConnection().commit();
//			return roomList;
			return roomSet;
		} catch (SQLException e) {
			ConnectDB.getInstance().getConnection().rollback();
			throw new DatabaseException("Exception while getting available rooms in hotel: " + e.getMessage(), e);

		} finally {
			ConnectDB.closeResource(ps, rs);
			ConnectDB.getInstance().getConnection().setAutoCommit(true);
			
		}
	}

	public static List<Hotel> getAllHotelsInCity(int cityid) throws DatabaseException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Hotel> hotelList = new ArrayList<>();
		String query1 = "SELECT * FROM mydb.hotels  r where cityId = ? and softdelete = 0 ";
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement(query1);
			ps.setInt(1,cityid);
			rs = ps.executeQuery();
			while (rs.next()) {
				Hotel hotel = new Hotel();
				hotel.setId(rs.getInt(1));
				hotel.setName(rs.getString(2));
				hotel.setImage(rs.getString(3));
				hotel.setStateId(rs.getInt(4));
				hotel.setCityId(rs.getInt(5));
				hotel.setAddress(rs.getString(6));
				hotel.setRating(rs.getInt(7));
				hotel.setServices(rs.getString(8));
				hotel.setEmailId(rs.getString(9));
				hotel.setPolicy(rs.getString(10));
				hotelList.add(hotel);
			}
			return hotelList;
		} catch (SQLException e) {
			throw new DatabaseException("Exception while getting city wise hotels 2: " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps, rs);
		}
	}
	
	public static List<Hotel> getCityWiseHotels(SearchHotel searchhotel) throws DatabaseException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Hotel> hotelList = new ArrayList<>();
		String query1 = "SELECT h.* FROM mydb.hotels h , mydb.city c , mydb.roomtype r where h.cityid = c.id and h.id=r.hotelid and c.cityname = ? and softdelete = 0 and r.maxcapacity <= ? \r\n"
				+ " group by h.id order by r.maxcapacity desc";
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement(query1);
			ps.setString(1, searchhotel.getCity());
			int totalparsons = searchhotel.getAdult() + searchhotel.getChildren();
//			System.out.println((int) (searchhotel.getAdult() + searchhotel.getChildren()) % searchhotel.getRoom());
			if ((int) totalparsons % searchhotel.getRoom() == 0) {

				ps.setInt(2,totalparsons / searchhotel.getRoom() == 2 || totalparsons / searchhotel.getRoom() == 1 ? 3 : totalparsons / searchhotel.getRoom());
			} else {

				ps.setInt(2, (totalparsons / searchhotel.getRoom()) + 1);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Hotel hotel = new Hotel();
				hotel.setId(rs.getInt(1));
				hotel.setName(rs.getString(2));
				hotel.setImage(rs.getString(3));
				hotel.setStateId(rs.getInt(4));
				hotel.setCityId(rs.getInt(5));
				hotel.setAddress(rs.getString(6));
				hotel.setRating(rs.getInt(7));
				hotel.setServices(rs.getString(8));
				hotel.setEmailId(rs.getString(9));
				hotel.setPolicy(rs.getString(10));
				hotelList.add(hotel);
			}
			return hotelList;
		} catch (SQLException e) {
			throw new DatabaseException("Exception while getting city wise hotels: " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps, rs);
		}
	}

}
