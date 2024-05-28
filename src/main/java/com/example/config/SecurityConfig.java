package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User
            .withUsername("admin")
            .password(passwordEncoder().encode("admin1234"))
            .roles("admin")
            .build();
        UserDetails student = User
            .withUsername("student")
            .password(passwordEncoder().encode("student5678"))
            .roles("user")
            .build();
        return new InMemoryUserDetailsManager(admin, student);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.formLogin(login -> login
                .loginPage("/")
                .defaultSuccessUrl("/employee/showList")
                .loginProcessingUrl("/login")
                .usernameParameter("mailAddress")
                .passwordParameter("password")
                .permitAll())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/toInsert").permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/insert").permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/img/**").permitAll()
                .requestMatchers("/js/**").permitAll()
                .anyRequest().authenticated()
            );
        return http.build();
    }
}
