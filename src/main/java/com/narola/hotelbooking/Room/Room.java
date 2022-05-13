package com.narola.hotelbooking.Room;

import java.util.Collection;

import com.narola.hotelbooking.Hotel.Hotel;

public class Room {

	private int id;
	private String name;
	private int hotelID;
	private double price;
	private int qty;
	private String inclusions;
	private String image;
	private int maxcapacity;
	private String description;
	private String createdon;
	private String updatedon;
	private Hotel hotel;
	private Collection<String> images;
	private int availableroom;
	

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel h) {
		this.hotel = h;
	}

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

	public int getHotelID() {
		return hotelID;
	}

	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getInclusions() {
		return inclusions;
	}

	public void setInclusions(String inclusions) {
		this.inclusions = inclusions;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getMaxcapacity() {
		return maxcapacity;
	}

	public void setMaxcapacity(int maxcapacity) {
		this.maxcapacity = maxcapacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getAvailableroom() {
		return availableroom;
	}

	public void setAvailableroom(int availableroom) {
		this.availableroom = availableroom;
	}

	

}
