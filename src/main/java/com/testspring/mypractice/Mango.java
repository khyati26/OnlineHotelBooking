package com.testspring.mypractice;

import org.springframework.beans.factory.BeanNameAware;

public class Mango implements BeanNameAware  {
    private int price;

//    @Value("${jdbc.url}")
    private String colour;

    private String beanName;

    public Mango() {
        this.colour = "red";
        this.price = 45;
    }

    public Mango(String colour, int price) {
        this.colour = colour;
        this.price = price;
    }

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
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

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void getBeanName() {
        System.out.println("Bean name: " + beanName);
    }

    @Override
    public String toString() {
        return "Mango{" +
                "price=" + price +
                ", colour='" + colour + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

//    @Override
//    public void start() {
//        System.out.println("mango start Bean name : " + beanName);
//    }
//
//    @Override
//    public void stop() {
//        System.out.println("mango stop Bean name : " + beanName);
//
//    }
//
//    @Override
//    public boolean isRunning() {
//        System.out.println("mango isrunning Bean name : " + beanName);
//        return false;
//    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("mango inti method Bean name : " + beanName);
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("mango cleanup method  Bean name : " + beanName);
//    }
}
