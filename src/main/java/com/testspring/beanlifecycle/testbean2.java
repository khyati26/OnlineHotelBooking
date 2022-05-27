package com.testspring.beanlifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class testbean2 implements InitializingBean , DisposableBean {
private double price;
	
	public testbean2() {
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
		return "testbean2 [price=" + price + "]";
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Init method of interface");
		
	}

	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("destroy method of interface");
	}
	
	

}
