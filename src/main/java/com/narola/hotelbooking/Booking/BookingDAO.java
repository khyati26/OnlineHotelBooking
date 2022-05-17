package com.narola.hotelbooking.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.narola.hotelbooking.Customer.Customer;
import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Hotel.Hotel;
import com.narola.hotelbooking.Hotel.HotelDAO;
import com.narola.hotelbooking.Room.Room;
import com.narola.hotelbooking.Utility.ConnectDB;

public class BookingDAO {
	public static List<Booking> searchBookingData(BookingFilterParam filterParam) {

		String Query = "select b.*, c.firstname , c.lastname from mydb.booking b , mydb.customer c  , mydb.hotels h\r\n"
				+ "where  b.customerid = c.id and b.hotelid = h.id ";
		if(filterParam.getBookingId() != 0) {
			Query += "and b.id = " + filterParam.getBookingId();
		}else {
			if (filterParam.getCheckIn() != null  && filterParam.getCheckOut() != null) {
				Query += "and b.checkin between '" + filterParam.getCheckIn() + "' and '" + filterParam.getCheckOut() + "' ";
			}
			else if (filterParam.getCheckIn() != null && filterParam.getCheckOut() == null) {
				Query += "and b.checkin = '" + filterParam.getCheckIn() + "' ";
			}
			else if (filterParam.getCheckIn() == null && filterParam.getCheckOut() != null) {
				Query += "and b.checkout = '" + filterParam.getCheckOut() + "' ";
			}
			
			if (filterParam.getBookingStatus() != null) {
				Query += "and b.booking_status = '" + filterParam.getBookingId() + "' ";
			}
			if (filterParam.getPaymentStatus() != null) {
				Query += "and b.payment_status = '" + filterParam.getPaymentStatus() + "' ";
			}		
			
			Query += " group by b.id order by b.id ;";
		}

		List<Booking> bookingList = new ArrayList<Booking>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement(Query);
//			ps.setString(1, checkin);
//			ps.setString(2, checkout);
			rs = ps.executeQuery();
			while (rs.next()) {
				Booking booking = new Booking();
				booking.setId(rs.getInt(1));
				booking.setCustomerId(rs.getInt(2));
				booking.setHotelId(rs.getInt(3));
				booking.setHotel(HotelDAO.viewHotel(rs.getInt(3)));
				booking.setTotalRoomQty(rs.getInt(4));
				booking.setAdults(rs.getInt(5));
				booking.setChildren(rs.getInt(6));
				booking.setTotalAmount(rs.getDouble(7));
				booking.setCheckIn(rs.getString(8));
				booking.setCheckOut(rs.getString(9));
				booking.setPaymentMode(rs.getString(10));
				booking.setPaymentStatus(rs.getString(11));
				booking.setBookingStatus(rs.getString(12));
				booking.setRazorPaymentId(rs.getString(13));
				booking.setRazorOrderId(rs.getString(14));
				booking.setFailedReason(rs.getString(15));
				booking.setErrorJson(rs.getString(16));
				// 17,18
				booking.setRoomList(BookingRoomDAO.getBookedRooms(rs.getInt(1)));
				Customer customer = new Customer();
				customer.setFirstName(rs.getString(19));
				customer.setLastName(rs.getString(20));
				booking.setCustomer(customer);

				bookingList.add(booking);
			}

			return bookingList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new DatabaseException("Exception while filtering booking data : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps, rs);
		}

	}

	public static int createBooking(Booking book, List<Room> roomList) {
		Connection connection = ConnectDB.getInstance().getConnection();
		try {
			connection.setAutoCommit(false);
			int bookingid = inserData(book, connection);
			for (Room r : roomList) {
				BookingRoomDAO.inserData(bookingid, r.getId(), r.getAvailableroom(), connection);
			}
			return bookingid;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static int inserData(Booking book) throws DatabaseException {
		return inserData(book, null);
	}

	public static Booking viewBooking(int id) throws DatabaseException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement("select * from booking where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Booking booking = null;
			if (rs.next()) {
				booking = new Booking();
				booking.setId(rs.getInt(1));
				booking.setCustomerId(rs.getInt(2));
				booking.setHotelId(rs.getInt(3));
				booking.setTotalRoomQty(rs.getInt(4));
				booking.setAdults(rs.getInt(5));
				booking.setChildren(rs.getInt(6));
				booking.setTotalAmount(rs.getInt(7));
				booking.setCheckIn(rs.getString(8));
				booking.setCheckOut(rs.getString(9));
				booking.setPaymentMode(rs.getString(10));
				booking.setPaymentStatus(rs.getString(11));
				booking.setPaymentMode(rs.getString(12));
				booking.setBookingStatus(rs.getString(13));
				booking.setRazorPaymentId(rs.getString(14));
				booking.setRazorOrderId(rs.getString(15));
				booking.setFailedReason(rs.getString(16));
				booking.setErrorJson(rs.getString(17));
			}
			return booking;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Exception while getting single booking : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps, rs);
		}

	}

	public static int inserData(Booking book, Connection connection) throws DatabaseException {
		if (connection == null) {
			connection = ConnectDB.getInstance().getConnection();
		}
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO " + "booking (customerid,hotelid,roomqty,adults,children,totalamount,"
					+ "checkin,checkout,payment_mode,payment_status)" + "VALUES (?,?,?,?,?,?,?,?,?,?)";
			ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, book.getCustomerId());
			ps.setInt(2, book.getHotelId());
			ps.setInt(3, book.getTotalRoomQty());
			ps.setInt(4, book.getAdults());
			ps.setInt(5, book.getChildren());
			ps.setDouble(6, book.getTotalAmount());
			ps.setString(7, book.getCheckIn());
			ps.setString(8, book.getCheckOut());
			ps.setString(9, book.getPaymentMode());
			ps.setString(10, book.getPaymentStatus());
			ps.executeUpdate();
			ResultSet generatedid = ps.getGeneratedKeys();
			if (generatedid.next()) {
				return generatedid.getInt(1);
			}
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException("Exception while inserting booking details : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps);
		}
		return 0;
	}

	public static void updateData(Booking booking) throws DatabaseException {

		String query = "update booking set " + "customerid = ?,\r\n" + "hotelid = ?,\r\n" + "roomqty = ?,\r\n"
				+ "adults = ?,\r\n" + "children = ?,\r\n" + "totalamount = ?,\r\n" + "checkin = ?,\r\n"
				+ "checkout = ?,\r\n" + "payment_mode = ?,\r\n" + "payment_status = ?,\r\n" + "booking_status = ?,\r\n"
				+ "razorpaymentid = ? ," + "razororderid = ? ," + "failedreason = ? ," + "errorjson = ? "
				+ "where id = ?";
		PreparedStatement ps = null;
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement(query);
			ps.setInt(1, booking.getCustomerId());
			ps.setInt(2, booking.getHotelId());
			ps.setInt(3, booking.getTotalRoomQty());
			ps.setInt(4, booking.getAdults());
			ps.setInt(5, booking.getChildren());
			ps.setDouble(6, booking.getTotalAmount());
			ps.setString(7, booking.getCheckIn());
			ps.setString(8, booking.getCheckOut());
			ps.setString(9, booking.getPaymentMode());
			ps.setString(10, booking.getPaymentStatus());
			ps.setString(11, booking.getBookingStatus());
			ps.setString(12, booking.getRazorPaymentId());
			ps.setString(13, booking.getRazorOrderId());
			ps.setString(14, booking.getFailedReason());
			ps.setString(15, booking.getErrorJson());
			ps.setInt(16, booking.getId());
			ps.executeUpdate();
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException("Exception while updatingting booking details: " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps);
		}
	}

	public static List<Booking> showallbooking() throws DatabaseException {
		List<Booking> bookingList = new ArrayList();
		Statement s = null;
		ResultSet rs = null;
		try {

			s = ConnectDB.getInstance().getConnection().createStatement();
			rs = s.executeQuery("select b.*, c.firstname , c.lastname  \r\n"
					+ "from mydb.booking b , mydb.customer c  , mydb.hotels h\r\n"
					+ "where  b.customerid = c.id and b.hotelid = h.id\r\n" + "group by b.id order by b.id;");

			while (rs.next()) {
				Booking booking = new Booking();

				booking.setId(rs.getInt(1));
				booking.setCustomerId(rs.getInt(2));
				booking.setHotelId(rs.getInt(3));
				booking.setHotel(HotelDAO.viewHotel(rs.getInt(3)));
				booking.setTotalRoomQty(rs.getInt(4));
				booking.setAdults(rs.getInt(5));
				booking.setChildren(rs.getInt(6));
				booking.setTotalAmount(rs.getDouble(7));
				booking.setCheckIn(rs.getString(8));
				booking.setCheckOut(rs.getString(9));
				booking.setPaymentMode(rs.getString(10));
				booking.setPaymentStatus(rs.getString(11));
				booking.setBookingStatus(rs.getString(12));
				booking.setRazorPaymentId(rs.getString(13));
				booking.setRazorOrderId(rs.getString(14));
				booking.setFailedReason(rs.getString(15));
				booking.setErrorJson(rs.getString(16));
//				17,18
				booking.setRoomList(BookingRoomDAO.getBookedRooms(rs.getInt(1)));
				Customer customer = new Customer();
				customer.setFirstName(rs.getString(19));
				customer.setLastName(rs.getString(20));
				booking.setCustomer(customer);

				bookingList.add(booking);
			}

			return bookingList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new DatabaseException("Exception while getting all bookings : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(s, rs);
		}

	}

}
