package com.example.structural_pattern._6_adapter.after;

/**
 * implements userdetail 하면 어댑터 없어도 됨.
 *  -> 단일책임원칙에 덜 가까움. 어댑터를 만들어 새 클래스로 하는게 객체지향적인 코딩
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
