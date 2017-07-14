package com.onikitich;

import com.onikitich.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SupermarketCheckoutApp {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        SupermarketCheckoutMachine supermarketCheckoutMachine = ctx.getBean(SupermarketCheckoutMachine.class);
        supermarketCheckoutMachine.start();
    }
}
