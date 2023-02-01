package com.example.websocketchatting.config.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().and()
                .csrf().disable()
                .cors()
                .and()
                .headers().frameOptions().sameOrigin()// SockJS는 기본적으로 HTML iframe 요소를 통한 전송을 허용하지 않도록 설정되는데 해당 내용을 해제한다.
                .and()
                .authorizeRequests()
                //.antMatchers("/chat/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .usernameParameter("userName")
                .passwordParameter("password")
                .defaultSuccessUrl("/chat/rooms")
                .and()
                .logout().logoutUrl("/logout")
                .and()
                .build();
    }
}
