package com.testspring.mypractice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class AppConfig2 {

    @Bean("GreemApple")
    @Qualifier("greenapple")
    public Apple getAppleInstance2() {
        return new Apple("green",10);
    }

    @Bean("OrderFruits2")
    @Qualifier("orderfruits")
    @Primary
    public Order getOrderInstance2() {
        Mango mango = new AppConfig3().getMangoInstance2();
        return new Order(500,10,getAppleInstance2(),mango);
    }

}
