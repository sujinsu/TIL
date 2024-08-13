package com.example.behavioral_patterns._22_template.before;

/**
 * Template pattern : 알고리즘 구조를 서브 클래스가 확장할 수 있도록 템플릿으로 제공
 *  ㄴ 만약 지금의 더하는 과정 외에 곱하는 것이 필요하다면 파일읽어오는 부분 등 중복 발생 ! >> template 필요
 *
 * - AbstractClass 하나
 * - ConcreteClass 하나 or 여러개
 */
public class Client {

    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor("number.txt");
        int result = fileProcessor.process();
        System.out.println(result);
    }
}
