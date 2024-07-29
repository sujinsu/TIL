package com.example.behavioral_patterns._15_interpreter.java;

import java.util.regex.Pattern;

/**
 * Interpreter pattern : JVM 전체로 보면 컴파일러에 적용 & 정규표현식에 적용
 *
 */
public class InterpreterInJava {

    public static void main(String[] args) {
        System.out.println(Pattern.matches(".pr...", "spring"));
        System.out.println(Pattern.matches("[a-z]{6}", "spring"));
        System.out.println(Pattern.matches("white[a-z]{4}[0-9]{4}", "whiteship2000"));
        System.out.println(Pattern.matches("\\d", "1")); // one digit
        System.out.println(Pattern.matches("\\D", "a")); // one non-digit
    }
}
