package com.example.creational_pattern._3_abstract_factory.after;

import com.example.creational_pattern._3_abstract_factory.before.WhiteAnchor;
import com.example.creational_pattern._3_abstract_factory.before.WhiteWheel;

public class WhiteShipPartsFactory implements ShipPartsFactory{

    @Override
    public Anchor createAnchor() {
        return new WhiteAnchor();
    }

    @Override
    public Wheel createWheel() {
        return new WhiteWheel();
    }
}
