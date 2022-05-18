package com.narola.hotelbooking.Room.service.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.narola.hotelbooking.Exception.ApplicationException;
import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Hotel.Hotel;
import com.narola.hotelbooking.Hotel.HotelDAO;
import com.narola.hotelbooking.Hotel.IHotelDAO;
import com.narola.hotelbooking.Hotel.SearchHotelCriteria;
import com.narola.hotelbooking.Hotel.SearchHotelDAO;
import com.narola.hotelbooking.Hotel.SearchHotelResult;
import com.narola.hotelbooking.Room.dao.IRoomDAO;
import com.narola.hotelbooking.Room.model.Room;
import com.narola.hotelbooking.Room.service.IRoomService;
import com.narola.hotelbooking.Utility.DAOFactory;

public class RoomServiceImpl implements IRoomService {

	@Override
	public void addRoom(Room room, HttpServletRequest request) throws ApplicationException {
		try {
			IRoomDAO roomDAO = DAOFactory.getInstance().getRoomDAO();
			room.setId(roomDAO.inserData(room));
			String mainfilename = null;
			String filePath = request.getServletContext().getInitParameter("imagefolderpath") + "rooms\\"
					+ room.getId();

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
					mainfilename = "main" + room.getId()
							+ submitedfilename.substring(submitedfilename.lastIndexOf("."));
					SaveimageInFolder(filePart, filePath + "\\" + mainfilename);
					File folder2 = new File(filePath + "\\" + mainfilename);
				}
			}
			room.setImage(mainfilename);
			roomDAO.updateData(room);
		} catch (ServletException e) {
			throw new ApplicationException("Opps, something went wrong", e);
		} catch (IOException e) {
			throw new ApplicationException("Opps, something went wrong", e);
		}
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

	@Override
	public SearchHotelResult searchRooms(SearchHotelCriteria searchHotelCriteria) throws ApplicationException {
		try {
			Set<Room> roomSet = SearchHotelDAO.getAvailableRooms(searchHotelCriteria, searchHotelCriteria.getHotelId());
			IHotelDAO hotelDAO = new HotelDAO();
			Hotel hotel = hotelDAO.viewHotel(searchHotelCriteria.getHotelId());

			SearchHotelResult searchHotelResult = new SearchHotelResult();
			searchHotelResult.setRoomSet(roomSet);
			searchHotelResult.setHotel(hotel);
			return searchHotelResult;
		} catch (ApplicationException e) {
			throw e;
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps, something went wrong", e);
		} catch (SQLException e) {
			throw new ApplicationException("Opps, something went wrong", e);
		} catch (Exception e) {
			throw new ApplicationException("Opps, something went wrong", e);
		}
	}

	@Override
	public void deleteRoom(int roomId) throws ApplicationException {
		try {
			IRoomDAO roomDAO = DAOFactory.getInstance().getRoomDAO();
			roomDAO.deleteRoom(roomId);
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps, something went wrong", e);
		}
	}

	@Override
	public List<Room> getAllRoom(int hotelId) throws ApplicationException {
		try {
			IRoomDAO roomDAO = DAOFactory.getInstance().getRoomDAO();
			return roomDAO.showHotelWiseRoom(hotelId);
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps, something went wrong", e);
		}
	}

	@Override
	public Room getRoom(int roomId, HttpServletRequest request) throws ApplicationException {
		try {
			IRoomDAO roomDAO = DAOFactory.getInstance().getRoomDAO();
			Room room = roomDAO.viewRoom(roomId);
			Collection<String> images = new ArrayList<>();
			File imageDir = new File(
					request.getServletContext().getInitParameter("imagefolderpath") + "rooms\\" + room.getId());
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
			return room;
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps, something went wrong", e);
		}
	}

	@Override
	public Room getRoom(int roomId) throws ApplicationException {
		try {
			IRoomDAO roomDAO = DAOFactory.getInstance().getRoomDAO();
			return roomDAO.viewRoom(roomId);
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps, something went wrong", e);
		}
	}

	@Override
	public void updateRoom(Room room, HttpServletRequest request) throws ApplicationException {
		try {
			IRoomDAO roomDAO = DAOFactory.getInstance().getRoomDAO();
			String mainfilename = null;
			String submitedfilename = null;
			String filePath = request.getServletContext().getInitParameter("imagefolderpath") + "rooms\\"
					+ room.getId();
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
				if (filePart.getName().equalsIgnoreCase("roommainphoto")
						&& !filePart.getSubmittedFileName().isEmpty()) {
					submitedfilename = filePart.getSubmittedFileName().replaceAll("\\s+", "").trim();
					mainfilename = "main" + room.getId()
							+ submitedfilename.substring(submitedfilename.lastIndexOf("."));
					SaveimageInFolder(filePart, filePath + "\\" + mainfilename);
					room.setImage(mainfilename);
				} else {
					room.setImage(request.getParameter("hiddenmainphoto"));
				}
			}
			roomDAO.updateData(room);
		} catch (DatabaseException e) {
			throw new ApplicationException("Opps, something went wrong", e);
		} catch (IOException e) {
			throw new ApplicationException("Opps, something went wrong", e);
		} catch (ServletException e) {
			throw new ApplicationException("Opps, something went wrong", e);
		}
	}
}
