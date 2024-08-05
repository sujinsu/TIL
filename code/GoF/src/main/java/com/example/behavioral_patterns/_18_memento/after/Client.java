package com.example.behavioral_patterns._18_memento.after;

/**
 * Game.java : originator
 * ㄴ createMemento(): originator의 내부 정보 > Memento class로 추상화해서 전달
 * ㄴ restore(Memento()) : Memento 정보로 자신 복원
 *
 * Client.java : care taker (originator의 내부 정보를 저장하고 다시 복원 할 수 있는 외부 클래스)
 * ㄴ originator 디테일한 상태정보를 가지고 있는 것이 아닌, memento라는 타입으로 가지고 있음
 *
 * (+) : 객체 내부의 상태를 노출시키지 않고 스냅샷 생성 가능 >> 유연하게 만듦 (Open Closed principle)
 *
 * (-) : memento가 많은 정보를 저장하고, 자주 생성한다면 메모리 사용량이 많아짐. >> 오래된 메멘토 정리해주는 역할을 케어테이커가 해야할 수도
 */
public class Client {

    public static void main(String[] args) {
        Game game = new Game();
        game.setBlueTeamScore(10);
        game.setRedTeamScore(20);

        GameSave save = game.save();

        game.setBlueTeamScore(12);
        game.setRedTeamScore(22);

        game.restore(save);

        System.out.println(game.getBlueTeamScore());
        System.out.println(game.getRedTeamScore());
    }
}
