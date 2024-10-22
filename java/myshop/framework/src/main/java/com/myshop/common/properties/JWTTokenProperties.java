package com.myshop.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Cấu hình hết hạn token
 *
 * @author vantrang
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "myshop.jwt-setting")
public class JWTTokenProperties {


    /**
     * Thời gian hết hạn mặc định của token
     */
    private long tokenExpireTime = 60;
}