package com.narola.hotelbooking.Room;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.narola.hotelbooking.Hotel.Hotel;
import com.narola.hotelbooking.Hotel.HotelDAO;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.Utility.DAOFactory;

/**
 * Servlet implementation class DeleteRoomServlet
 */
public class DeleteRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IRoomDAO roomDAO = DAOFactory.getInstance().getRoomDAO();
	
		int id = Integer.parseInt(request.getParameter("id"));
		
		if (roomDAO.deleteRoom(id)) {
			HttpSession session=request.getSession();
			Hotel h=(Hotel)session.getAttribute("hotel");
			response.sendRedirect(request.getContextPath() + AdminURLConstant.DISPLAYALL_ROOMS_URL+"?hotelid="+h.getId());
		} 
		
	}


}
