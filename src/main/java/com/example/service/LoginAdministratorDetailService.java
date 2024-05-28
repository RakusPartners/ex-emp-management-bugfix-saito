package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.config.LoginAdministratorDetail;
import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import com.example.repository.SecurityAdministratorRepository;

@Service
public class LoginAdministratorDetailService implements UserDetailsService {
    // private final SecurityAdministratorRepository securityAdministratorRepository;
    @Autowired
    private AdministratorRepository administratorRepository;

    // public LoginAdministratorDetailService(SecurityAdministratorRepository securityAdministratorRepository){
    //     this.securityAdministratorRepository = securityAdministratorRepository;
    // }

    @Override
    public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
    Administrator administrator = administratorRepository.findByMailAddress(mailAddress);
    LoginAdministratorDetail loginAdministratorDetail = new LoginAdministratorDetail(administrator);
    return loginAdministratorDetail;
    // return _administrator.map(administrator -> new LoginAdministratorDetail(administrator))
    //     .orElseThrow(() -> new UsernameNotFoundException("not found email=" + mailAddress));
    // }
    }
}
