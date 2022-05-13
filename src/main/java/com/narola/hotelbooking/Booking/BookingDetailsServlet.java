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
import com.narola.hotelbooking.Hotel.SearchHotel;
import com.narola.hotelbooking.Room.Room;
import com.narola.hotelbooking.Room.RoomDAO;

/**
 * Servlet implementation class BookingServlet
 */
public class BookingDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
			
//		System.out.println((String)request.getParameter("roomid") + " "+request.getParameter("totprice2") );
		Gson g = new Gson();  
		HashMap<Double,String> s = g.fromJson((String)request.getParameter("roomid"), HashMap.class);  
		List<Room> roomList = new ArrayList<>();
		
		for(Entry<Double, String> pair : s.entrySet()) {
			Room room = RoomDAO.viewRoom(pair.getKey().intValue());
			room.setAvailableroom(Integer.parseInt(pair.getValue()));
			roomList.add(room);			
//			System.out.println(pair.getKey() + "  "+ pair.getValue());			
		}
		
		request.setAttribute("hotel", HotelDAO.viewHotel(Integer.parseInt(request.getParameter("hotelid"))));
		request.setAttribute("totalprice", request.getParameter("totprice2"));
		request.setAttribute("roomlist", roomList);
		
		session.setAttribute("roomlist", roomList);
		RequestDispatcher rd= request.getRequestDispatcher("customerside/Bookingpage.jsp");
		rd.forward(request, response);
	}

}
