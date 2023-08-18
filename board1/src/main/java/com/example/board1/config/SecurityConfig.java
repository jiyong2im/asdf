package com.example.board1.config;

import com.example.board1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity //웹보안 활성화를위한 annotation
public class SecurityConfig {

    private final UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests() // 시작한다 ~
                .antMatchers("/**").permitAll()
//                .antMatchers("/sss/**").hasRole("USER")
                .and()
                .formLogin()// 로그인 형태 지원 메소드
                .loginPage("http://localhost:3000/login")
                .loginProcessingUrl("/login")// 로그인 하지 않았을 경우 매개변수
                .defaultSuccessUrl("http://localhost:3000/", true)// 로그인 되었을 때 url
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                .csrf().disable()
                .userDetailsService(userService)
                .build();
    }
}


//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web ->  web.ignoring().requestMatchers
//                (PathRequest.toStaticResources().atCommonLocations()));
//    }
//                .logoutSuccessUrl("/list")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 여기서 로그아웃 처러ㅣ url 이쪽으로 부르면 됨
//                .logoutUrl("/")

/*
//                .antMatchers("/list/**").authenticated()//해당하는 요청을 보내면 막는것인가... 3000포트 자체를 막아준다는 것인가...



//                .failureUrl("")
//                .and()
//                .exceptionHandling().accessDeniedPage("/list")


//                // URLredirect

 */
