package com.example.creational_pattern._2_factory.after;

public class WhiteShipFactory extends DefaultShipFactory {


    @Override
    public Ship createShip() {
        return new WhiteShip();
    }

}
