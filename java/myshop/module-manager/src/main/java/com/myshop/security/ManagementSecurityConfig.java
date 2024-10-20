package com.myshop.security;

import com.myshop.cache.Cache;
import com.myshop.common.properties.IgnoredUrlsProperties;
import com.myshop.common.security.InvalidAuthenticationEntryPoint;
import com.myshop.modules.permission.service.MenuService;
import com.myshop.modules.system.token.ManagerTokenGenerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * Spring Security Core Configuration Class Manager Security Configuration Center
 *
 * @author vantrang
 */
@Slf4j
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@Configuration
public class ManagementSecurityConfig {

    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Autowired
    private InvalidAuthenticationEntryPoint invalidAuthenticationEntryPoint;

    /**
     * Authentication failure handling class Bean
     */
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * Insufficient permissions for processor bean
     */
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private MenuService menuService;

    @Autowired
    private ManagerTokenGenerate managerTokenGenerate;

    @Autowired
    private Cache<String> cache;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();

        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authorize -> {
                    for (String url : ignoredUrlsProperties.getUrls()) {
                        authorize.requestMatchers(url).permitAll();
                    }
                    authorize.anyRequest().authenticated();
                })
                //Enable cross-domain
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                //CSRF is disabled because Session is not used
                .csrf(AbstractHttpConfigurer::disable)
                //Based on token mechanism, so no Session is required
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //A bunch of custom Spring Security handlers
                .exceptionHandling(c -> c.authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
//                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
                .headers(c -> c.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        // Add custom JWT authentication filter
        http.addFilterBefore(new ManagementAuthenticationFilter(authenticationManager, menuService, managerTokenGenerate, cache), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}