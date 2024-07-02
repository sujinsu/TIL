package com.example.structural_pattern._6_adapter.java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class AdapterInJava {

    public static void main(String[] args) {
        // collections
        List<String> strings = Arrays.asList("a", "b", "c"); // 배열을 전달하고 List로 받음,  가변 인자(variable arguments)
        Enumeration<String> enumeration = Collections.enumeration(strings); // strings변수가 어댑티,  Collections.enumeration가 어댑터, enumeration 타입이 Target interface
        ArrayList<String> list = Collections.list(enumeration);

        // io
        try(InputStream is = new FileInputStream("input.txt"); // 일종의 어댑터라고 해석 가능 세가지 모두
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr)) // try-with resource 문법
        {
            while(reader.ready()) {
                System.out.println(reader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
