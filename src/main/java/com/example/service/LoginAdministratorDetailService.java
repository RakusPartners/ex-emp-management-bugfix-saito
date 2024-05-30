package com.example.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.config.LoginAdministratorDetail;
import com.example.domain.LoginAdministrator;
import com.example.repository.LoginAdministratorRepository;

@Service
public class LoginAdministratorDetailService implements UserDetailsService {
    private final LoginAdministratorRepository repo;

    public LoginAdministratorDetailService(LoginAdministratorRepository repo){
        this.repo = repo;
    }

    @Override
    public LoginAdministratorDetail loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
        Optional<LoginAdministrator> administratorOp = repo.findByMailAddress(mailAddress);
        return administratorOp.map( loginAdministrator -> new LoginAdministratorDetail(loginAdministrator)).orElseThrow(()-> new UsernameNotFoundException("not found"));
    }

}
