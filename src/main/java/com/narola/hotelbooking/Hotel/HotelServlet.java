package com.narola.hotelbooking.Hotel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.StateCity.CityDAO;
import com.narola.hotelbooking.StateCity.StateDAO;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.Utility.Constant;

/**
 * Servlet implementation class HotelServlet1
 */
public class HotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IHotelDAO HotelDAO = new HotelDAO();
		if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.DISPLAYALL_HOTELS_URL)) {
			try {
				List<Hotel> hotels = HotelDAO.showData();
				request.setAttribute("hotels", hotels);
				RequestDispatcher rd = request.getRequestDispatcher("admin/hotel/ShowAllHotels.jsp");
				rd.forward(request, response);
			} catch (DatabaseException e) {
				e.printStackTrace();
				request.setAttribute("errMsg", "Something went wrong.");
			}
		} else if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.VIEW_HOTELPAGE_URL)) {

			int id = Integer.parseInt(request.getParameter("id"));
			Hotel hotel = HotelDAO.viewHotel(id);
			hotel.setCancellationRulesList(CancellationRulesDAO.getCancellationRulesByHotel(id));
			request.setAttribute("hotel", hotel);
			RequestDispatcher rd = request.getRequestDispatcher("admin/hotel/ViewHotel.jsp");
			rd.forward(request, response);

		} else if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.DELETE_HOTEL_URL)) {

			HotelDAO.deleteHotel(Integer.parseInt(request.getParameter("id")));
			response.sendRedirect(request.getContextPath() + AdminURLConstant.DISPLAYALL_HOTELS_URL);

		} else if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.EDIT_HOTELPAGE_URL)) {

			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Hotel hotel = HotelDAO.viewHotel(id);
				hotel.setCancellationRulesList(CancellationRulesDAO.getCancellationRulesByHotel(id));

				Collection<String> images = new ArrayList<String>();
//	File imageDir = new File(
//			getServletContext().getRealPath("/") + ImagePathConstant.HOTEL_FOLDER_PARENTPATH + id);
				File imageDir = new File(
						getServletContext().getInitParameter("imagefolderpath") + "hotels\\" + hotel.getId());
				if (!imageDir.exists()) {
					imageDir.mkdirs();
				}
				for (File imageFile : imageDir.listFiles()) {
					String imageFileName = imageFile.getName();
					if (!imageFileName.contains("main" + hotel.getId())) {
						images.add(imageFileName);
					}
				}
				hotel.setImages(images);
				request.setAttribute("hotel", hotel);
				request.setAttribute("states", StateDAO.getStates());
				request.setAttribute("cities", CityDAO.getStateWiseData(hotel.getStateId()));
			} catch (DatabaseException e) {
				e.printStackTrace();
				request.setAttribute("errMsg", "Opps....");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errMsg", "Opps....");
			}
			RequestDispatcher rd = request.getRequestDispatcher("admin/hotel/UpdateHotel.jsp");
			rd.forward(request, response);

		} else if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.ADD_HOTELPAGE_URL)) {

			request.setAttribute("states", StateDAO.getStates());
			RequestDispatcher rd = request.getRequestDispatcher("admin/hotel/AddHotel.jsp");
			rd.forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
