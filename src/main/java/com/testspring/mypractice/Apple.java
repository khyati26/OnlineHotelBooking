package com.testspring.mypractice;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Apple implements BeanNameAware {
    @Min(10)
    private int price;

//    @Value("${jdbc.driverClassName}")
    @NotNull
    @Size(min = 2, max = 14)
    private String colour;

    private String beanName;

    public Apple() {
//        this.colour = "red";
//        this.price = 45;
    }
    public Apple(String colour, int price) {
        this.colour = colour;
        this.price = price;
    }

    @Autowired
    private Environment environment;
    public Environment getEnvironment() {
        return environment;
    }
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getColour() {
        return colour;
    }
//    @Autowired
//    public void setColour(@Value("${jdbc.url}") String colour) {
//        this.colour = colour;
//    }
    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "price=" + price +
                ", colour='" + colour + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }


//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("apple init Bean name : " + beanName);
//    }
//
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("apple destroy method Bean name : " + beanName);
//    }

    public void getBeanName() {
        System.out.println("Bean name: " + beanName);
    }
    @Override
    public void setBeanName(String s) {
        beanName = s;
    }
}
