package com.example.structural_pattern._11_flyweight.after;

/**
 * 외적인 속성(자주 변경될 가능성이 있는 것)은 그대로 Character로 생성
 */
public class Character {

    private char value;

    private String color;

    private Font font;

    public Character(char value, String color, Font font) {
        this.value = value;
        this.color = color;
        this.font = font;
    }
}
