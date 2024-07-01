package com.example.structural_pattern._6_adapter.after;


import com.example.structural_pattern._6_adapter.after.security.UserDetails;
import com.example.structural_pattern._6_adapter.after.security.UserDetailsService;

/**
 * 기존 AccountService 클래스 코드 수정이 필요 없음.
 * 단일 책임 원칙 ㅇ, 객체 지향적인 코딩
 */
public class AccountUserDetailsService implements UserDetailsService {

    private AccountService accountService;

    public AccountUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUser(String username) {
        return new AccountUserDetails(accountService.findAccountByUsername(username));
    }
}
