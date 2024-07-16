package com.example.structural_pattern._12_proxy.java;

import org.springframework.stereotype.Service;

@Service // bean으로 등록 >>  aop 적용 가능
public class GameService {

    public void startGame() {
        System.out.println("이 자리에 오신 여러분을 진심으로 환영합니다.");
    }

}
