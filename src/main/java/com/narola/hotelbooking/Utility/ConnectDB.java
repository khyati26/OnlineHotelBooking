package com.narola.hotelbooking.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.narola.hotelbooking.Exception.DatabaseException;

public class ConnectDB {

	static Connection connection = null;
	static String dbname = null;
	static String url = null;
	static String username = null;
	static String password = null;

	public static void initializeConnection() throws DatabaseException {
		getConnection();
	}

	public static Connection getConnection() throws DatabaseException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(getUrl() + "/" + getDbname(), getUsername(), getPassword());
			}
		} catch (ClassNotFoundException e) {
			throw new DatabaseException("Database driver error", e);
		} catch (SQLException e) {
			throw new DatabaseException("Database connection error", e);
		}
		return connection;
	}
	
	public static void closeResource(Statement statement) {
		closeResource(statement, null);
	}

	public static void closeResource(Statement statement, ResultSet resultSet) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getDbname() {
		return dbname;
	}

	public static void setDbname(String dbname) {
		ConnectDB.dbname = dbname;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		ConnectDB.url = url;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		ConnectDB.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		ConnectDB.password = password;
	}
}
