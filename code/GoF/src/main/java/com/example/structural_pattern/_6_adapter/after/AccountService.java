package com.example.structural_pattern._6_adapter.after;

/**
 * 코드 내용이 중요 하진 않음
 * 어떻게  user details 을 연결할 것인가 집중
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
