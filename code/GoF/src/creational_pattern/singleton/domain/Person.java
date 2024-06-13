package creational_pattern.singleton.domain;

public class Person {

     private static Person person;

//    private static volatile Person personDoubleChecking;

     private static final Person personEager = new Person();

    /**
     *  (-) : 멀티쓰레드 방어 불가능
     * @return
     */
     public static Person getPerson(){
         if(person ==null){
             person = new Person();
         }
         return person;
     }


    /**
     * (+) : 멀티쓰레드 방어
     * (-) : 성능저하, 인스턴스 생성시 메서드 진입 불가
     * 클래스락 O, 인스턴스락 X
     * @return
     */
    public static synchronized Person getPersonSync(){
        if(person ==null){
            person = new Person();
        }
        return person;
    }

    /**
     * (+) : 멀티쓰레드 방어, 클래스락 방어
     *
     * 인스턴스 volatile 정의   ex)private static volatile Person person;
     * @return
     */
    public static Person getPersonSyncByDOubleChecking(){
        if(person ==null){
            synchronized(Person.class){
                if(person == null){
                    person = new Person();
                }
            }

        }
        return person;
    }



    /**
     * (+) : 멀티쓰레드 방어, 성능저하 방어
     * (-) : 사용하기 이전에 미리 생성
     * @return
     */
    public static Person getPersonEager(){
        return personEager;
    }

    /**
     * (+) : 추천하는 방법, 사용 시에 생성, 멀티쓰레드 방어, 성능저하 방어
     */

    private static class PersonHolder{
        private static final Person PERSON_INNER = new Person();
    }

    public static Person getPersonInnerClass(){
        return PersonHolder.PERSON_INNER;
    }
}
