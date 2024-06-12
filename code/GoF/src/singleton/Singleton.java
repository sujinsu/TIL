package singleton;

import singleton.domain.Person;

/**
 * 싱글톤 구현 방법 : static, sync, docblechecking,eager init, innerclass, enum
 * 싱글톤 꺠트리는 방법 : 리플렉션, 직렬화 & 역직렬화 -> 인스턴스 새로 생성
 */
public class Singleton {

    public static void main(String[] args) {
//        * static
//        Person person = Person.getPerson();
//        Person person1 = Person.getPerson();

//        * sync
//        Person person = Person.getPersonSync();
//        Person person1 = Person.getPersonSync();

//        * eager init
//        Person person = Person.getPersonEager();
//        Person person1 = Person.getPersonEager();

//        * inner class
        Person person = Person.getPersonInnerClass();
        Person person1 = Person.getPersonInnerClass();

//        * enum
//        singleton.value.Person person = singleton.value.Person.PERSON;
//        singleton.value.Person person1 = singleton.value.Person.PERSON;
        

        System.out.println("person == person1 :::" + (person == person1));
    }
}
