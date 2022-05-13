package com.narola.hotelbooking.Hotel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.narola.hotelbooking.Utility.AdminURLConstant;

/**
 * Servlet implementation class UpdateHotelServlet
 */
public class UpdateHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] hoursarray = request.getParameterValues("hours");
		String[] refundarray = request.getParameterValues("refund");
		String[] cancleidarray = request.getParameterValues("cancelid");
		String[] deletecancleidarray = request.getParameterValues("deletecancelid");

		CancellationRules cancellationrules = new CancellationRules();

		cancellationrules.setHotelid(Integer.parseInt(request.getParameter("id")));
		if (hoursarray != null) {
			for (int i = 0; i < hoursarray.length; i++) {
				if (!cancleidarray[i].equals("0")) {
					cancellationrules.setHours(Integer.parseInt(hoursarray[i]));
					cancellationrules.setRefundpercentsge(Integer.parseInt(refundarray[i]));
					cancellationrules.setId(Integer.parseInt(cancleidarray[i]));
					CancellationRulesDAO.updateData(cancellationrules);
				} else {
					cancellationrules.setHours(Integer.parseInt(hoursarray[i]));
					cancellationrules.setRefundpercentsge(Integer.parseInt(refundarray[i]));
					cancellationrules.setId(CancellationRulesDAO.inserData(cancellationrules));

				}
			}
		}

		if (deletecancleidarray != null && deletecancleidarray.length != 0) {
			for (String id : deletecancleidarray) {
				CancellationRulesDAO.deleteData(Integer.parseInt(id));
			}
		}

		Hotel hotel = new Hotel();
		hotel.setId(Integer.parseInt(request.getParameter("id")));
		hotel.setName(request.getParameter("name"));
		hotel.setCityId(Integer.parseInt(request.getParameter("cityid")));
		hotel.setStateId(Integer.parseInt(request.getParameter("stateid")));
		hotel.setAddress(request.getParameter("address"));
		hotel.setRating(Integer.parseInt(request.getParameter("rating")));
		hotel.setEmailId(request.getParameter("email"));
		hotel.setServices(request.getParameter("service"));
		hotel.setPolicy(request.getParameter("policy"));

		String mainfilename = null;
		String submitedfilename = null;
//			String filePath = getServletContext().getRealPath("/") + ImagePathConstant.HOTEL_FOLDER_PARENTPATH
//					+ h.getId();
		String filePath = getServletContext().getInitParameter("imagefolderpath") + "hotels\\" + hotel.getId();

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
			if (filePart.getName().equalsIgnoreCase("photos") && !filePart.getSubmittedFileName().isEmpty()) {

				submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
				SaveimageInFolder(filePart, filePath + "\\" + submitedfilename);

			}
			if (filePart.getName().equalsIgnoreCase("mainphoto")) {
				if (filePart.getSubmittedFileName().isEmpty()) {
					hotel.setImage(request.getParameter("hiddenmainphoto"));
				} else {
					submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
					mainfilename = "main" + hotel.getId()
							+ submitedfilename.substring(submitedfilename.lastIndexOf("."));
					SaveimageInFolder(filePart, filePath + "\\" + mainfilename);
					hotel.setImage(mainfilename);
				}
			}
		}

		HotelDAO.updateData(hotel);
		CancellationRulesDAO.updateData(cancellationrules);
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
		inputStream.close();

	}

}
