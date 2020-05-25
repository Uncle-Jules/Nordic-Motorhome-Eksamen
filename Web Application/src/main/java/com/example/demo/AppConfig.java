package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.example.*"})
@EnableAutoConfiguration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("${middleSeason}")
    private double middleSeasonPercent;

    @Value("${highSeason}")
    private double highSeasonPercent;
    @Value("${pickupDropoffTax}")
    private double pickupDropoffTax;

    public double getMiddleSeasonPercent() {
        return middleSeasonPercent;
    }

    public double getHighSeasonPercent() {
        return highSeasonPercent;
    }

    public double getPickupDropoffTax() {
        return pickupDropoffTax;
    }
}
