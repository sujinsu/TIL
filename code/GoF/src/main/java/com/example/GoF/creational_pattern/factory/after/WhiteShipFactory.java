package com.example.GoF.creational_pattern.factory.after;

public class WhiteShipFactory extends DefaultShipFactory {


    @Override
    public Ship createShip() {
        return new WhiteShip();
    }

}
