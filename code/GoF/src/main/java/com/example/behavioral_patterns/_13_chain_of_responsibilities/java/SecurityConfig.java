package com.example.behavioral_patterns._13_chain_of_responsibilities.java;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 스프링 예제 >> http 모든 요청 받겠다
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll().and(); // 필터를 전 후 추가 가능
        // 스프링 시큐리티는 아주 거대한 스프링 시큐리티 필터들의 체인 목록
    }

}
