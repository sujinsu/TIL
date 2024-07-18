package com.example.behavioral_patterns._13_chain_of_responsibilities.after;


import com.example.behavioral_patterns._13_chain_of_responsibilities.before.Request;


/**
 * 목적 : 클라이언트가 구체적인 핸들러 타입을 모르게끔, 클라이언트 코드는 변경되지 않게끔.
 *
 * (+) : 클라이언트 코드 수정하지 않고 핸들러 추가 가능(Open Closed), 각 핸들러마다 본인이 할 일만 가지고 있음(single responsibility)
 *   >> 체인을 원하는 다양한 형태로 구성 가능
 * (-) : 디버깅이 복잡해짐
 */
public class Client {

    private RequestHandler requestHandler;

    public Client(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public void doWork() {
        Request request = new Request("이번 놀이는 뽑기입니다.");
        requestHandler.handle(request);
    }

    public static void main(String[] args) {
        RequestHandler chain = new AuthRequestHandler(new LoggingRequestHandler(new PrintRequestHandler(null)));
        Client client = new Client(chain);
        client.doWork();
    }
}
