package com.narola.hotelbooking.Room;

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
		
		IRoomDAO roomDAO = DAOFactory.getInstance().getRoomDAO();

		if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.DISPLAYALL_ROOMS_URL)) {
			
			int id = Integer.parseInt(request.getParameter("hotelid"));
			List<Room> roomList = roomDAO.showHotelWiseRoom(id);

			Hotel hotel = new Hotel();
			HttpSession session = request.getSession();
			hotel = HotelDAO.viewHotel(id);
			session.setAttribute("hotel", hotel);
			request.setAttribute("rooms", roomList);
			RequestDispatcher rd = request.getRequestDispatcher("admin/room/ShowHotelWiseRooms.jsp");
			rd.forward(request, response);

		}
		else if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.VIEW_ROOMPAGE_URL)) {

			int id = Integer.parseInt(request.getParameter("id"));
			Room room = roomDAO.viewRoom(id);
			request.setAttribute("room", room);
			RequestDispatcher rd = request.getRequestDispatcher("admin/room/ViewRoom.jsp");
			rd.forward(request, response);
		}
	    else if (request.getRequestURI().equals(request.getContextPath() + UserURLConstant.ROOM_VIEW_URL)) {
	    	
	    	int id = Integer.parseInt(request.getParameter("id"));
			Room room = roomDAO.viewRoom(id);
			request.setAttribute("room", room);
			RequestDispatcher rd = request.getRequestDispatcher("customerside/Roomview.jsp");
			rd.forward(request, response);
	    
	    }

		
	}

}
