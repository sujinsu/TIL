package com.example.structural_pattern._6_adapter.after.security;

public interface UserDetailsService {

    UserDetails loadUser(String username);

}
