package com.testspring.beanlifecycle;

public class testbean {
	private double price;
	
	public testbean() {
		super();
	}
	
	public double getPrice() {
		System.out.println("get method of price");
		return this.price;
	}
	public void setPrice(double amount) {
		System.out.println("set method of price");
		this.price=amount;
	}
	
	@Override
	public String toString() {
		return "testbean [price=" + price + "]";
	}
	
	public void init() {
		System.out.println("Init method");
	}
	public void destroy() {
		System.out.println("destroy method");
	}
	
}
