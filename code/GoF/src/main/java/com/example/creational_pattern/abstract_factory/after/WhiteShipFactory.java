package com.example.creational_pattern.abstract_factory.after;

import com.example.creational_pattern.factory.after.DefaultShipFactory;
import com.example.creational_pattern.factory.after.Ship;
import com.example.creational_pattern.factory.after.WhiteShip;

public class WhiteShipFactory  extends DefaultShipFactory {

    private ShipPartsFactory shipPartsFactory;
    public WhiteShipFactory(ShipPartsFactory shipPartsFactory){
        this.shipPartsFactory = shipPartsFactory;
    }
    @Override
    public Ship createShip() {
        Ship ship = new WhiteShip();
        ship.setAnchor(shipPartsFactory.createAnchor());
        ship.setWheel(shipPartsFactory.createWheel());
        return ship;
    }
}
