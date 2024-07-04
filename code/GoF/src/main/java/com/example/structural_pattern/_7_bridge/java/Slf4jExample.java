package com.example.structural_pattern._7_bridge.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 로깅퍼사드 api : 추상화된 계층 구조
 * 로깅 퍼사드가 감싸고 있는 실질적인 로거 : 구현체
 * 현재 예제는 다 api, 로깅 퍼사드를 통해 로거 사용 중 < 관점에 따라 .. 다른 패턴일 수 있지만
 * ㄴ 새로운 로거 만든다고 해도 로거 팩토리 인터페이스 같은건 바뀌지 않음
 */
public class Slf4jExample {

    private static Logger logger = LoggerFactory.getLogger(Slf4jExample.class);

    public static void main(String[] args) {
        logger.info("hello logger");
    }
}
