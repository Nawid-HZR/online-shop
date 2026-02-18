package com.nawid.onlineshop.security;

import com.nawid.onlineshop.entity.User;
import com.nawid.onlineshop.repo.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class CustomUserDetailsService implements UserDetailsService {


    private UserRepo repo;

    public CustomUserDetailsService(UserRepo repo) {
        this.repo = repo;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {


        User user = repo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail )
                .orElseThrow(() -> new UsernameNotFoundException("User not exist by username or email"));

        Set<GrantedAuthority> authorities = Set.of(
                new SimpleGrantedAuthority(user.getRole())
        );

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );


    }
}
