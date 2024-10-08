package com.example.behavioral_patterns._21_strategy.after;

/**
 * Context
 */
public class BlueLightRedLight {

    public void blueLight(Speed speed) {
        speed.blueLight();
    }

    public void redLight(Speed speed) {
        speed.redLight();
    }
}
