package com.example.behavioral_patterns._14_command.java;


import com.example.behavioral_patterns._14_command.before.Game;
import com.example.behavioral_patterns._14_command.before.Light;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 각각의 커맨드를 executorService 가 실행시켜 줌
 */
public class CommandInJava {

    public static void main(String[] args) {
        Light light = new Light();
        Game game = new Game();
        // Executors : threadpool 만들어주는 factory같은 클래스
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // 메서드 레퍼런스 (Java8 도입)
        // 4개의 thread를 가진 threadpool을 만들어 준 것 >> 비동기적으로 오퍼레이션 실행 기능 제공
        executorService.submit(light::on);

        // 익명 내부 클래스 : 기존 Java 방식
        // 아래와 같이 runnable이란 구현체를 준 것과 비슷한 효과 >> runnable 타입 인스턴스에 해야할 일 모두 들어있다 >> 이 자체가 커맨드, 커맨트 패턴 적용
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                light.on();
            }
        });
        // 람다 표현식 : 함수형 프로그래밍 선호시 사용
        executorService.submit(()-> light.on());

        executorService.submit(game::start);
        executorService.submit(game::end);
        executorService.submit(light::off);
        executorService.shutdown();
    }
}
