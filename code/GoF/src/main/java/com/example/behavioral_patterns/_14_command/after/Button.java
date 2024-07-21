package com.example.behavioral_patterns._14_command.after;


import com.example.behavioral_patterns._14_command.before.Game;
import com.example.behavioral_patterns._14_command.before.Light;

import java.util.Stack;

/**
 * 인보커 (invoker)
 */
public class Button {

    private Stack<Command> commands = new Stack<>(); // undo 실행 시 최근 실행 기능부터 반대 기능 실행

    public void press(Command command) {
        command.execute();
        commands.push(command);
    }

    public void undo() {
        if (!commands.isEmpty()) {
            Command command = commands.pop();
            command.undo();
        }
    }

    public static void main(String[] args) {
        Button button = new Button();
        button.press(new GameStartCommand(new Game()));
        button.press(new LightOnCommand(new Light()));
        button.undo();
        button.undo();
    }

}
