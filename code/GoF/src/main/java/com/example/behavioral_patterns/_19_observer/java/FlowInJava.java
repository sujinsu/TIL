package com.example.behavioral_patterns._19_observer.java;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * Flow에서 알아야 할 세 가지 : publisher, subscriber, subscribe
 *
 * 비동기에 대해 더 알고 싶다면 >> Flow API, Rx JAVA, Reactor 등  reactive stream
 */
public class FlowInJava {

    public static void main(String[] args) throws InterruptedException {
        // Flow.Publisher<String> publisher = new Flow.Publisher <String>() {} ~~ >> 동기

        Flow.Publisher<String> publisher = new SubmissionPublisher<>(); // 비동기

        Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("sub!");
                this.subscription = subscription;
                this.subscription.request(1); // publisher가 알아서 onNext 호출
            }

            @Override
            public void onNext(String item) {
                System.out.println("onNext called");
                System.out.println(Thread.currentThread().getName());
                System.out.println(item);
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onComplete() {
                System.out.println("completed");
            }
        };

        publisher.subscribe(subscriber);

        ((SubmissionPublisher)publisher).submit("hello java");

        System.out.println("이게 먼저 출력될 수도 있습니다."); // subscriber가 처리하기 전에 그냥 진행 됨.
    }
}
