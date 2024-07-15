package com.example.structural_pattern._11_flyweight.java;

/**
 * valueof 문서 설명보면 caching
 */
public class FlyweightInJava {

    public static void main(String[] args) {
        Integer i1 = Integer.valueOf(10);
        Integer i2 = Integer.valueOf(10);
        System.out.println(i1 == i2); // 캐싱(-128~127)해서 가져온거라 true
        // 10000 같이 캐싱 범위를 벗어난 숫자는 == 결과 false >> == 쓰지말자
        // 숫자 문자 비교 시엔 equals 로 비교! 값으로 비교!
    }
}
