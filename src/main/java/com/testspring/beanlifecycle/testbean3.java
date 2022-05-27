package com.testspring.beanlifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class testbean3 {
private double price;
	
	public testbean3() {
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
		return "testbean3 [price=" + price + "]";
	}

	@PostConstruct
	public void init() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Init method with annotation");
		
	}
	
	@PreDestroy
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("destroy method with annotation");
	}
}
