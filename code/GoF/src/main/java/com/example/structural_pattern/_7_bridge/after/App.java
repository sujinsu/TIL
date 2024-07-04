package com.example.structural_pattern._7_bridge.after;


import com.example.structural_pattern._7_bridge.before.Champion;

/**
 * 브릿지(Bridge) 패턴 : 추상적인 것과 구체적인 것을 분리하여 연결하는 패턴
 * ㄴ 독립적인 계층 구조 (single responsability) : 하나의 계층 구조 << 각기 나누었을 때
 * ㄴ 클라이언트는 추상적인 계층 구조 사용
 *
 * (-) : 계층 구조가 늘어나 복잡도가 증가할 수 있다
 */
public abstract class App implements Champion {

    public static void main(String[] args) {
        Champion kda아리 = new 아리(new KDA());
        kda아리.skillQ();
        kda아리.skillW();

        Champion poolParty아리 = new 아리(new PoolParty());
        poolParty아리.skillR();
        poolParty아리.skillW();
    }
}
