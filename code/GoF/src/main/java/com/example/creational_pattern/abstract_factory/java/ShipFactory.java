package com.example.creational_pattern.abstract_factory.java;


import com.example.creational_pattern.factory.after.Ship;
import com.example.creational_pattern.factory.after.WhiteShip;
import org.springframework.beans.factory.FactoryBean;

/**
 * spring에서 추상팩토리 구조 예시 : FactoryBean
 * ship Factory가 아닌 Ship 이라는 오브젝트 자체가 빈으로 등록 및 처리 <- spring 내부
 */
public class ShipFactory implements FactoryBean<Ship> {

    @Override
    public Ship getObject() throws Exception {
        Ship ship = new WhiteShip();
        ship.setName("whiteship");
        return ship;
    }

    @Override
    public Class<?> getObjectType() {
        return Ship.class;
    }
}
