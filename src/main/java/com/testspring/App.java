package com.testspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        AbstractApplicationContext context= new ClassPathXmlApplicationContext("com/testspring/config.xml");
//        Book book = context.getBean(Book.class);    	
//        System.out.println(book.getBookName());
//        
//        Order order = context.getBean(Order.class);
//        System.out.println(order.getBook().getBookAuthor() + "<br>");
        
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        
        Book book2 = applicationContext.getBean(Book.class);
        System.out.println(book2.getBookName());
        System.out.println(book2.getBookAuthor() + " bean name: "+book2.getBeanName());
        
        Order order2 = applicationContext.getBean(Order.class);
        System.out.println(order2.getBook().getBookAuthor()+ " bean name: "+order2.getBook().getBeanName());
    }
}
