package com.example.behavioral_patterns._18_memento.before;

/**
 * memento pattern : 캡슐화를 유지하면서 내부 상태를 외부에 저장하는 방법, 외부에 저장했다가 해당 상태로 다시 복구 가능
 * ㄴ캡슐화 (인캡슐레이션) 를 지키는 상황에서 데이터 복원 : 객체 내부의 상태를 밖으로 디테일하게 공개하지 않음(필드 등)
 */
public class Client {

    public static void main(String[] args) {
        Game game = new Game();
        game.setRedTeamScore(10);
        game.setBlueTeamScore(20);

        // 현재 client 가 컬럼을 알아야 함. Game 정보 바뀔때마다 client 코드도 변경됨.
        int blueTeamScore = game.getBlueTeamScore();
        int redTeamScore = game.getRedTeamScore();

        Game restoredGame = new Game();
        restoredGame.setBlueTeamScore(blueTeamScore);
        restoredGame.setRedTeamScore(redTeamScore);
    }
}
