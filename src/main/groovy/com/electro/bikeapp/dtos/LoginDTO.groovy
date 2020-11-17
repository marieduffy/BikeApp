package com.electro.bikeapp.dtos

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class LoginDTO implements UserDetails {

    String userName
    String password
    boolean active
    List<GrantedAuthority> authorities

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities
    }

    @Override
    String getPassword() {
        return password
    }

    @Override
    String getUsername() {
        return userName
    }

    @Override
    boolean isAccountNonExpired() {
       return true
    }

    @Override
    boolean isAccountNonLocked() {
        return true
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    boolean isEnabled() {
        return active
    }

}
