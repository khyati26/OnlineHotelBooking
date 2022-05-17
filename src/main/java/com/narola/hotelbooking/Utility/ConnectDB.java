package com.narola.hotelbooking.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.narola.hotelbooking.Exception.DatabaseException;

public class ConnectDB {

	private static ConnectDB connectDB = null;

	private Connection connection = null;
	private String dbname = null;
	private String url = null;
	private String username = null;
	private String password = null;

	private ConnectDB() {

	}

	public static ConnectDB getInstance() {
		if (connectDB == null) {
			connectDB = new ConnectDB();
		}
		return connectDB;
	}

	public void initializeConnection() throws DatabaseException {
		getConnection();
	}

	public Connection getConnection() throws DatabaseException {
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

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
