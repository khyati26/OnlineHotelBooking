package com.testspring.beanlifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class test {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("com/testspring/beanlifecycle/lifecycleconfig.xml");
		/*
		 * testbean a= (testbean)context.getBean("bean11"); System.out.println(a);
		 * context.registerShutdownHook();
		 * 
		 * System.out.println("------------------------------------------------");
		 * 
		 * testbean2 b= (testbean2)context.getBean("bean2"); System.out.println(b);
		 */
		

		System.out.println("------------------------------------------------");
		
		testbean3 c= (testbean3)context.getBean("bean3");
		System.out.println(c);
		
	}
}
