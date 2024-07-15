package com.example.structural_pattern._12_proxy.before;

/**
 * Proxy : 대리, 대리인 의미 >> 클라이언트가 원래 사용하려는 객체를 직접 쓰는 것이 아닌 대리인을 거쳐서 사용
 * 프록시 패턴 : 특정 객체에 대한 접근을 제어하거나 기능을 추가할 수 있는 패턴. (초기화 지연, 접근 제어, 로깅, 캐싱 등 다양하게 응용 사용)
 *
 * ㄴ (+) : 접근 제어 (시큐리티를 적용하여 권한 로직), 많은 리소스가 필요한 인스턴스의 경우 초기화 지연 가능, 로깅 및 캐싱 가능 -> 성능적인 장점
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        GameService gameService = new GameService();
        gameService.startGame();
    }
}
