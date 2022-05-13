package com.narola.hotelbooking.Authentication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.narola.hotelbooking.Customer.Customer;
import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Utility.ConnectDB;

public class UserDAO {
    
	public static boolean findUser(String email1,String pass) throws DatabaseException {
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			ps = ConnectDB.getConnection().prepareStatement("SELECT count(*) from user where email = ?  and password = ? and usertype = "+ 0);
			ps.setString(1, email1);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) == 0 ? true : false;
			}

		} catch (SQLException e) {
			throw new DatabaseException("Exception while finding user : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps, rs);
		}
		return false;
	}
	
	public static boolean findAdminUser(String email1,String pass) throws DatabaseException {
		PreparedStatement ps = null;
		ResultSet rs = null ;
		try {
			ps = ConnectDB.getConnection().prepareStatement("SELECT count(*) from user where email = ? and password = ? and usertype = "+ 1);
			ps.setString(1, email1);
			ps.setString(2, pass);
			rs = ps.executeQuery();
		      if (rs.next()) {
		        return rs.getInt(1) != 0 ? true : false;
		      }	
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Exception while finding admin : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps, rs);
		}
		return false;
		
	}
	
	public static int inserData(User user) throws DatabaseException {
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO "
					+ "user (email,password,customerid,usertype) "
					+ "VALUES (?,?,?,?)";
			ps = ConnectDB.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getCustomerId());
			ps.setInt(4, user.getUserType());
			ps.executeUpdate();
			ResultSet generatedid=ps.getGeneratedKeys();
			if(generatedid.next()) {
				return generatedid.getInt(1);
			}
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException("Exception while inserting user details : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps);
		}
		return 0;
	}

	
}
