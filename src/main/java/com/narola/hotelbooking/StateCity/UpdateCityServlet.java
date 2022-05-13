package com.narola.hotelbooking.StateCity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.narola.hotelbooking.Utility.AdminURLConstant;

/**
 * Servlet implementation class UpdateCityServlet
 */
public class UpdateCityServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("id") == null) {
			request.setAttribute("errMsg", "Someting went wrong !! try again..");
			RequestDispatcher rd = request.getRequestDispatcher("admin/statecity/City.jsp");
			rd.forward(request, response);
			return;
		}else {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("states", StateDAO.getStates());
			request.setAttribute("city", CityDAO.viewCity(id));
			RequestDispatcher rd = request.getRequestDispatcher("admin/statecity/UpdateCity.jsp");
			rd.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("popular") + " "+request.getParameter("popular1"));
		City city = new City();
		city.setCityName(request.getParameter("cityname"));
		city.setId(Integer.parseInt(request.getParameter("cityid")));
		
		city.setStateId(Integer.parseInt(request.getParameter("stateid")));
//		city.setImage();
		String submitedfilename = request.getPart("cityimage").getSubmittedFileName().replaceAll("\\s+", "").trim();
		String	mainfilename = city.getCityName() + submitedfilename.substring(submitedfilename.lastIndexOf("."));
		SaveimageInFolder(request.getPart("cityimage"),getServletContext().getInitParameter("imagefolderpath") + "cities\\"+mainfilename );
		city.setImage(mainfilename);
		
		city.setPopular(Integer.parseInt(request.getParameter("popular")));
		CityDAO.updateCityData(city);
		response.sendRedirect(request.getContextPath() + AdminURLConstant.DISPLAYALL_CITIES_URL);
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
