package com.example.behavioral_patterns._15_interpreter.after;

import java.util.Map;

/**
 * 특정 expression 자체를 문법으로 쓰고 싶다. 파서를 이용해서 트리 구조로 만들어서 사용
 *
 * (+) : xyz+- 와 같이 자주 사용되는 패턴, 문법을 정의하고 재사용 & context만 바꿔서 사용 가능 (Map ~~)
 * (+) : 기존 코드 건드리지 않고 확장 가능 >> parser는 약간 손을 봐야하긴 함
 * (-) : 코드 복잡 >> ROI 고려 (Return of Interest)
 */
public class App {

    public static void main(String[] args) {
        PostfixExpression expression = PostfixParser.parse("xyz+-a+");
        // Map : context 정보
        int result = expression.interpret(Map.of('x', 1, 'y', 2, 'z', 3, 'a', 4));
        System.out.println(result);
    }
}
