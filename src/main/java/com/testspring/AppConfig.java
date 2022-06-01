package com.testspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class AppConfig {

	
	@Bean
	@Qualifier("main1")
	public Book mybook() {
		Book book = new Book();
		book.setBookName("Annotation book");
		book.setBookAuthor("Anno auth");
		return book;		
	}
	
	@Bean
	@Primary
	@Qualifier("main2")
	public Book mybook2() {
		Book book = new Book();
		book.setBookName("Annotation book2");
		book.setBookAuthor("Anno auth2");
		return book;		
	}
	
	@Bean
	public Order myorder() {
		return new Order();
	}
}
