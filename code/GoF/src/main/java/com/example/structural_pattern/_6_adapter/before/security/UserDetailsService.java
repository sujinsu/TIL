package com.example.structural_pattern._6_adapter.before.security;

public interface UserDetailsService {

    UserDetails loadUser(String username);

}
