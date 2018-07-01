package com.starylwu.strategyandfactory;

import com.starylwu.strategyandfactory.factory.CalPriceAnnoFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StrategyandfactoryApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StrategyandfactoryApplication.class, args);
        CalPriceAnnoFactory.initContext(context);
    }
}
