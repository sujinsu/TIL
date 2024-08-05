package com.example.behavioral_patterns._18_memento.after;

/**
 * Game.java : originator
 * ㄴ createMemento(): originator의 내부 정보 > Memento class로 추상화해서 전달
 * ㄴ restore(Memento()) : Memento 정보로 자신 복원
 *
 * Client.java : care taker (originator의 내부 정보를 저장하고 다시 복원 할 수 있는 외부 클래스)
 * ㄴ originator 디테일한 상태정보를 가지고 있는 것이 아닌, memento라는 타입으로 가지고 있음
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
