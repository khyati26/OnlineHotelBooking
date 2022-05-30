package com.testspring.v1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.testspring.v2.AppConfig;
import com.testspring.v2.Book;
import com.testspring.v2.Order;
import com.testspring.v2.Transaction;

public class V2Main {

	public static void main(String[] args) {

		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		Book book = applicationContext.getBean(Book.class);
		Order order = applicationContext.getBean(Order.class);
		System.out.println(order.getBook().getBookName());
		Transaction transaction = applicationContext.getBean(Transaction.class);
		System.out.println(transaction.getDbUrl());
		applicationContext.registerShutdownHook();

	}

}
