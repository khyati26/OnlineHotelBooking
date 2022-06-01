package com.testspring.v2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

public class SecurityConfig {

	@Bean("book3")
	@Qualifier(value = "getbook")
	public Book getBook() {
		return new Book();
	}

}
