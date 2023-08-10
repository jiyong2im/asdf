package com.example.board1.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//
@Configuration
@EnableWebSecurity //웹보안 활성화를위한 annotation
public class SecurityConfig {

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web ->  web.ignoring().requestMatchers
//                (PathRequest.toStaticResources().atCommonLocations()));
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests() // 시작한다 ~
                .antMatchers("/user/**").authenticated()// URI 인가한다
//                .antMatchers("/admin/**").access() //access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()// 로그인 형태 지원 메소드
                .loginPage("/login")// 로그인 하지 않았을 경우 매개변수 URL redirect
                .loginProcessingUrl("/loginProc")
                .defaultSuccessUrl("/list")// 로그인 되었을 때 url
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .and()
                .csrf().disable()
                .build();

    }
}