package com.example.behavioral_patterns._17_mediator.before;

/**
 * mediator pattern : 여러 객체 간의 의사소통 방법을 추상화 시켜서 여러 객체 간의 의존성 결합도를 낮추는 방법
 * ㄴ 여러 객체들이 소통하는 방법을 캡슐화하는 패턴
 * 
 * 실생활 예제 >> 층간 소음 등 서로 직접 방문 x, 관리 사무소 & 비행 상황 시 관제탑
 *
 * colleague는 colleague를 참조하지 않음. mediator를 참조.
 *
 * (+) : 컴포넌트 코드를 변경하지 않고 새로운 중재자를 만들어 사용
 * (+) : 각각의 컴포넌트 코드를 보다 간결하게 유지
 * (-) : 중재자 역할 클래스의 복잡도와 결합도 증가
 */
public class Hotel {

    public static void main(String[] args) {
        Guest guest = new Guest();
        guest.getTower(3);
        guest.dinner();

        Restaurant restaurant = new Restaurant();
        restaurant.clean();
    }
}
