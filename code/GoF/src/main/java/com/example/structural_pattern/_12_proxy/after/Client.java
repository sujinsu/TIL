package com.example.structural_pattern._12_proxy.after;

/**
 * 프록시 패턴 : 특정 객체에 대한 접근을 제어하거나 기능을 추가할 수 있는 패턴
 *
 * (+) : 기존 코드를 변경하지 않고 새로운 기능을 추가 (Open Closed principle)/기존 코드가 해야하는 일만 유지 가능/ 기능 추가 및 초기화 지연 등 활용 가능
 * (-) : 코드의 복잡도
 */
public class Client {

    public static void main(String[] args) {
        GameService gameService = new GameServiceProxy();
        gameService.startGame();
    }
}
