package com.example.structural_pattern._9_decorator.java;

import org.springframework.beans.factory.xml.BeanDefinitionDecorator;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;

public class DecoratorInSpring {

    public static void main(String[] args) {
        // 빈 설정 데코레이터 (쓸 일이 거의 없음. 스프링 인프라 스트럭쳐.)
        BeanDefinitionDecorator decorator;

        // 웹플럭스 HTTP 요청 /응답 데코레이터
        // 서블릿 필터처럼 웹필터를 상속해서 하나 만들고 필터들을 거쳐가는 요청 > serverHttprequest
        ServerHttpRequestDecorator httpRequestDecorator;
        ServerHttpResponseDecorator httpResponseDecorator;
    }
}
