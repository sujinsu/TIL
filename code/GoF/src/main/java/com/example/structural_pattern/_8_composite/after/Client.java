package com.example.structural_pattern._8_composite.after;


public class Client {

    public static void main(String[] args) {
        Item doranBlade = new Item("도란검", 450);
        Item healPotion = new Item("체력 물약", 50);

        Bag bag = new Bag();
        bag.add(doranBlade);
        bag.add(healPotion);

        Client client = new Client();
        client.printPrice(doranBlade);
        client.printPrice(bag);
    }

    /**
     * 추상적인 타입을 처리함으로써 코드가 줄어듦
     * @param component
     */
    private void printPrice(Component component) {
        System.out.println(component.getPrice());
    }


}
