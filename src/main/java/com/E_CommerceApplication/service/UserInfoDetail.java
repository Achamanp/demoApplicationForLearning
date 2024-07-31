package com.E_CommerceApplication.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import com.E_CommerceApplication.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoDetail implements UserDetails {

    private String userName;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoDetail(User user) {
        this.userName = user.getName();
        this.password = user.getPassword();
        this.authorities = user.getRoles().stream()
                               .map(role -> new SimpleGrantedAuthority(role.getName()))
                               .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

