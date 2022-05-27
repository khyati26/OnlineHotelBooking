package com.testspring.v1;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class V2Main {

	public static void main(String[] args) {

		AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("configV1.xml");
		Book book = applicationContext.getBean(Book.class);
		System.out.println(book.getBookName());
		System.out.println(book.getBookAuthor());
		applicationContext.registerShutdownHook();
		
	}

}
