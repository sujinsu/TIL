package com.example.behavioral_patterns._21_strategy.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StrategyInJava {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(5);

        System.out.println(numbers);

        // Comparator : 대표적인 strategy pattern
        Collections.sort(numbers, Comparator.naturalOrder());

        System.out.println(numbers);
    }
}
