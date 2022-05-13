package com.narola.hotelbooking.StateCity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.narola.hotelbooking.Utility.AdminURLConstant;

//import com.google.gson.Gson;
//import com.mysql.cj.xdevapi.JsonArray;

/**
 * Servlet implementation class CityServlet
 */
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.DISPLAYALL_CITIES_URL)) {
			
			request.setAttribute("cities", CityDAO.getData());
			RequestDispatcher rd = request.getRequestDispatcher("admin/statecity/City.jsp");
			rd.forward(request, response);
			
		} else if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.POPULAR_CITIES_URL)) {

			if(!request.getParameter("uncheckedids").isEmpty()) {
				ArrayList<String> myList2 = new ArrayList<>(Arrays.asList(request.getParameter("uncheckedids").split(",")));
				if (!myList2.isEmpty()) {
					for (String integer : myList2) {
						CityDAO.uncheckPopularCity(Integer.parseInt(integer));
					}
				}
			}
			if(!request.getParameter("checkedids").isEmpty()) {
				ArrayList<String> myList = new ArrayList<>(Arrays.asList(request.getParameter("checkedids").split(",")));
				if (!myList.isEmpty()) {
					for (String integer : myList) {
						CityDAO.checkPopularCity(Integer.parseInt(integer));
					}
				}
			}		
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("True");
			
		}else if (request.getRequestURI().equals(request.getContextPath() + AdminURLConstant.CITY_STATEWISE_URL)) {
			 
			List<City> citylist = CityDAO.getStateWiseData(Integer.parseInt(request.getParameter("stateid")));
            Gson json = new Gson();
            String cityjson =json.toJson(citylist); 
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(cityjson);
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
