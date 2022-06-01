package com.testspring;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Book implements BeanNameAware {
	
	private String beanName;
	
	private String bookName;
	private String bookAuthor;
	private String publisher;
	private double price;

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String name) {
		this.beanName = name;
	}

//	@Primary
	public String getBookName() {
		return bookName;
	}

	public void setBookName(@Value("${jdbc.url}") String bookName) {
		this.bookName = bookName;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
