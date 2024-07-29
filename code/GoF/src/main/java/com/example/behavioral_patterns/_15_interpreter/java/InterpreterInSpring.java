package com.example.behavioral_patterns._15_interpreter.java;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * SPEL :  Expression Language 를 spring에서 만듬
 */
public class InterpreterInSpring {

    public static void main(String[] args) {
        // Book 이란 객체에서 spring 이란 타이틀로 뽑아서 출력하는 간단한 예제
        Book book = new Book("spring");

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("title");
        System.out.println(expression.getValue(book));
    }
}
