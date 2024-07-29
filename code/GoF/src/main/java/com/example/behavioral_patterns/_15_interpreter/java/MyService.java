package com.example.behavioral_patterns._15_interpreter.java;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class MyService implements ApplicationRunner {

    // {}안에  SpEL 을 넣으면 Expression Language parser, expression 으로 값을 구해서 넣어줌
    @Value("#{2 + 5}")
    private String value;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(value);
    }
}
