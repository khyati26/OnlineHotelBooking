package com.testspring.v1;

import java.util.*;

public class JavaCollection {

	private List<String> addressList;
	private Set<String> addressSet;
	private Map<String, Integer> addressMap;
	private Properties addressProp;
	
	private Book book;
	
	
	
	
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public  List<String> getAddressList() {
		return addressList;
	}

	public void setAddressList(List addressList) {
		this.addressList = addressList;
	}

	public Set getAddressSet() {
		return addressSet;
	}

	public void setAddressSet(Set addressSet) {
		this.addressSet = addressSet;
	}

	public Map getAddressMap() {
		return addressMap;
	}

	public void setAddressMap(Map addressMap) {
		this.addressMap = addressMap;
	}

	public Properties getAddressProp() {
		return addressProp;
	}

	public void setAddressProp(Properties addressProp) {
		this.addressProp = addressProp;
	}

}
