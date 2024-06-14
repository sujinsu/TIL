package com.example.GoF.creational_pattern.factory.after;

public class BlackShipFactory extends DefaultShipFactory{
    @Override
    public Ship createShip() {
        return new BlackShip();
    }
}
