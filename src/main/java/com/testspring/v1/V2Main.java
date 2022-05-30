package com.testspring.v1;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.testspring.v2.Order;
import com.testspring.v2.Transaction;

public class V2Main {

	public static void main(String[] args) {

		AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("configV1.xml");

		Transaction transaction = applicationContext.getBean(Transaction.class);
		System.out.println(transaction.getDbUrl());
		applicationContext.registerShutdownHook();

	}

}
