package com.example.behavioral_patterns._13_chain_of_responsibilities.before;

/**
 * chain of responsibilities : 책임들의 연쇄 (책임 : 단일 책임 원칙의 책임 - 클래스의 변경 이유는 오직 한 가지 이유 때문이어야 함)
 */
public class Client {

    public static void main(String[] args) {
        Request request = new Request("무궁화 꽃이 피었습니다.");
        RequestHandler requestHandler = new LoggingRequestHandler(); // 로깅 핸들러에 인증 부분도 넣으면 단일 책임 원칙 위배
        // 사용자가 logging or auth 등 사용해야 하는 핸들러를 직접 알아야 한다는 문제 !
        requestHandler.handler(request);
    }
}
