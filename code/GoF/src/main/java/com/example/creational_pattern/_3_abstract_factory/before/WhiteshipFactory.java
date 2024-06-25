package com.example.creational_pattern._3_abstract_factory.before;


import com.example.creational_pattern._2_factory.after.DefaultShipFactory;
import com.example.creational_pattern._2_factory.after.Ship;
import com.example.creational_pattern._2_factory.after.WhiteShip;

public class WhiteshipFactory extends DefaultShipFactory {


    @Override
    public Ship createShip() {
        Ship ship = new WhiteShip();
        ship.setAnchor(new WhiteAnchor());
        ship.setWheel(new WhiteWheel());
        return ship;
    }
}
