package com.example.structural_pattern._8_composite.after;


/**
 * composit pattern에서 client는 구체적인 정보를 알 필요 없음.
 * component라는 인터페이스가 알고 있음
 *
 * (+) : 복잡한 트리 구조를 편리하게 사용 / 다형성과 재귀 활용 / 클라이언트 코드 변경 x 새로운 element type 추가 가능
 * (-) : 트리를 만들어야 함(공통된 인터페이스 정의) 지나친 일반화를 해야 하는 상황
 *  ㄴ ex) 런타임에 타입체크가 필요한 상황 등 (백이냐 아이템이냐 ) 에서는 꼭 디자인 패턴을 적용 시켜야 하는 상황인지 재고
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

    /**
     * 추상적인 타입을 처리함으로써 코드가 줄어듦
     * @param component
     */
    private void printPrice(Component component) {
        System.out.println(component.getPrice());
    }


}
