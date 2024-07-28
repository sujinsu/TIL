package com.example.behavioral_patterns._15_interpreter.after;

import java.util.Map;

public interface PostfixExpression {

    int interpret(Map<Character, Integer> context);

    static PostfixExpression plus(PostfixExpression left, PostfixExpression right){
        return new PostfixExpression() {
            @Override
            public int interpret(Map<Character, Integer> context) {
                return left.interpret(context) + right.interpret(context);
            }
        };
        // 람다로 표현 가능
//        return context -> left.interpret(context) + right.interpret(context);
    }

    static PostfixExpression minus(PostfixExpression left, PostfixExpression right){
        // 람다로 표현 가능
        return context -> left.interpret(context) - right.interpret(context);
    }

    static PostfixExpression variable(Character c){
        return context -> context.get(c);
        // 람다로 표현 가능
//        return context -> left.interpret(context) + right.interpret(context);
    }
}
