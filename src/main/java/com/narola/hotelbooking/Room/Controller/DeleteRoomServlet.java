package com.narola.hotelbooking.Room.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Hotel.Hotel;
import com.narola.hotelbooking.Room.service.IRoomService;
import com.narola.hotelbooking.Room.service.Impl.RoomServiceImpl;
import com.narola.hotelbooking.Utility.AdminURLConstant;

/**
 * Servlet implementation class DeleteRoomServlet
 */
public class DeleteRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			IRoomService roomService = new RoomServiceImpl();
			roomService.deleteRoom(Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			Hotel h = (Hotel) session.getAttribute("hotel");
			response.sendRedirect(
					request.getContextPath() + AdminURLConstant.DISPLAYALL_ROOMS_URL + "?hotelid=" + h.getId());
		} catch (ApplicationException e) {
			// Error to JSP
		}
	}

}
