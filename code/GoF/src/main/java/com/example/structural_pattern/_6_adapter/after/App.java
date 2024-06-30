package com.example.structural_pattern._6_adapter.after;


import com.example.structural_pattern._6_adapter.after.security.LoginHandler;
import com.example.structural_pattern._6_adapter.after.security.UserDetailsService;

/**
 * AccountUserDetails, AccountUserDetailsService 두 개의 어댑터 생성
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
