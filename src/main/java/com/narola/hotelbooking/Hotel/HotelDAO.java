package com.narola.hotelbooking.Hotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Utility.ConnectDB;

public class HotelDAO {	

	public static int inserData(Hotel hotel) throws DatabaseException {
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO "
					+ "hotels (name,image,stateid,cityid,address,rating,amenitiesAndservices,emailid,cancellationpolicy) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			ps = ConnectDB.getInstance().getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, hotel.getName());
			ps.setString(2, hotel.getImage());
			ps.setInt(3, hotel.getStateId());
			ps.setInt(4, hotel.getCityId());
			ps.setString(5, hotel.getAddress());
			ps.setInt(6, hotel.getRating());
			ps.setString(7, hotel.getServices());
			ps.setString(8, hotel.getEmailId());
			ps.setString(9, hotel.getPolicy());
			ps.executeUpdate();
			ResultSet generatedId=ps.getGeneratedKeys();
			if(generatedId.next()) {
				return generatedId.getInt(1);
			}
		} catch (DatabaseException e) {			
			throw e;
		} catch (SQLException e) {						
			throw new DatabaseException("Exception while inserting hotel details : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps);
		}
		return 0;
	}

	public static void updateData(Hotel hotel) throws DatabaseException {
		PreparedStatement ps = null;
		String query = "update hotels set "
				+ "name = ?,image = ?,stateid = ?,cityid = ?,address = ?,rating = ?,amenitiesAndservices = ?,emailid = ?,cancellationpolicy = ? "
				+ "where id = ?";
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement(query);
			ps.setString(1, hotel.getName());
			ps.setString(2, hotel.getImage());
			ps.setInt(3, hotel.getStateId());
			ps.setInt(4, hotel.getCityId());
			ps.setString(5, hotel.getAddress());
			ps.setInt(6, hotel.getRating());
			ps.setString(7, hotel.getServices());
			ps.setString(8, hotel.getEmailId());
			ps.setString(9, hotel.getPolicy());
			ps.setInt(10, Integer.valueOf(hotel.getId()));
			ps.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Exception while updating hotel details : " + e.getMessage(), e);
		}finally {
			ConnectDB.closeResource(ps, null);
		}
	}

	public static void deleteHotel(int id) throws DatabaseException {
		PreparedStatement ps = null;
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement("update hotels set softdelete = 1 where id = ? " );
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Exception while deleting hotel : " + e.getMessage(),e);
		}finally {
			ConnectDB.closeResource(ps, null);
		}		
	}

	public static Hotel viewHotel(int id) throws DatabaseException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement("SELECT h.*,s.statename,c.cityname "
					+ "FROM mydb.hotels h,mydb.city c , mydb.state s "
					+ "where h.stateid=s.id and h.cityid=c.id and h.id = ? ");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Hotel hotel=null;
			if (rs.next()) {
				hotel = new Hotel();
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
				hotel.setCreatedon(rs.getString(11));
				hotel.setUpdatedon(rs.getString(12));
				hotel.setCity(rs.getString(14));
				hotel.setState(rs.getString(15));
			}
			return hotel;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Exception while fetching single hotel detail : " + e.getMessage(),e);
		} finally {
			ConnectDB.closeResource(ps, rs);			
		}
	}

	public static List<Hotel> showData() throws DatabaseException {
		List<Hotel> hotelList = new ArrayList<>();
		Statement st=null;
		ResultSet rs=null;
		try {			
			st = ConnectDB.getInstance().getConnection().createStatement();
			rs = st.executeQuery("SELECT h.*,s.statename,c.cityname FROM mydb.hotels h,mydb.city c , mydb.state s where h.stateid=s.id and h.cityid=c.id and softdelete = 0;");
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
				hotel.setCreatedon(rs.getString(11));
				hotel.setUpdatedon(rs.getString(12));
				hotel.setState(rs.getString(14));
				hotel.setCity(rs.getString(15));
				hotelList.add(hotel);
			}

			return hotelList;
		} catch (SQLException e) {
			e.printStackTrace();
		throw new DatabaseException("Exception while fetching single hotel detail : " + e.getMessage(),e);
		} finally {
			ConnectDB.closeResource(st, rs);			
		}
	}

	public static List<Hotel> getHotelNameandId() throws DatabaseException {
		List<Hotel> hotelList = new ArrayList<>();
		Statement st=null;
		ResultSet rs=null;
		try {
			
			st = ConnectDB.getInstance().getConnection().createStatement();
			rs = st.executeQuery("SELECT id,name FROM hotels where softdelete = 0");

			while (rs.next()) {
				Hotel hotel = new Hotel();
				hotel.setId(rs.getInt(1));
				hotel.setName(rs.getString(2));
				hotelList.add(hotel);
			}

			return hotelList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new DatabaseException("Exception while fetching collection of hotel name and id : " + e.getMessage(),e);
		} finally {
			ConnectDB.closeResource(st, rs);			
		}
	}
	
}
