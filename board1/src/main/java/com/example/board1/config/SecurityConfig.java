package com.example.board1.config;

import com.example.board1.service.Impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity //웹보안 활성화를위한 annotation
public class SecurityConfig {

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web ->  web.ignoring().requestMatchers
//                (PathRequest.toStaticResources().atCommonLocations()));
//    }
    private final UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                /* 3000포트 막아봤는데 잘 돌아다녀짐, 서버포트를 막는듯. 서버포트 막으면 리액트에서
                405 오류 나오는데 이거 처림 해야함. login으로 get으로 가는데 post로 받고 있었음
                 */
                /*
                    권한 줘야함 ..   user 이러게 주던지
                 */
                .authorizeHttpRequests() // 시작한다 ~
                .antMatchers("/","login").permitAll()
//                .antMatchers("/list/**").authenticated()//해당하는 요청을 보내면 막는것인가... 3000포트 자체를 막아준다는 것인가...
//                .antMatchers("/list/**").hasRole("USER")
                .and()
                .formLogin()// 로그인 형태 지원 메소드
                .loginPage("http://localhost:3000/login")
                .loginProcessingUrl("/login")// 로그인 하지 않았을 경우 매개변수
                .defaultSuccessUrl("http://localhost:3000/", true)// 로그인 되었을 때 url
//                .and()
//                .exceptionHandling().accessDeniedPage("/list")
                .and()
                .logout()
                // URLredirect
                .logoutUrl("login")
                .and()
                .csrf().disable().userDetailsService(userService)
                .build();
    }
}