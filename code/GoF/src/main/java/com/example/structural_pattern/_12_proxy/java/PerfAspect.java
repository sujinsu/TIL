package com.example.structural_pattern._12_proxy.java;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component // aspect도 bean 으로 등록되어야 하기 때문
public class PerfAspect {

    @Around("bean(gameService)") // 어디에 적용할지
    public void timestamp(ProceedingJoinPoint point) throws Throwable {
        long before = System.currentTimeMillis();
        point.proceed(); // point는  GameService의 method를 일컫음.
        System.out.println(System.currentTimeMillis() - before);
    }
}
