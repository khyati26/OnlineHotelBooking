package com.testspring.v2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DBConfig {
	
	@Bean({"hardik","nandu"})
	@Primary
	@Qualifier("book4")
	public Book getBook() {
		return new Book();
	}
}
