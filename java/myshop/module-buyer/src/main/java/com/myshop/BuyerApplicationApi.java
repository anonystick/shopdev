package com.myshop;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Buyer API
 *
 * @author vantrang
 * @since 2024/10/15
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
public class BuyerApplicationApi {
    public static void main(String[] args) {
        SpringApplication.run(BuyerApplicationApi.class, args);
    }

}