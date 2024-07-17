package com.example.behavioral_patterns._13_chain_of_responsibilities.after;


import com.example.behavioral_patterns._13_chain_of_responsibilities.before.Request;

/**
 * 다음 체인으로 이어질 필드가 필요 >> 인터페이스 말고 추상 클래스로 진행
 */
public abstract class RequestHandler {

    private RequestHandler nextHandler;

    public RequestHandler(RequestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handle(Request request) {
        if (nextHandler != null) { // 다음 핸들러 있으면 진행
            nextHandler.handle(request);
        }
    }
}
