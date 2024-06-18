package com.example.creational_pattern.factory.after;

/**
 * 팩토리 메서드 패턴 :: 구체적으로 어떤 것을 만들지는 서브클래스가 정한다.
 * 확장에는 열려있고, 변경에 닫혀있는 구조로 만들기 !
 */
public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        client.print(new WhiteShipFactory(),"Whiteship", "keesun@mail.com");
        client.print(new BlackShipFactory(),"Blackship", "keesun@mail.com");

    }

    private void print(ShipFactory shipFactory, String name, String email) {
        System.out.println(shipFactory.orderShip(name, email));
    }


}
