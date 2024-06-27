package com.example.creational_pattern._5_prototype.java;

import java.util.ArrayList;
import java.util.List;

public class JavaCollectionExample {

    public static void main(String[] args) {
        Student keesun = new Student("keesun");
        Student whiteship = new Student("whiteship");
        List<Student> students = new ArrayList<>(); // ArrayList가 아닌 List 로 받는 이유 : 추상적인 타입을 씀으로써 더욱 유연하게 사용하기 위해
        students.add(keesun);
        students.add(whiteship);

        // clone 메서드를 자주 쓰지 않는 이유
        // ArrayList는 cloneable 상속 받고, List는 cloneable 상속 안받음 -> ArrayList 생성자 사용함 : 프로토타입 활용은 아니고 얕은 복사 ㅇ
        List<Student> clone = new ArrayList<>(students);
        System.out.println(clone);
    }
}
