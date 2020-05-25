package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.example.*"})
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${middleSeason}")
    private static double middleSeasonPercent;

    public static double getMiddleSeasonPercent() {
        return middleSeasonPercent;
    }
}
