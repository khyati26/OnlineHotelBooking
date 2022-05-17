package com.narola.hotelbooking.Utility;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DatabaseServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			String dbType = getServletContext().getInitParameter("DB-IN-USE");
			DAOFactory.getInstance().init(dbType);

			String dbName = getServletContext().getInitParameter(dbType + "_dbname");
			String dbUrl = getServletContext().getInitParameter(dbType + "_dburl");
			String userName = getServletContext().getInitParameter(dbType + "_username");
			String password = getServletContext().getInitParameter(dbType + "_password");

			ConnectDB.getInstance().setDbname(dbName);
			ConnectDB.getInstance().setUrl(dbUrl);
			ConnectDB.getInstance().setUsername(userName);
			ConnectDB.getInstance().setPassword(password);
			ConnectDB.getInstance().initializeConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
