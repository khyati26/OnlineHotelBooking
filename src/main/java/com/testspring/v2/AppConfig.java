package com.testspring.v2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com.testspring.v2")
@Import({ SecurityConfig.class, DBConfig.class })
public class AppConfig {

	@Bean
	@Qualifier("book2")
	public Book book2() {
		return new Book();
	}

}
