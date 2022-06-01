package com.testspring.v2;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

public class Book implements InitializingBean, DisposableBean, ApplicationContextAware, BeanNameAware {

	private String beanName;
	private String bookName;
	private String bookAuthor;
	private String publisher;
	private double price;

	@Autowired
	private Environment environment;

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void init() {
		System.out.println("Book:Custome init...");
	}

	@PreDestroy
	public void customDestroy() {
		System.out.println("Book:Custome destory...");
	}

	public Book() {
		this.bookName = "Default...";
		this.bookAuthor = "Default...";
		this.publisher = "Default...";
	}

	public Book(String bookName, String bookAuth, String pubshlisher) {
		this.bookName = bookName;
		this.bookAuthor = bookAuth;
		this.publisher = pubshlisher;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getBeanName() {
		return beanName;
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("Book:In afterPropertiesSet...");
	}

	public void destroy() throws Exception {
		System.out.println("Book:In destroy...");
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("Book:Setting application context...");
		this.applicationContext = applicationContext;
	}

	public void setBeanName(String name) {
		this.beanName = name;
		System.out.println("Book:Bean name " + name + "...");
	}

	@Override
	public String toString() {
		return "Book [beanName=" + beanName + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", publisher="
				+ publisher + ", price=" + price + "]";
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
