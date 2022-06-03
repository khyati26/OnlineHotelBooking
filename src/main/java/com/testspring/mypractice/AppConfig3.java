package com.testspring.mypractice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
@Profile("dev")
public class AppConfig3 {
    Environment environment;
    @Bean("KesarMango")
    @Qualifier("kesarmango")
    public Mango getMangoInstance2() {
        return new Mango("yellow",100);
    }
    @Bean("GreemApple2")
    @Qualifier("greenapple2")
//    @Primary
    public Apple getAppleInstance2() {
        return new Apple("green",10);
    }

    @Bean("OrderFruits")
//    @Qualifier("orderfruits")
    public Order getOrderInstance2() {
//        Mango mango = new AppConfig3().getMangoInstance2();
        return new Order(500,10,getAppleInstance2(),getMangoInstance2());
    }

}
