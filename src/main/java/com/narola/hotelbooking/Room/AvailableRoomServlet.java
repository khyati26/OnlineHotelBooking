package com.narola.hotelbooking.Room;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Hotel.Hotel;
import com.narola.hotelbooking.Hotel.HotelDAO;
import com.narola.hotelbooking.Hotel.SearchHotel;
import com.narola.hotelbooking.Hotel.SearchHotelDAO;
import com.narola.hotelbooking.Utility.Constant;

public class AvailableRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		List<Room> roomList = null;
		Set<Room> roomSet = null;
		try {
//			roomList = SearchHotelDAO.getAvailableRooms((SearchHotel) session.getAttribute("searchdetails"),
//					Integer.parseInt(request.getParameter("hotelid")));
			roomSet = SearchHotelDAO.getAvailableRooms((SearchHotel) session.getAttribute("searchdetails"),
					Integer.parseInt(request.getParameter("hotelid")));
			Hotel hotel = HotelDAO.viewHotel(Integer.parseInt(request.getParameter("hotelid")));

			File imageDir = new File(
					getServletContext().getRealPath("/") + Constant.HOTEL_FOLDER_PARENTPATH + hotel.getId());
			for (File imageFile : imageDir.listFiles()) {
				String imageFileName = imageFile.getName();
				if (imageFileName.contains("main")) {
					
				}
			}

			request.setAttribute("roomlist", roomSet);
			request.setAttribute("hotel", hotel);
			RequestDispatcher rd = request.getRequestDispatcher("customerside/Availablerooms.jsp");
			rd.forward(request, response);
		} catch (NumberFormatException | DatabaseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}



