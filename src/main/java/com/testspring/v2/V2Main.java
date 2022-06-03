package com.testspring.v2;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.testspring.validation.Address;
import com.testspring.validation.AddressValidator;
import com.testspring.validation.Hotel;
import com.testspring.validation.HotelValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class V2Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.getEnvironment().setDefaultProfiles("dev");
//		applicationContext.getEnvironment().setActiveProfiles("prod");
		applicationContext.register(AppConfig.class);
		applicationContext.refresh();

		MessageSource messageSource = (MessageSource) applicationContext.getBean("messageSource");
		System.out.println(messageSource);
		Locale locale = new Locale("gj");
		System.out.println(messageSource.getMessage("hotel.name",new Object[]{"Ashirvad", "Restro"}, Locale.GERMAN));

		Hotel hotel = new Hotel();
		hotel.setId(-1);
//        hotel.setName("ABCDEF");
		hotel.setEmail("fdgdsgdg");
		hotel.setMobile(null);

		Address address1 = new Address();
		address1.setArea(null);
		address1.setCountry(null);
		address1.setState(null);
		hotel.setAddress(address1);

		BindingResult bindingResult = new BeanPropertyBindingResult(hotel,"hotel");
		org.springframework.validation.Validator springValidator = applicationContext.getBean(HotelValidator.class);
		springValidator.validate(hotel,bindingResult);
		if(bindingResult.hasErrors()){
			System.out.println("Error exist...");
			System.out.println(bindingResult.getFieldError().getField());
			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
//            List<FieldError> fieldErrorList = bindingResult.getFieldErrors("mobile");
			for(FieldError fieldError : fieldErrorList){
				messageSource.getMessage(fieldError.getCode(),fieldError.getArguments(), Locale.getDefault());
				System.out.println(fieldError.getField() + "=" +fieldError.getCode());
				System.out.println(fieldError.getDefaultMessage());
				System.out.println("=========================");
			}

			System.out.println(bindingResult.getErrorCount());
			System.out.println(bindingResult.getFieldErrorCount());
			System.out.println(bindingResult.getGlobalErrorCount());

			List<ObjectError> globalErr = bindingResult.getGlobalErrors();
			for(ObjectError objectError : globalErr){
				System.out.println(objectError.getCode() + "=" + objectError.getDefaultMessage());
			}
		}


//		Book book = applicationContext.getBean(Book.class);
//		System.out.println(book);
//		System.out.println(book.getEnvironment().getProperty("jdbc.url"));
//		System.out.println(book.getEnvironment().getProperty("email.host"));
//		System.out.println(book.getEnvironment().containsProperty("TEST_VAR"));
//		System.out.println("==========================");
//		for (Iterator it = ((AbstractEnvironment) book.getEnvironment()).getPropertySources().iterator(); it
//				.hasNext();) {
//			PropertySource propertySource = (PropertySource) it.next();
//			if (propertySource instanceof MapPropertySource) {
//				System.out.println(((MapPropertySource) propertySource).getSource());
//				System.out.println("==========================");
//			}
//		}

//		Order order = applicationContext.getBean(Order.class);
//		System.out.println(order.getBook().getBookName());
//		Transaction transaction = applicationContext.getBean(Transaction.class);
//		System.out.println(transaction.getDbUrl());
		applicationContext.registerShutdownHook();

	}

}
