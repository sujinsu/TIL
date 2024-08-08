package com.example.behavioral_patterns._19_observer.after;

/**
 * 다수의 객체가 특정 객체 상태 변화를 감지하고 알림을 받는 패턴
 *
 * (+) : 상태를 변경하는 객체(pub) 와 변경을 감지하느 객체(sub) 의 관계를 느슨하게 유지 가능
 * (+) : subject (주제) 의 상태 변경을 주기적으로 조회하지 않고 자동으로 감지할 수 있다
 * (+) : 런타임에 옵저버 추가 및 제거 가능
 *
 * (-) : 복잡도 증가 / 다수의 observer 등록 후 해지하지 않으면 memory leak 발생 (예시에서도 map에 담겨있으면 GC X)
 */
public class Client {

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("keesun");
        User user2 = new User("whiteship");

        chatServer.register("오징어게임", user1);
        chatServer.register("오징어게임", user2);

        chatServer.register("디자인패턴", user1);

        chatServer.sendMessage(user1, "오징어게임", "아.. 이름이 기억났어.. 일남이야.. 오일남");
        chatServer.sendMessage(user2, "디자인패턴", "옵저버 패턴으로 만든 채팅");

        chatServer.unregister("디자인패턴", user2);

        chatServer.sendMessage(user2, "디자인패턴", "옵저버 패턴 장, 단점 보는 중");
    }
}
