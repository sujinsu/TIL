package com.example.structural_pattern._7_bridge.before;


import com.example.structural_pattern._7_bridge.after.Skin;

public interface Champion extends Skin {

    void move();

    void skillQ();

    void skillW();

    void skillE();

    void skillR();

}
