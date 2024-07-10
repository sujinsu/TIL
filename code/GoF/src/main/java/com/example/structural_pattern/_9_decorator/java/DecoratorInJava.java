package com.example.structural_pattern._9_decorator.java;

import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collection이 제공하는 여러 오퍼레이션 중 기존 인스턴스 컬렉션에서 제공하는 인스턴스를 부가적인 기능 추가해 변환해주는 메서드
 */
public class DecoratorInJava {

    public static void main(String[] args) {
        // Collections가 제공하는 데코레이터 메소드
        ArrayList list = new ArrayList<>();
        list.add(new Book());

        List books = Collections.checkedList(list, Book.class);


        // 타입캐스팅 에러가 날 것.
//        books.add(new Item());

        // 순차적으로 접근하게끔 블록으로 직렬적으로 처리
//        Collections.synchronizedCollection(list);
        // 객체를 불변 객체로 취급
        List unmodifiableList = Collections.unmodifiableList(list);
        list.add(new Item());
//        unmodifiableList.add(new Book()); // 에러


        // 서블릿 요청 또는 응답 랩퍼
        HttpServletRequestWrapper requestWrapper; // httpservletRequest 확장해 부가적인 기능 추가 가능
        HttpServletResponseWrapper responseWrapper;
    }

    private static class Book {

    }

    private static class Item {

    }
}
