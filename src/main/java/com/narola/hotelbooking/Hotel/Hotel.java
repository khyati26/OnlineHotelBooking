package com.narola.hotelbooking.Hotel;

import java.util.List;
import java.util.Collection;

public class Hotel {

	private int id;
	private String name;
	private String image;
	private String city;
	private int cityId;
	private int stateId;
	private String state;
	private String address;
	private int rating;
	private String services;
	private String emailId;
	private String policy;
	private String createdon;
	private String updatedon;
	private Collection<String> images;

	private List<CancellationRules> cancellationRulesList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityid) {
		this.cityId = cityid;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateid) {
		this.stateId = stateid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailid) {
		this.emailId = emailid;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}

	public String getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(String updatedon) {
		this.updatedon = updatedon;
	}

	public Collection<String> getImages() {
		return images;
	}

	public void setImages(Collection<String> images) {
		this.images = images;
	}

	public List<CancellationRules> getCancellationRulesList() {
		return cancellationRulesList;
	}

	public void setCancellationRulesList(List<CancellationRules> cancellationRulesList) {
		this.cancellationRulesList = cancellationRulesList;
	}

}
