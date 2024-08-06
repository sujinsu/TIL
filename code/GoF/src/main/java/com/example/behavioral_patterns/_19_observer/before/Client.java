package com.example.behavioral_patterns._19_observer.before;

/**
 * Observer pattern : 상태 변경 감지 및 반응을 해야 하는 경우 적용
 * - Subject : 여러 observer 등록 및 해지 기능 - subscribe(Observer), unsubscribe(Observer), notify()
 *   ㄴ 상태 변경 시 자신에게 등록된 observer를 순회하며 observer 메서드 호출
 *
 * - Observer : - update()
 *
 * (+) :  pub/sub 패턴 구현 용이
 */
public class Client {

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();

        // get 해와야만 추가 메세지 확인 가능 >> 주기적으로 가져와야함.(폴링)
        // >> BUT 새 메세지 없을 경우 주기적 호출 불필요
       User user1 = new User(chatServer);
        user1.sendMessage("디자인패턴", "이번엔 옵저버 패턴입니다.");
        user1.sendMessage("롤드컵2021", "LCK 화이팅!");

        User user2 = new User(chatServer);
        System.out.println(user2.getMessage("디자인패턴"));

        user1.sendMessage("디자인패턴", "예제 코드 보는 중..");
        System.out.println(user2.getMessage("디자인패턴"));
    }
}
