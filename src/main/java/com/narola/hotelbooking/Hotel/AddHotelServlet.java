package com.narola.hotelbooking.Hotel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.narola.hotelbooking.Room.dao.IRoomDAO;
import com.narola.hotelbooking.Room.dao.RoomDAOMySQL;
import com.narola.hotelbooking.Room.model.Room;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.Utility.Constant;
import com.narola.hotelbooking.Utility.DAOFactory;

/**
 * Servlet implementation class AddHotelServlet
 */
public class AddHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IRoomDAO roomDAO = DAOFactory.getInstance().getRoomDAO();
		IHotelDAO HotelDAO = new HotelDAO();
		Hotel hotel = new Hotel();
		hotel.setName(request.getParameter("name"));
		hotel.setCityId(Integer.parseInt(request.getParameter("cityid")));
		hotel.setStateId(Integer.parseInt(request.getParameter("stateid")));
		hotel.setAddress(request.getParameter("address"));
		hotel.setRating(Integer.parseInt(request.getParameter("rating")));
		hotel.setEmailId(request.getParameter("email"));
		hotel.setServices(request.getParameter("service"));
		hotel.setPolicy(request.getParameter("policy"));
		hotel.setImage("");

		hotel.setId(HotelDAO.inserData(hotel));

		String[] hoursarray = request.getParameterValues("hours");
		String[] refundarray = request.getParameterValues("refund");

		CancellationRules cancellationrule = new CancellationRules();

		cancellationrule.setHotelid(hotel.getId());
		for (int i = 0; i < hoursarray.length; i++) {
			System.out.println(hoursarray[i]);
			if (!hoursarray[i].isEmpty() || !refundarray[i].isEmpty()) {
				cancellationrule.setHours(Integer.parseInt(hoursarray[i]));
				cancellationrule.setRefundpercentsge(Integer.parseInt(refundarray[i]));

				cancellationrule.setId(CancellationRulesDAO.inserData(cancellationrule));
			}
		}

		Room room = new Room();
		room.setName(request.getParameter("roomname"));
		room.setHotelID(hotel.getId());
		room.setPrice(Double.valueOf(request.getParameter("price")));
		room.setQty(Integer.parseInt(request.getParameter("qty")));
		room.setInclusions(request.getParameter("inclusions"));
		room.setMaxcapacity(Integer.valueOf(request.getParameter("maxcapacity")));
		room.setDescription(request.getParameter("description"));
		room.setImage("");

		room.setId(roomDAO.inserData(room));

		String hotelDefaultImage = null, roomDefaultImage = null;
		String hotelfilePath = getServletContext().getInitParameter("imagefolderpath") + "hotels\\" + hotel.getId();

		String roomfilePath = getServletContext().getInitParameter("imagefolderpath") + "rooms\\" + room.getId();

		File folder = new File(hotelfilePath);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		File folderroom = new File(roomfilePath);
		if (!folderroom.exists()) {
			folderroom.mkdirs();
		}
		for (Part filePart : request.getParts()) {
			if (filePart.getName().equalsIgnoreCase("photos") && filePart.getSize() > 0) {
				String submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
				SaveimageInFolder(filePart, hotelfilePath + "\\" + submitedfilename);
			} else if (filePart.getName().equalsIgnoreCase("roomphotos")) {
				String submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
				SaveimageInFolder(filePart, roomfilePath + "\\" + submitedfilename);
			} else if (filePart.getName().equalsIgnoreCase("mainphoto")) {
				String submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
				hotelDefaultImage = "main" + hotel.getId()
						+ submitedfilename.substring(submitedfilename.lastIndexOf("."));
				SaveimageInFolder(filePart, hotelfilePath + "\\" + hotelDefaultImage);
			} else if (filePart.getName().equalsIgnoreCase("roommainphoto") && filePart.getSize() > 0) {
				String submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
				roomDefaultImage = "main" + room.getId()
						+ submitedfilename.substring(submitedfilename.lastIndexOf("."));
				SaveimageInFolder(filePart, roomfilePath + "\\" + roomDefaultImage);
			}
		}
		hotel.setImage(hotelDefaultImage);
		HotelDAO.updateData(hotel);

		room.setImage(roomDefaultImage);
		roomDAO.updateData(room);
		response.sendRedirect(request.getContextPath() + AdminURLConstant.DISPLAYALL_HOTELS_URL);
	}

	void SaveimageInFolder(Part imagepart, String imagefullpath) throws IOException {
		File newFile = new File(imagefullpath);
		InputStream inputStream = imagepart.getInputStream();
		final byte[] imgAsBytes = new byte[1024];
		FileOutputStream fos = new FileOutputStream(newFile);
		int read = 0;
		while ((read = inputStream.read(imgAsBytes)) != -1) {
			fos.write(imgAsBytes, 0, read);
		}

		fos.flush();
		fos.close();
	}

}
