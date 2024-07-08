package com.example.structural_pattern._8_composite.after;

import java.util.ArrayList;
import java.util.List;

/**
 * leaf 타입을 참조하면 안된다
 *  Item -> Component :  private List<Component> components = new ArrayList<>();
 */
public class Bag implements Component {

    private List<Component> components = new ArrayList<>();

    public void add(Component component) {
        components.add(component);
    }

    public List<Component> getComponents() {
        return components;
    }

    /**
     * client 쪽에 있던 코드가 여기로 옴. 객체 지향적 ㅇㅇ
     *
     * @return
     */
    @Override
    public int getPrice() {
        return components.stream().mapToInt(Component::getPrice).sum();
    }
}
