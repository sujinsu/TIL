package com.example.behavioral_patterns._14_command.after;

/**
 * java의 runnable이라는 인터페이스와 비슷 : 반환값 없고 실행하는 메서드 하나만 정의ㅏ
 */
public interface Command {

    void execute();

    void undo();

}
