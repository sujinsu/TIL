package com.example.behavioral_patterns._15_interpreter.after;

import java.util.Stack;

/**
 * 문자가 들어오면 캐릭터를 순회하면서 파싱
 */
public class PostfixParser {

    public static PostfixExpression parse(String expression) {
        // expression들의 스택
        Stack<PostfixExpression> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            stack.push(getExpression(c, stack));
        }
        return stack.pop();
    }

    private static PostfixExpression getExpression(char c, Stack<PostfixExpression> stack) {
        switch (c) {
            case '+':
                return new PlusExpression(stack.pop(), stack.pop());
            case '-':
                PostfixExpression right = stack.pop();
                PostfixExpression left = stack.pop();
                return new MinusExpression(left, right);
            default:
                return new VariableExpression(c);
        }
    }
}
