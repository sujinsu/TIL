package com.example.structural_pattern._6_adapter.after;


import com.example.structural_pattern._6_adapter.after.security.UserDetails;

/**
 * 기존 Account 클래스 코드 수정이 필요없음.
 * 단일 책임 원칙 ㅇ, 객체지향적인 코딩
 */
public class AccountUserDetails implements UserDetails {

    private Account account;

    public AccountUserDetails(Account account) {
        this.account = account;
    }

    @Override
    public String getUsername() {
        return account.getName();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }
}
