package com.testspring.v1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class V1Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("configV1.xml");

		Book book = applicationContext.getBean(Book.class);
		System.out.println(book.getBookName());
		System.out.println(book.getBookAuthor());
		System.out.println(book.getPublisher());

		Book book2 = (Book) applicationContext.getBean("bookBean");
		System.out.println(book == book2);

		Order order = applicationContext.getBean(Order.class);
		System.out.println(order.getOrderId());
		System.out.println(order.getQty());
		System.out.println(order.getBook().getBookName());
		
		JavaCollection collection=applicationContext.getBean(JavaCollection.class);
		System.out.println(collection.getAddressList());
		System.out.println(collection.getAddressSet());
		System.out.println(collection.getAddressProp());
		System.out.println(collection.getAddressMap());
		System.out.println(collection.getBook().getPublisher());
		System.out.println(collection.getBook().getBookName());
		
		
		
		ClientService clientService = (ClientService) applicationContext.getBean("clientService");
		clientService.setStr("Hello");
		System.out.println( clientService.getStr());
		
		AccountService accountService = (AccountService) applicationContext.getBean("accountService");
		accountService.setStr("account str");
		System.out.println(accountService.getStr());
		applicationContext.registerShutdownHook();
	}
}
