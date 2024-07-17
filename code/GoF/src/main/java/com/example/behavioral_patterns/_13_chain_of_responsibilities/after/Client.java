package com.example.behavioral_patterns._13_chain_of_responsibilities.after;


import com.example.behavioral_patterns._13_chain_of_responsibilities.before.Request;


/**
 * 목적 : 클라이언트가 구체적인 핸들러 타입을 모르게끔, 클라이언트 코드는 변경되지 않게끔.
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
