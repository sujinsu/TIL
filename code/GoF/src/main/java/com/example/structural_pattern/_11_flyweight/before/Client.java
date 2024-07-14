package com.example.structural_pattern._11_flyweight.before;

/**
 * Flyweight pattern 이란?
 * 객체를 가볍게 만들어 메모리 사용을 줄이는 패턴
 * : 자주 변하는 속성과 변하지 않는 속성을 분리, 재사용
 */
public class Client {

    public static void main(String[] args) {
        Character c1 = new Character('h', "white", "Nanum", 12);
        Character c2 = new Character('e', "white", "Nanum", 12);
        Character c3 = new Character('l', "white", "Nanum", 12);
        Character c4 = new Character('l', "white", "Nanum", 12);
        Character c5 = new Character('o', "white", "Nanum", 12);
    }
}
