package com.narola.hotelbooking.Utility;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DatabaseServlet extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {	
		super.init(config);
		String dbName = getServletContext().getInitParameter("dbname");
		String dbUrl = getServletContext().getInitParameter("dburl");
		String userName = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("password");		
		ConnectDB.setDbname(dbName);
		ConnectDB.setUrl(dbUrl);
		ConnectDB.setUsername(userName);
		ConnectDB.setPassword(password);	
		try {
			ConnectDB.initializeConnection();			
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	

}
