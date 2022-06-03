package com.testspring.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;

import java.util.Locale;

@Configuration
@ComponentScan(basePackages = {"com.testspring.v2","com.testspring.validation"})
//@PropertySource("classpath:jdbc.properties")
//@PropertySource("classpath:email.properties")
@PropertySources({ @PropertySource("classpath:jdbc.properties"), @PropertySource("classpath:email.properties") })
public class AppConfig {

	@Autowired
	private Environment environment;


	@Bean
	public MessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	@Profile("dev")
	public Book book2() {
		System.out.println(environment.getProperty("jdbc.url"));
		System.out.println(environment.getProperty("email.host"));
		return new Book("DEV", "DEV", "DEV");
	}

	@Bean
	@Profile("prod")
	public Book book21() {
		return new Book("Prod", "Prod", "Prod");
	}

}
