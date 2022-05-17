package com.narola.hotelbooking.StateCity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Hotel.Hotel;
import com.narola.hotelbooking.Hotel.HotelDAO;
import com.narola.hotelbooking.Utility.ConnectDB;

public class CityDAO {

	public static City viewCity(int id) throws DatabaseException {
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement("select * from city where id = ?" );
			ps.setInt(1, id);
			 rs = ps.executeQuery();
			 City city =null;
			if (rs.next()) {
				city= new City();
				city.setId(rs.getInt(1));
				city.setCityName(rs.getString(3));
				city.setStateId(rs.getInt(2));
				city.setPopular(rs.getInt(4));
				city.setImage(rs.getString(5));
			}
			return city;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Exception while getting single city : "+e.getMessage(),e);
		}finally {
			ConnectDB.closeResource(ps,rs);
		}

	}

	
	public static void updateCityData(City city) throws DatabaseException {

		String query = "update city set "
				+ "cityname = ?,stateid = ?,image = ?,popular = ? "
				+ "where id = ?";
		PreparedStatement ps=null;
		try {
			 ps = ConnectDB.getInstance().getConnection().prepareStatement(query);
			ps.setString(1, city.getCityName());
			ps.setInt(2, city.getStateId());
			ps.setString(3, city.getImage());
			ps.setInt(4, city.getPopular());
			ps.setInt(5, city.getId());
			ps.executeUpdate() ;
		} catch (DatabaseException e) {			
			throw e;
		} catch (SQLException e) {						
			throw new DatabaseException("Exception while updatingting city details: "+e.getMessage(), e);
		}finally {
			ConnectDB.closeResource(ps);
		}
	}

	
	public static boolean deleteCity(int id) throws DatabaseException {
		PreparedStatement ps=null;
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement("delete from city where id = ? " );
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Exception while deleting city : "+e.getMessage(),e);
		}finally {
			ConnectDB.closeResource(ps);
		}
	}
	
	public static int addcity(City city) {
		PreparedStatement ps =null;
		try {
			String query = "INSERT INTO "
					+ "city (stateid,cityname,popular,image)"
					+ "VALUES (?,?,?,?)";
			ps = ConnectDB.getInstance().getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, city.getStateId());
			ps.setString(2, city.getCityName());
			ps.setInt(3, city.getPopular());
			ps.setString(4, city.getImage());
			ps.executeUpdate();
			ResultSet generatedid=ps.getGeneratedKeys();
			if(generatedid.next()) {
				return generatedid.getInt(1);
			}
		} catch (DatabaseException e) {			
			throw e;
		} catch (SQLException e) {						
			throw new DatabaseException("Exception while inserting city details : "+ e.getMessage(), e);
		}finally {
			ConnectDB.closeResource(ps);
		}
		return 0;
	}
	public static boolean checkPopularCity(int cityid) throws DatabaseException {
		PreparedStatement ps = null;
		String query = "update city set " + "popular = " + 1 + " where id = ? ";
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement(query);
			ps.setInt(1, cityid);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DatabaseException("Exception while check popular city : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps, null);
		}
	}

	public static boolean uncheckPopularCity(int cityid) {
		PreparedStatement ps = null;
		String query = "update city set " + "popular = " + 0 + " where id = ? ";
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement(query);
			ps.setInt(1, cityid);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DatabaseException("Exception while uncheck popular city : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps, null);
		}
	}

	public static List<City> getData() throws DatabaseException {
		Statement st = null;
		ResultSet rs = null;
		List<City> cityList = new ArrayList<>();
		try {
			st = ConnectDB.getInstance().getConnection().createStatement();
			rs = st.executeQuery("SELECT c.*,s.statename FROM city c,state s where c.stateid=s.id ");
			while (rs.next()) {
				City city = new City();
				city.setId(rs.getInt(1));
				city.setStateId(rs.getInt(2));
				city.setCityName(rs.getString(3));
				city.setPopular(rs.getInt(4));
				city.setImage(rs.getString(5));
				city.setStateName(rs.getString(8));
				cityList.add(city);
				
			}
			return cityList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Exception while getting cities : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(st, rs);
		}
	}

	public static List<City> getStateWiseData(int id) throws DatabaseException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<City> cityList = new ArrayList<>();
		try {
			// @formatter:off
			ps = ConnectDB.getInstance().getConnection().prepareStatement(
					"SELECT c.*,s.statename FROM city c,state s "
					+ "where c.stateid=s.id and c.stateid=?");
			// @formatter:on
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				City city = new City();
				city.setId(rs.getInt(1));
				city.setStateId(rs.getInt(2));
				city.setCityName(rs.getString(3));
				city.setPopular(rs.getInt(4));
				city.setStateName(rs.getString(7));
				cityList.add(city);
			}

			return cityList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Exception while getting state wise cities : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(ps, rs);
		}
	}
	
	public static List<City> getPopularCities(){
		Statement st = null;
		ResultSet rs = null;
		String query="select c.*,s.statename from mydb.state s, mydb.city c where s.id=c.stateid and c.popular = 1";
		List<City> cityList = new ArrayList<>();
		try {
			st = ConnectDB.getInstance().getConnection().createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				City city = new City();
				city.setId(rs.getInt(1));
				city.setStateId(rs.getInt(2));
				city.setCityName(rs.getString(3));
				city.setPopular(rs.getInt(4));
				city.setImage(rs.getString(5));
				city.setStateName(rs.getString(8));
				cityList.add(city);
			}
			return cityList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Exception while getting popular cities : " + e.getMessage(), e);
		} finally {
			ConnectDB.closeResource(st, rs);
		}
	}

}
