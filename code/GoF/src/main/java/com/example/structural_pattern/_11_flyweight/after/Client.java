package com.example.structural_pattern._11_flyweight.after;


/**
 * 플라이웨이트 패턴
 * (+) : 애플리케이션에서 사용하는 메모리를 줄일 수 있다
 * (-) : 코드 복잡도 증가
 */
public class Client {

    public static void main(String[] args) {
        FontFactory fontFactory = new FontFactory();
        Character c1 = new Character('h', "white", fontFactory.getFont("nanum:12"));
        Character c2 = new Character('e', "white", fontFactory.getFont("nanum:12"));
        Character c3 = new Character('l', "white", fontFactory.getFont("nanum:12"));
    }
}
