package com.example.creational_pattern._4_builder.java;

import lombok.Builder;

/**
 * Builder API가 저절로 생성
 */
@Builder
public class LombokExample {

    private String title;

    private int nights;

    private int days;

    public static void main(String[] args) {
        LombokExample trip = LombokExample.builder()
                .title("여행")
                .nights(2)
                .days(3)
                .build();
    }

}
