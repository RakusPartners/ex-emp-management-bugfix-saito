package com.example.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.domain.Administrator;

public class LoginAdministratorDetail implements UserDetails {
    private final String mailAddress;
    private final String password;
    private final String name;
    private final Collection <? extends GrantedAuthority> authorities;

    public LoginAdministratorDetail(Administrator administrator){
        this.mailAddress = administrator.getMailAddress();
        this.name = administrator.getName();
        this.password = administrator.getPassword();
        this.authorities = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      // ロールのコレクションを返す
      return authorities;
    }
  
    @Override
    public String getPassword() {
      // パスワードを返す
      return password;
    }
  
    @Override
    public String getUsername() {
      // ログイン名を返す
      return mailAddress;
    }
  
    public String getName() {
      // ユーザー名を返す
      return name;
    }
    
    @Override
    public boolean isAccountNonExpired() {
      //  ユーザーが期限切れでなければtrueを返す
      return true;
    }
  
    @Override
    public boolean isAccountNonLocked() {
      //  ユーザーがロックされていなければtrueを返す
      return true;
    }
  
    @Override
    public boolean isCredentialsNonExpired() {
      //  パスワードが期限切れでなければtrueを返す
      return true;
    }
  
    @Override
    public boolean isEnabled() {
      //  ユーザーが有効ならtrueを返す
      return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mailAddress == null) ? 0 : mailAddress.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LoginAdministratorDetail other = (LoginAdministratorDetail) obj;
        if (mailAddress == null) {
            if (other.mailAddress != null)
                return false;
        } else if (!mailAddress.equals(other.mailAddress))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (authorities == null) {
            if (other.authorities != null)
                return false;
        } else if (!authorities.equals(other.authorities))
            return false;
        return true;
    }
}
