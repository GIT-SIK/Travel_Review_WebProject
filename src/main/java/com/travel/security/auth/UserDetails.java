package com.travel.security.auth;

import com.travel.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;


public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private User user; //컴포지션

    public UserDetails(User user) {
        this.user = user;
    }


    //해당 User이 가지고 있는 권한 목록을 리턴함.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
    }

    public User getUser(){return user;}

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getId();
    }

    //계정 만료됐는지 않았는지 리턴 (true: 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠겨있는지 않았는지 리턴 (true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 만료되지 않았는지 리턴. (ture: 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 사용가능인지 리턴(true : 활성화)
    @Override
    public boolean isEnabled() {

        //1년동안 로그인 안할시 휴면계정으로 돌림
        return true;
    }

}