package com.example.creational_pattern._2_factory.after;

import com.example.creational_pattern._3_abstract_factory.after.Anchor;
import com.example.creational_pattern._3_abstract_factory.after.Wheel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ship {

    private String name;

    private String color;

    private String logo;


    private Wheel wheel;

    private Anchor anchor;


    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
