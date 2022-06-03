package com.testspring.mypractice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Iterator;
import java.util.Set;

public class mainClass {
    public static void main(String[] args) {
//        AbstractApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(AppConfig.class);

        AnnotationConfigApplicationContext applicationContext1 = new AnnotationConfigApplicationContext();
        applicationContext1.getEnvironment().setActiveProfiles("dev");
        applicationContext1.register(AppConfig.class, AppConfig2.class, AppConfig3.class);
//        applicationContext1.scan("com.testspring.beanlifecycle");
        applicationContext1.refresh();

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        Apple apple = applicationContext1.getBean(Apple.class);
        System.out.println(apple.toString());
        apple.getBeanName();

        System.out.println("++++++++++++++++++++++Error Message+++++++++++++++++++++++++++++");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Apple>> constraintViolations = validator.validate(apple);
        for (ConstraintViolation<Apple> violation: constraintViolations ) {
            System.out.println(violation.getMessage());
        }

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        Mango mango = applicationContext1.getBean(Mango.class);
        System.out.println(mango.toString());
        mango.getBeanName();


        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        Order order = applicationContext1.getBean(Order.class);
        System.out.println(order.toString());
        order.getBeanName();


        System.out.println("==========================");
        for (Iterator it = ((AbstractEnvironment) apple.getEnvironment()).getPropertySources().iterator();
             it.hasNext(); ) {
            PropertySource propertySource = (PropertySource) it.next();
            if (propertySource instanceof MapPropertySource) {
                System.out.println(((MapPropertySource) propertySource).getSource());
                System.out.println("==========================");
            }
        }

        applicationContext1.registerShutdownHook();

//        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/testspring/beanlifecycle/lifecycleconfig.xml");
//        Order order = applicationContext.getBean(Order.class);
//        System.out.println(order.toString() );
//        order.getBeanName();
//
//        applicationContext.registerShutdownHook();

    }
}
