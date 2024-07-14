package com.example.structural_pattern._11_flyweight.after;


/**
 * character에서 내적인 속성(자주 변경하지 않을 속성)만  따로 모음. 기준은 그때그때 달라질 수 있음.
 *
 *  *주의 !! immutable 해야 함. 공유하는 객체이기 때문에 어느 한 곳에서 바뀌면 전체에 영향 >> final
 */
public final class Font {

    final String family;

    final int size;

    public Font(String family, int size) {
        this.family = family;
        this.size = size;
    }

    public String getFamily() {
        return family;
    }

    public int getSize() {
        return size;
    }
}
