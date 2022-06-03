package com.testspring.mypractice;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Order implements BeanNameAware {
    private int qty;

    private int amount;

    private String beanName;

    @Autowired
    @Qualifier("greenapple2")
    private Apple apple;

    @Resource
    @Qualifier("kesarmango")
    private Mango mango;

    public Apple getApple() {
        return apple;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public Mango getMango() {
        return mango;
    }

    public void setMango(Mango mango) {
        this.mango = mango;
    }

    public Order() {
        this.amount = 100;
        this.qty = 10;
        this.apple = getApple();
        this.mango = getMango();
    }
    public Order(int amount,int qty,Apple apple,Mango mango){
        this.amount=amount;
        this.qty=qty;
        this.apple=apple;
        this.mango=mango;
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("init from Order Bean name : " + beanName);
//    }
//
//    @PreDestroy
//    public void cleanup() {
//        System.out.println("cleanup from Order Bean name : " + beanName);
//    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "qty=" + qty +
                ", amount=" + amount +
                ", beanName='" + beanName + '\'' +
                ", apple=" + apple + '\'' +
                ", mango=" + mango +
                '}';
    }

    public void getBeanName() {
        System.out.println("Bean name: " + beanName);
    }

    @Override
    public void setBeanName(String s) {
        beanName = s;
    }
}
