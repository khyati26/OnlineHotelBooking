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

import com.narola.hotelbooking.StateCity.City;
import com.narola.hotelbooking.Utility.AdminURLConstant;
/**
 * Servlet implementation class AddCityServlet
 */
public class AddCityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("states", StateDAO.getStates());
		RequestDispatcher rd = request.getRequestDispatcher("admin/statecity/AddCity.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		System.out.println(request.getParameter("cityname") + " " );
//		System.out.println(request.getParameter("popular")+ " ");
//		System.out.println(Integer.parseInt(request.getParameter("stateid")));
		City city=new City();
		city.setCityName(request.getParameter("cityname"));
		city.setPopular(Integer.parseInt(request.getParameter("popular")));
		
		city.setStateId(Integer.parseInt(request.getParameter("stateid")));
		
		
		String submitedfilename = request.getPart("cityimage").getSubmittedFileName().replaceAll("\\s+", "").trim();
		String	mainfilename = city.getCityName() + submitedfilename.substring(submitedfilename.lastIndexOf("."));
		SaveimageInFolder(request.getPart("cityimage"),getServletContext().getInitParameter("imagefolderpath") + "cities\\"+mainfilename );
		city.setImage(mainfilename);
		city.setId(CityDAO.addcity(city));
		int id = city.getId();
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
