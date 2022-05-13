package com.narola.hotelbooking.Room;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.narola.hotelbooking.Hotel.Hotel;
import com.narola.hotelbooking.Hotel.HotelDAO;
import com.narola.hotelbooking.Utility.AdminURLConstant;

/**
 * Servlet implementation class RoomServlet
 */
public class AddRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.ADD_ROOMPAGE_URL)) {
			request.setAttribute("room", new Room());
			RequestDispatcher rd = request.getRequestDispatcher("admin/room/AddHotelWiseRoom.jsp");
			rd.forward(request, response);
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		
			Room room = new Room();
			room.setName(request.getParameter("name"));
			room.setHotelID(Integer.parseInt(request.getParameter("hotelid")));
			room.setPrice(Double.valueOf(request.getParameter("price")));
			room.setQty(Integer.parseInt(request.getParameter("qty")));
			room.setInclusions(request.getParameter("inclusions"));
			room.setMaxcapacity(Integer.valueOf(request.getParameter("maxcapacity")));
			room.setDescription(request.getParameter("description"));
			room.setImage("");

			room.setId(RoomDAO.inserData(room));
			
			String mainfilename = null;
			String filePath = getServletContext().getInitParameter("imagefolderpath")+"rooms\\"+room.getId();
			
			File folder = new File(filePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			
			for (Part filePart : request.getParts()) {
				if (filePart.getName().equalsIgnoreCase("roomphotos") && filePart != null) {

					String submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
					SaveimageInFolder(filePart, filePath + "\\" + submitedfilename);

				} else if (filePart.getName().equalsIgnoreCase("roommainphoto")) {

					String submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
					mainfilename = "main" + room.getId() + submitedfilename.substring(submitedfilename.lastIndexOf("."));
					SaveimageInFolder(filePart, filePath + "\\" + mainfilename);
					File folder2 = new File(filePath+ "\\" + mainfilename);
					
				}
			}

			room.setImage(mainfilename);
			RoomDAO.updateData(room);		
			response.sendRedirect(request.getContextPath() + AdminURLConstant.DISPLAYALL_ROOMS_URL+"?hotelid="+room.getHotelID());
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
