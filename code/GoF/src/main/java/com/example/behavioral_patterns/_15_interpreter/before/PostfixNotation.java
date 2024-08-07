package com.example.behavioral_patterns._15_interpreter.before;

import java.util.Stack;

/**
 * 인터프리터 예시 : 정규표현식
 *
 * context :  모든 expression에서 사용하는 공통된 정보가 들어있는 곳 (global 변수 등)
 * expression : interpret 메서드 (context 값 들어있음) >> tree 구조 생성 가능
 *  ㄴ terminal : 그 자체로 종료
 *  ㄴ non - terminal : 다른 expression을 참조하고, 재귀적으로 참조하는 expression
 */
public class PostfixNotation {

    private final String expression;

    public PostfixNotation(String expression) {
        this.expression = expression;
    }

    /**
     * 123+- 자체를 재사용할 수 있음 >> x,y,z는 각각 터미널 익스프레션에 해당 (해당 값 리턴하면 끝) BUT +- 는 다른 2개의 표현을 인터프리트 한 다음에 결과를 더해야 함
     * @param args
     */
    public static void main(String[] args) {
        PostfixNotation postfixNotation = new PostfixNotation("123+-");
        // 123 +- -> 15 - -> 1-5 -> -4
        postfixNotation.calculate();
        // 1 + 5 - 3 : infix
        // 1 5 + 3 - : postfix
    }

    private void calculate() {
        Stack<Integer> numbers = new Stack<>();

        for (char c : this.expression.toCharArray()) {
            switch (c) {
                case '+':
                    numbers.push(numbers.pop() + numbers.pop());
                    break;
                case '-':
                    int right = numbers.pop();
                    int left = numbers.pop();
                    numbers.push(left - right);
                    break;
                default:
                    numbers.push(Integer.parseInt(c + ""));
            }
        }

        System.out.println(numbers.pop());
    }
}
