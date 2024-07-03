package com.example.structural_pattern._7_bridge.before;

/**
 * 브릿지(Bridge) 패턴 : 추상적인 것과 구체적인 것을 분리하여 연결하는 패턴
 * ㄴ 독립적인 계층 구조 : 하나의 계층 구조 << 각기 나누었을 때
 * ㄴ 클라이언트는 추상적인 계층 구조 사용
 */
public class App {

    public static void main(String[] args) {
        Champion kda아리 = new KDA아리();
        kda아리.skillQ();
        kda아리.skillR();
    }
}
