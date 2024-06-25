package com.example.creational_pattern._3_abstract_factory.after;

import com.example.creational_pattern._2_factory.after.DefaultShipFactory;
import com.example.creational_pattern._2_factory.after.Ship;
import com.example.creational_pattern._2_factory.after.WhiteShip;

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
