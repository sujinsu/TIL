package com.example.behavioral_patterns._15_interpreter.after;

import java.util.Map;

/**
 * 특정 expression자체를 문법으로 쓰고 싶다. 파서를 이용해서 트리 구조로 만들어서 사용
 */
public class App {

    public static void main(String[] args) {
        PostfixExpression expression = PostfixParser.parse("xyz+-a+");
        // Map : context 정보
        int result = expression.interpret(Map.of('x', 1, 'y', 2, 'z', 3, 'a', 4));
        System.out.println(result);
    }
}
