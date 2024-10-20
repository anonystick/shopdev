package com.myshop.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

/**
 * Bean Bảo Mật
 */
@Configuration
public class SecurityBean {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Định nghĩa cấu hình chéo miền
     *
     * @return bean
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        // Khởi tạo một đối tượng UrlBasedCorsConfigurationSource, một lớp được sử dụng để cấu hình CORS dựa trên URL
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Khởi tạo một đối tượng CorsConfiguration, một lớp được sử dụng để xác định các thông số cấu hình CORS
        CorsConfiguration config = new CorsConfiguration();
        // Cho phép trình duyệt gửi cookie cùng với yêu cầu chéo miền
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Collections.singletonList(CorsConfiguration.ALL));
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedMethod(CorsConfiguration.ALL);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}