package com.example.creational_pattern._4_builder.java;

/**
 * StringBuilder : syncronized 안 쓰는 API
 *  ㄴ 들어갈 데이터를 추가해가면서 진행
 * StringBuilder와 String buffer 차이
 */
public class StringBuilderExample {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        String result = stringBuilder.append("whiteship").append("keesun").toString();
        System.out.println(result);
    }
}
