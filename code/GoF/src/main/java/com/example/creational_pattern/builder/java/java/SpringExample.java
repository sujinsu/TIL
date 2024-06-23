package me.whiteship.designpatterns._01_creational_patterns._04_builder._03_java;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


public class SpringExample {

    public static void main(String[] args) {
        UriComponents howToStudyJava = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("www.whiteship.me")
                .path("java playlist ep1")
                .build().encode(); // encode : 공백 등 안전하게 처리 가능
        System.out.println(howToStudyJava);
    }
}
