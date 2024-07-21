package com.example.behavioral_patterns._14_command.before;

/**
 * command pattern : 요청을 캡슐화하여 호출자(인보커)와 수신자(리시버)를 분리하는 패턴
 * ㄴ 요청을 처리하는 방법이 바뀌더라도 호출자의 코드는 변경되지 않는다
 * >> 인보커는 매우 추상화ㅓ되어있는 command라는 인터페이스에 execute라는 메서드만 호출 & 모든 정보는 커맨드 안에 구성
 */
public class MyApp {

    private Game game;

    public MyApp(Game game) {
        this.game = game;
    }

    public void press() {
        game.start();
    }

    public static void main(String[] args) {
        Button button = new Button(new Light());
        button.press();
        button.press();
        button.press();
        button.press();
    }
}
