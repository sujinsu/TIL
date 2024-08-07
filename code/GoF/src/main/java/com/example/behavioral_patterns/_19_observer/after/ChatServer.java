package com.example.behavioral_patterns._19_observer.after;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Subject별로 Observer 등록 및 해지
 */
public class ChatServer {
    // 주제 별로 Subscriber
    private Map<String, List<Subscriber>> subscribers = new HashMap<>();


    /**
     * 특정 subject 구독, 구독자 등록
     * @param subject
     * @param subscriber
     */
    public void register(String subject, Subscriber subscriber) {
        if (this.subscribers.containsKey(subject)) {
            this.subscribers.get(subject).add(subscriber);
        } else {
            List<Subscriber> list = new ArrayList<>();
            list.add(subscriber);
            this.subscribers.put(subject, list); // List.of(subsriber) : 이렇게 하면 안 됨. immutable 한 객체 반환 !
        }
    }

    /**
     * 특정 subject 해지
     * @param subject
     * @param subscriber
     */
    public void unregister(String subject, Subscriber subscriber) {
        if (this.subscribers.containsKey(subject)) {
            this.subscribers.get(subject).remove(subscriber);
        }
    }

    /**
     * 상태 변경 시 (특정 유저가 특정 주제로 특정 메세지를 보내면), 동작
     * @param user
     * @param subject
     * @param message
     */
    public void sendMessage(User user, String subject, String message) {
        if (this.subscribers.containsKey(subject)) {
            String userMessage = user.getName() + ": " + message;
            this.subscribers.get(subject).forEach(s -> s.handleMessage(userMessage));
        }
    }

}
