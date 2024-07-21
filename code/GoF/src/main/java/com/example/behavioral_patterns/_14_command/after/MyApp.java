package com.example.behavioral_patterns._14_command.after;


import com.example.behavioral_patterns._14_command.before.Game;

/**
 * (+) : 인보커 코드는 변경되지 않음 / DB저장, logging, 다른 컴퓨터 서버 기능 실행 등 활용 가능
 * (-) : 코드의 복잡도 증가
 */
public class MyApp {

    private Command command;

    public MyApp(Command command) {
        this.command = command;
    }

    public void press() {
        command.execute();
    }

    public static void main(String[] args) {
        MyApp myApp = new MyApp(new GameStartCommand(new Game()));
    }
}
