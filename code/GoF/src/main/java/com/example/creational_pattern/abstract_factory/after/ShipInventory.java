package com.example.creational_pattern.abstract_factory.after;

import com.example.creational_pattern.factory.after.Ship;
import com.example.creational_pattern.factory.after.ShipFactory;

/**
 * 추상 팩토리 패턴 ::  구체적으로 어떤 클래스의 인스턴스를 (Concrete product) 를 사용하는지 감출 수 있다.
 *
 * 클라이언트 코드(WhiteShipFactory.java) 에서 구체적인 클래스의 의존성을 제거한다.
 */
public class ShipInventory {

    public static void main(String[] args) {
        ShipFactory shipFactory = new WhiteShipFactory(new WhiteShipPartsProFactory());
        Ship ship = shipFactory.createShip();
        System.out.println("ship.getAnchor() = " + ship.getAnchor().getClass());
        System.out.println("ship.getWheel() = " + ship.getWheel().getClass());
    }
}
