package com.example.behavioral_patterns._21_strategy.before;

/**
 * strategy pattern : 알고리즘을 캡슐화, 공통된 인터페이스로 추상화해서 로직을 수행하는 곳에서 추상된 인터페이스만 사용
 * ㄴ 코드 변경 없이 알고리즘을 바꿔낼 수 있게끔 해주는 패턴
 *
 * ex) java에서 Comparator
 *
 * context에서 사용할 알고리즘을 클라이언트가 선택 (생성자 or operation)
 */
public class Client {

    public static void main(String[] args) {
        //BlueLightRedLight : context
        BlueLightRedLight blueLightRedLight = new BlueLightRedLight(3);
        blueLightRedLight.blueLight();
        blueLightRedLight.redLight();
    }
}
