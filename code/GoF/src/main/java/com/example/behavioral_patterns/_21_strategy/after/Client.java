package com.example.behavioral_patterns._21_strategy.after;

/**
 * (+) : 새로운 전략 추가 시 기존 코드 수정 X (Open closed principle)
 * (+) : single responsibility
 * (+) : 상속 대신 위임 사용 가능 (상속은 하나만 가능, 상속은 상위 클래스 변경 시 하위 다 영향 있음)
 * (+) : 런타임에 전략 변경 가능
 */
public class Client {

    public static void main(String[] args) {
        BlueLightRedLight game = new BlueLightRedLight();
        game.blueLight(new Normal()); // operation 마다 strategy 선택
        game.redLight(new Fastest());

        // 익명 내부 클래스 >> 클래스 생성 없이 없이 직접 구현 (ex. comparator)
        game.blueLight(new Speed() {
            @Override
            public void blueLight() {
                System.out.println("blue light");
            }

            @Override
            public void redLight() {
                System.out.println("red light");
            }
        });
    }
}
