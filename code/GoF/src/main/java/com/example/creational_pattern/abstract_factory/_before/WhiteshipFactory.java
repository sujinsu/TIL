package com.example.creational_pattern.abstract_factory._before;


import com.example.creational_pattern.factory.after.DefaultShipFactory;
import com.example.creational_pattern.factory.after.Ship;
import com.example.creational_pattern.factory.after.WhiteShip;

public class WhiteshipFactory extends DefaultShipFactory {


    @Override
    public Ship createShip() {
        Ship ship = new WhiteShip();
        ship.setAnchor(new WhiteAnchor());
        ship.setWheel(new WhiteWheel());
        return ship;
    }
}
