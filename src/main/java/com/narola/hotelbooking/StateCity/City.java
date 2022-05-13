package com.narola.hotelbooking.StateCity;

public class City {
	private int id;
	private int stateId;
	private String cityName;
	private int popular;
	private String stateName;
	private String image;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateid) {
		this.stateId = stateid;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public int getPopular() {
		return popular;
	}
	public void setPopular(int popular) {
		this.popular = popular;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
