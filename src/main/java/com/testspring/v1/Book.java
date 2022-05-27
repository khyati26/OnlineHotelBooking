package com.testspring.v1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Book implements InitializingBean, DisposableBean, ApplicationContextAware, BeanNameAware {

	private String bookName;
	private String bookAuthor;
	private String publisher;
	private double price;

	private ApplicationContext applicationContext;

	public void init() {
		System.out.println("Book:Custome init...");
	}

	public void customDestroy() {
		System.out.println("Book:Custome destory...");
	}

	public Book() {

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
		System.out.println("Book:Bean name " + name + "...");
	}
}
