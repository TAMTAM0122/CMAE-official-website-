package com.cmae.chairman.config;

import com.cmae.chairman.service.impl.TokenServiceImpl;
import com.cmae.chairman.tool.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Autowired
    private TokenServiceImpl tokenServiceImpl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilter(corsFilter())
                .csrf(csrf -> csrf.disable())  // 使用新的API禁用CSRF
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/User/**",
                                "/Case/**",
                                "/Tool/**",
                                "/News/**",
                                "/Honor/**",
                                "/Job/**").permitAll()  // 允许公共路径访问
                        .anyRequest().authenticated()  // 其他路径需要认证
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);  // 添加JWT验证过滤器，在UsernamePasswordAuthenticationFilter之前

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        // 将 tokenServiceImpl 通过构造函数传递给 JwtAuthenticationFilter
        return new JwtAuthenticationFilter(tokenServiceImpl);
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080","http://localhost"));  // 允许的前端地址
        configuration.addAllowedMethod("*");  // 允许所有请求方法
        configuration.addAllowedHeader("*");  // 允许所有请求头
        configuration.setAllowCredentials(true);  // 允许发送凭证（如Cookies）

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // 应用到所有路径
        return source;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // 使用 BCrypt 进行密码加密
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}