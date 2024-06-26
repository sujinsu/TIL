package com.example.structural_pattern._6_adapter.before;

/**
 * security 디렉토리(패키지)와 무관하게 애플리케이션에서 만드는 account
 *
 * account는 해당 애플리케이션에서 사용하고, security 패키지는 다른 곳에서 재사용 가능
 */
public class Account {

    private String name;

    private String password;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
