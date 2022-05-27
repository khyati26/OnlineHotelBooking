package com.testspring.setterInjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/testspring/ref/refconfig.xml");
		A a = (A) context.getBean("Aref");
		System.out.println(a.getX());
		System.out.println(a.getObj().getY());
		
		System.out.println(a);
	
	
	}
}
