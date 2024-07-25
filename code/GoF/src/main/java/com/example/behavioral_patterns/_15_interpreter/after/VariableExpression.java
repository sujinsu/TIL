package com.example.behavioral_patterns._15_interpreter.after;

import java.util.Map;

/**
 * 터미널 익스프레션 (ex. x,y,z)
 */
public class VariableExpression implements PostfixExpression {

    private Character character;

    public VariableExpression(Character character) {
        this.character = character;
    }

    /**
     * 변수를 받고 해당 변수에 맞는 integer 값 반환
     * @param context
     * @return
     */
    @Override
    public int interpret(Map<Character, Integer> context) {
        return context.get(this.character);
    }
}
