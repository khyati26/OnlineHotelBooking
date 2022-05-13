package com.narola.hotelbooking.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Hotel.CancellationRules;
import com.narola.hotelbooking.Utility.ConnectDB;

public class CustomerDAO {

	public static Customer getUserDataById(int id) throws DatabaseException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = ConnectDB.getConnection().prepareStatement("SELECT * from customer where id = ?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			Customer c = null;
			if (rs.next()) {
				c = new Customer();
				c.setId(rs.getInt(1));
				c.setFirstName(rs.getString(2));
				c.setLastName(rs.getString(3));
				c.setEmail(rs.getString(4));
				c.setMobile(rs.getString(5));
			}
			return c;
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException("Exception while getting customer by its  ID : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps, rs);
		}
	}

	public static Customer getUserData(String email1) throws DatabaseException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = ConnectDB.getConnection().prepareStatement("SELECT * from customer where email = ?");
			ps.setString(1, email1);
			rs = ps.executeQuery();
			Customer c = null;
			if (rs.next()) {
				c = new Customer();
				c.setId(rs.getInt(1));
				c.setFirstName(rs.getString(2));
				c.setLastName(rs.getString(3));
				c.setEmail(rs.getString(4));
				c.setMobile(rs.getString(5));
			}
			return c;
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException("Exception while getting customer by its email ID : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps, rs);
		}
	}

	public static boolean checkUniqueEmail(String email1) throws DatabaseException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = ConnectDB.getConnection().prepareStatement("SELECT count(*) from customer where email = ?");
			ps.setString(1, email1);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) == 0 ? true : false;
			}
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException("Exception while checking emailid is unique or not : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps, rs);
		}

		return false;
	}

	public static int inserData(Customer h) throws DatabaseException {
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO " + "customer (firstname,lastname,email,mobile) " + "VALUES (?,?,?,?)";
			ps = ConnectDB.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, h.getFirstName());
			ps.setString(2, h.getLastName());
			ps.setString(3, h.getEmail());
			ps.setString(4, h.getMobile());
			ps.executeUpdate();
			ResultSet generatedid = ps.getGeneratedKeys();
			if (generatedid.next()) {
				return generatedid.getInt(1);
			}
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException("Exception while inserting Customer details : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps);
		}
		return 0;
	}

}
