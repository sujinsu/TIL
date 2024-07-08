package com.example.structural_pattern._8_composite.before;

/**
 * 트리구조
 * 컴포짓 패턴 : 그룹 전체와 개별 객체를 동일하게 처리할 수 있는 패턴
 * ㄴ 클라이언트 입장에서는 전체나 부분이나 모두 동일한 컴포넌트
 */
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

    private void printPrice(Item item) {
        System.out.println(item.getPrice());
    }

    private void printPrice(Bag bag) {
        int sum = bag.getItems().stream().mapToInt(Item::getPrice).sum();
        // reduce 방법도 있음
        // int sum = bag.getItems().stream().map(Item::getPrice).reduce(0, Integer::sum);
        System.out.println(sum);
    }

}
