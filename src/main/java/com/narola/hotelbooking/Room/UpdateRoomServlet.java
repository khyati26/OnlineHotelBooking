package com.narola.hotelbooking.Room;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.narola.hotelbooking.Hotel.Hotel;
import com.narola.hotelbooking.Hotel.HotelDAO;
import com.narola.hotelbooking.Utility.AdminURLConstant;
import com.narola.hotelbooking.Utility.Constant;

/**
 * Servlet implementation class UpdateRoomServlet
 */
public class UpdateRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		if (request.getParameter("id") == null) {
			request.setAttribute("errMsg", "");
			RequestDispatcher rd = request.getRequestDispatcher("admin/room/UpdateHotelWiseRoom.jsp");
			rd.forward(request, response);
			return;
		}

		Room room = RoomDAO.viewRoom(id);

		Collection<String> images = new ArrayList<>();
		File imageDir = new File(getServletContext().getInitParameter("imagefolderpath") + "rooms\\" + room.getId());
		if (!imageDir.exists()) {
			imageDir.mkdirs();
		}
		for (File imageFile : imageDir.listFiles()) {
			String imageFileName = imageFile.getName();
			if (!imageFileName.contains("main" + room.getId())) {
				images.add(imageFileName);
			}
		}
		room.setImages(images);
		request.setAttribute("room", room);
		RequestDispatcher rd = request.getRequestDispatcher("admin/room/UpdateHotelWiseRoom.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Room room = new Room();
		room.setId(Integer.parseInt(request.getParameter("id")));
		room.setName(request.getParameter("name"));
		room.setHotelID(Integer.parseInt(request.getParameter("hotelid")));
		room.setPrice(Double.valueOf(request.getParameter("price")));
		room.setQty(Integer.parseInt(request.getParameter("qty")));
		room.setInclusions(request.getParameter("inclusions"));
		room.setMaxcapacity(Integer.parseInt(request.getParameter("maxcapacity")));
		room.setDescription(request.getParameter("description"));

		String mainfilename = null;
		String submitedfilename = null;
		String filePath = getServletContext().getInitParameter("imagefolderpath") + "rooms\\" + room.getId();

		String deletedfiles = request.getParameter("deletedfiles");
		for (String file : deletedfiles.split(",")) {
			if (!file.isEmpty()) {
				try {
					Files.deleteIfExists(Paths.get(filePath + "\\" + file));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		File folder = new File(filePath);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		for (Part filePart : request.getParts()) {
			if (filePart.getName().equalsIgnoreCase("roomphotos") && !filePart.getSubmittedFileName().isEmpty()) {
				submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
				SaveimageInFolder(filePart, filePath + "\\" + submitedfilename);
			}
			if (filePart.getName().equalsIgnoreCase("roommainphoto") && !filePart.getSubmittedFileName().isEmpty()) {
				submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
				mainfilename = "main" + room.getId() + submitedfilename.substring(submitedfilename.lastIndexOf("."));
				SaveimageInFolder(filePart, filePath + "\\" + mainfilename);
				room.setImage(mainfilename);
			} else {
				room.setImage(request.getParameter("hiddenmainphoto"));
			}
		}

		RoomDAO.updateData(room);
		response.sendRedirect(
				request.getContextPath() + AdminURLConstant.DISPLAYALL_ROOMS_URL + "?hotelid=" + room.getHotelID());
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
		inputStream.close();

	}

}
