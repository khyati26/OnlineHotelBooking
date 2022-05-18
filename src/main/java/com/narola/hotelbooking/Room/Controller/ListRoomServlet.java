package com.narola.hotelbooking.Room.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.narola.hotelbooking.Hotel.Hotel;
import com.narola.hotelbooking.Hotel.HotelDAO;
import com.narola.hotelbooking.Hotel.IHotelDAO;
import com.narola.hotelbooking.Room.dao.IRoomDAO;
import com.narola.hotelbooking.Room.model.Room;
import com.narola.hotelbooking.Room.service.IRoomService;
import com.narola.hotelbooking.Room.service.Impl.RoomServiceImpl;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.Utility.DAOFactory;
import com.narola.hotelbooking.Utility.UserURLConstant;

/**
 * Servlet implementation class ListRoomServlet
 */
public class ListRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IRoomService roomService = new RoomServiceImpl();
		IHotelDAO HotelDAO = new HotelDAO();
		if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.DISPLAYALL_ROOMS_URL)) {
			try {
				int hotelId = Integer.parseInt(request.getParameter("hotelid"));
				List<Room> roomList = roomService.getAllRoom(hotelId);
				Hotel hotel = new Hotel();
				HttpSession session = request.getSession();
				hotel = HotelDAO.viewHotel(hotelId);
				session.setAttribute("hotel", hotel);
				request.setAttribute("rooms", roomList);
				RequestDispatcher rd = request.getRequestDispatcher("admin/room/ShowHotelWiseRooms.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				// Error to JSP
			}
		} else if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.VIEW_ROOMPAGE_URL)) {
			try {
				Room room = roomService.getRoom(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("room", room);
				RequestDispatcher rd = request.getRequestDispatcher("admin/room/ViewRoom.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				// Error to JSP
			}
		} else if (request.getRequestURI().equals(request.getContextPath() + UserURLConstant.ROOM_VIEW_URL)) {
			try {
				Room room = roomService.getRoom(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("room", room);
				RequestDispatcher rd = request.getRequestDispatcher("customerside/Roomview.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				// Error to JSP
			}
		}
	}

}
