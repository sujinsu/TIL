package com.example.behavioral_patterns._18_memento.before;

/**
 * memento pattern : 객체 내부의 상태를 외부에 저장하고, 저장된 정보 복원을 위해 사용
 * ㄴ캡슐화 (인캡슐레이션) 를 지키는 상황에서 데이터 복원 : 객체 내부의 상태를 밖으로 디테일하게 공개하지 않음(필드 등)
 */
public class Client {

    public static void main(String[] args) {
        Game game = new Game();
        game.setRedTeamScore(10);
        game.setBlueTeamScore(20);

        int blueTeamScore = game.getBlueTeamScore();
        int redTeamScore = game.getRedTeamScore();

        Game restoredGame = new Game();
        restoredGame.setBlueTeamScore(blueTeamScore);
        restoredGame.setRedTeamScore(redTeamScore);
    }
}
