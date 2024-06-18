package com.example.creational_pattern.abstract_factory.after;

import com.example.creational_pattern.factory.after.Ship;
import com.example.creational_pattern.factory.after.ShipFactory;

public class ShipInventory {

    public static void main(String[] args) {
        ShipFactory shipFactory = new WhiteShipFactory(new WhiteShipPartsProFactory());
        Ship ship = shipFactory.createShip();
        System.out.println("ship.getAnchor() = " + ship.getAnchor().getClass());
        System.out.println("ship.getWheel() = " + ship.getWheel().getClass());
    }
}
