package com.example.structural_pattern._6_adapter.after;

/**
 * 코드 내용이 중요 하진 않음
 * 어떻게  user details 을 연결할 것인가 집중
 *
 * implements user detail service 하면 어댑터 생성하지 않아도 됨.
 *  -> 단일책임원칙에 덜 가까움. 어댑터를 만들어 새 클래스로 하는게 객체지향적인 코딩
 */
public class AccountService {

    public Account findAccountByUsername(String username) {
        Account account = new Account();
        account.setName(username);
        account.setPassword(username);
        account.setEmail(username);
        return account;
    }

    public void createNewAccount(Account account) {

    }

    public void updateAccount(Account account) {

    }

}
