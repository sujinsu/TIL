package com.example.behavioral_patterns._19_observer.java;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * ApplicationRunner : springboot 서버 띄울 시 자동으로 실행되는 코드
 */
@Component
public class MyRunner implements ApplicationRunner {

    // ApplicationEventPublisher : Application context (event publisher)가 구현하는 구체적인 형태
    private ApplicationEventPublisher publisher;

    public MyRunner(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    /**
     * 애플리케이션 실행 > MyRunner 실행 > 퍼블리셔를 통해 My Event라는 이벤트 발생
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        publisher.publishEvent(new MyEvent("hello spring event"));
    }
}
