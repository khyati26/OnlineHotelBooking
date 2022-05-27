package com.testspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        ApplicationContext context= new ClassPathXmlApplicationContext("config.xml");
        
        studentBean stud=(studentBean) context.getBean("student1");
	    System.out.println(stud);
	    studentBean stud2=(studentBean) context.getBean("student2");
	    System.out.println(stud2);
    }
}
