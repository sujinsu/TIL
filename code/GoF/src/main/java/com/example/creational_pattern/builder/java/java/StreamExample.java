package me.whiteship.designpatterns._01_creational_patterns._04_builder._03_java;

import java.util.stream.Stream;

/**
 * StringBuilder 와 비슷하게 stream에 계속해서 추가하면서 진행하여 원하는 객체를 만들어냄
 */
public class StreamExample {

    public static void main(String[] args) {

        Stream.Builder<String> stringStreamBuilder = Stream.builder();
        Stream<String> names = stringStreamBuilder.add("keesun").add("whiteship").build();

        // 위 두 줄의 코드를 한 줄로 줄인 아래 코드는 안됨.
        Stream<String> names = Stream.builder().add("keesun").add("whiteship").build();


        // 이렇게 해야홤 < 자바 제네릭 타입 공부 필요
        Stream<String> names = Stream.<String>builder().add("keesun").add("whiteship").build();
        names.forEach(System.out::println);
    }
}
