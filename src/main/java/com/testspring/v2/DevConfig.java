package com.testspring.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

public class DevConfig {

	@Bean
	public Book book2() {
		return new Book("DEV", "DEV", "DEV");
	}

}
