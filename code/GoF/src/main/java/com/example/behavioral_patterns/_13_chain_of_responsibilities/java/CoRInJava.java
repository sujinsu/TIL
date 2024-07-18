package com.example.behavioral_patterns._13_chain_of_responsibilities.java;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 요청을 처리하는 과정 중 필터 혹은 인터셉터에서 chain of responsibility 확인 가능
 */
public class CoRInJava {

    /**
     * 서블릿 필터 : 특정한 서블릿 url 패턴, 서블릿 컨테이너로 들어오는 요청 패턴의 필터링을 하는 용도
     * ㄴ 이 기능 사용 >> 실제 그 요청 처리하는 서블릿 도착 이전에 필터들을 거치면서 가게 됨
     * @param args
     */
    public static void main(String[] args) {
        Filter filter = new Filter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                // TODO 전처리
                chain.doFilter(request, response);
                // TODO 후처리
            }
        };
    }
}
