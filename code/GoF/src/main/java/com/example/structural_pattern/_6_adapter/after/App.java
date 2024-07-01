package com.example.structural_pattern._6_adapter.after;


import com.example.structural_pattern._6_adapter.after.security.LoginHandler;
import com.example.structural_pattern._6_adapter.after.security.UserDetailsService;

/**
 * AccountUserDetails, AccountUserDetailsService 두 개의 어댑터 생성
 * ㄴ 어댑터를 통해 직접 고쳐서 진행 가능 이점
 *
 * 어댑터 패턴이란 ? : 기존 코드를 클라이언트가 사용하는 인터페이스의 구현체로 바꿔주는 패턴
 * (+) :  기존 코드 변경 x, 원하는 인터페이스 구현체 재사용, 기존 코드와 특정 구현체 변환 작업 각기 다른 클래스 분리하여 단일책임
 * (-) : 새 클래스 필요해 복잡도 증가, 상황에 따라 기존 코드 수정이 나을 수도
 */
public class App {

    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        UserDetailsService userDetailsService = new AccountUserDetailsService(accountService);
        LoginHandler loginHandler = new LoginHandler(userDetailsService);
        String login = loginHandler.login("keesun", "keesun");
        System.out.println(login);
    }
}
