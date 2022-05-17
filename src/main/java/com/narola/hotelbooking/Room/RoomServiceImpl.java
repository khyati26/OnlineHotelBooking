package com.narola.hotelbooking.Room;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.narola.hotelbooking.Utility.DAOFactory;

public class RoomServiceImpl implements IRoomService {

	@Override
	public void addRoom(Room room, HttpServletRequest request) throws IOException, ServletException {
		IRoomDAO roomDAO = DAOFactory.getInstance().getRoomDAO();
		room.setId(roomDAO.inserData(room));

		String mainfilename = null;
		String filePath = request.getServletContext().getInitParameter("imagefolderpath") + "rooms\\" + room.getId();

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
				File folder2 = new File(filePath + "\\" + mainfilename);
			}
		}

		room.setImage(mainfilename);
		roomDAO.updateData(room);
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
