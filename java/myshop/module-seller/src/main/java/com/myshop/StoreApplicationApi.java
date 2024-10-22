package com.myshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Store API
 *
 * @author vantrang
 * @since 2024/10/15
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
public class StoreApplicationApi {
    public static void main(String[] args) {
        SpringApplication.run(StoreApplicationApi.class, args);
    }

}