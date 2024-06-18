package com.example.creational_pattern.abstract_factory.after;

import com.example.creational_pattern.abstract_factory._before.WhiteAnchor;
import com.example.creational_pattern.abstract_factory._before.WhiteWheel;

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
