package com.testspring.v2;

import java.util.Iterator;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

public class V2Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//		applicationContext.getEnvironment().setDefaultProfiles("dev");
//		applicationContext.getEnvironment().setActiveProfiles("prod");
		applicationContext.register(AppConfig.class);
		applicationContext.refresh();
		Book book = applicationContext.getBean(Book.class);
		System.out.println(book);
		System.out.println(book.getEnvironment().getProperty("jdbc.url"));
		System.out.println(book.getEnvironment().getProperty("email.host"));
		System.out.println(book.getEnvironment().containsProperty("TEST_VAR"));
		System.out.println("==========================");
		for (Iterator it = ((AbstractEnvironment) book.getEnvironment()).getPropertySources().iterator(); it
				.hasNext();) {
			PropertySource propertySource = (PropertySource) it.next();
			if (propertySource instanceof MapPropertySource) {
				System.out.println(((MapPropertySource) propertySource).getSource());
				System.out.println("==========================");
			}
		}

//		Order order = applicationContext.getBean(Order.class);
//		System.out.println(order.getBook().getBookName());
//		Transaction transaction = applicationContext.getBean(Transaction.class);
//		System.out.println(transaction.getDbUrl());
		applicationContext.registerShutdownHook();

	}

}
