package com.testspring.mypractice;

import org.springframework.context.annotation.*;

@Configuration
//@ComponentScan(basePackages = "com.testspring.beanlifecycle")
@Import({AppConfig2.class, AppConfig3.class})
//@PropertySource("classpath:jdbc.properties")
@PropertySources({
        @PropertySource("classpath:jdbc.properties"),
        @PropertySource("classpath:email.properties")
})
@Profile("dev")
public class AppConfig {

    @Bean
    @Primary
    public Order getOrderInstance() {
        return new Order();
    }

    @Bean
    @Primary
    public Apple getAppleInctance() {
        return new Apple();
    }

    @Bean
    @Primary
    public Mango getMangoInctance() {
        return new Mango();
    }

}
