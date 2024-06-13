package creational_pattern.factory.after;

/**
 * 확장에는 열려있고, 변경에 닫혀있는 구조로 만들기 !
 */
public class Client {

    public static void main(String[] args) {
        WhiteShipFactory whiteShipFactory = new WhiteShipFactory();
        Ship whiteship = whiteShipFactory.orderShip("Whiteship", "keesun@mail.com");
        System.out.println(whiteship);

//        Ship blackship = WhiteShipFactory.orderShip("Blackship", "keesun@mail.com");
//        System.out.println(blackship);
    }

}
