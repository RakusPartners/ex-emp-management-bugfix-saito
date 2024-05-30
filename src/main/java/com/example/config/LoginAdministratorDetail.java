package com.example.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.domain.LoginAdministrator;

public class LoginAdministratorDetail implements UserDetails{
    private final LoginAdministrator loginAdministrator;
    private final Collection<? extends GrantedAuthority> authorities;

    public LoginAdministratorDetail(LoginAdministrator loginAdministrator){
        this.loginAdministrator = loginAdministrator;
        this.authorities = loginAdministrator.roleList().stream().map(role -> new SimpleGrantedAuthority(role)).toList();
    }

    public LoginAdministrator getLoginAdministrator(){
        return loginAdministrator;
    }

    @Override
    public String getPassword(){
        return loginAdministrator.password();
    }

    @Override
    public String getUsername(){
        return loginAdministrator.mailAddress();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override // ユーザーが期限切れでなければtrueを返す
    public boolean isAccountNonExpired() { return true; }

    @Override // ユーザーがロックされていなければtrueを返す
    public boolean isAccountNonLocked() { return true; }

    @Override // ユーザーのパスワードが期限切れでなければtrueを返す
    public boolean isCredentialsNonExpired() { return true; }
    
    @Override // ユーザーが有効であればtrueを返す
    public boolean isEnabled() { return true; }

}
