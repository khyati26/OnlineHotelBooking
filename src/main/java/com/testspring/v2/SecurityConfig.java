package com.testspring.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
	
	@Bean("book3")
	public Book getBook() {
		return new Book();
	}
	
}
