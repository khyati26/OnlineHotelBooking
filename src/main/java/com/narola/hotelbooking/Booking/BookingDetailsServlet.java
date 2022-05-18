package com.narola.hotelbooking.Booking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.narola.hotelbooking.Hotel.HotelDAO;
import com.narola.hotelbooking.Hotel.IHotelDAO;
import com.narola.hotelbooking.Hotel.SearchHotelCriteria;
import com.narola.hotelbooking.Room.dao.IRoomDAO;
import com.narola.hotelbooking.Room.dao.RoomDAOMySQL;
import com.narola.hotelbooking.Room.dao.RoomDAOPostgres;
import com.narola.hotelbooking.Room.model.Room;
import com.narola.hotelbooking.Utility.DAOFactory;

/**
 * Servlet implementation class BookingServlet
 */
public class BookingDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IRoomDAO roomDAO = DAOFactory.getInstance().getRoomDAO();
		IHotelDAO HotelDAO = new HotelDAO();

		HttpSession session = request.getSession();
		Gson g = new Gson();
		HashMap<Double, String> s = g.fromJson((String) request.getParameter("roomid"), HashMap.class);
		List<Room> roomList = new ArrayList<>();

		for (Entry<Double, String> pair : s.entrySet()) {
			Room room = roomDAO.viewRoom(pair.getKey().intValue());
			room.setAvailableroom(Integer.parseInt(pair.getValue()));
			roomList.add(room);
		}

		request.setAttribute("hotel", HotelDAO.viewHotel(Integer.parseInt(request.getParameter("hotelid"))));
		request.setAttribute("totalprice", request.getParameter("totprice2"));
		request.setAttribute("roomlist", roomList);

		session.setAttribute("roomlist", roomList);
		RequestDispatcher rd = request.getRequestDispatcher("customerside/Bookingpage.jsp");
		rd.forward(request, response);
	}

}
