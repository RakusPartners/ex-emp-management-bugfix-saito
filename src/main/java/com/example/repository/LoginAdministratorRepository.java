package com.example.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.LoginAdministrator;

@Repository
public class LoginAdministratorRepository {
    
    private static final RowMapper<LoginAdministrator> LOGIN_ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		LoginAdministrator loginAdministrator = new LoginAdministrator(
            rs.getInt("id"), 
            rs.getString("mail_address"), 
            rs.getString("name"), 
            rs.getString("password"),
            new ArrayList<String>()
        );
		
		return loginAdministrator;
	};

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LoginAdministratorRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<LoginAdministrator> findByMailAddress(String mailAdress){
        String sql ="SELECT id, name, mail_address, password FROM administrators WHERE mail_address=:mailAddress";
        SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAdress);
        LoginAdministrator loginAdministrator = jdbcTemplate.queryForObject(sql, param, LOGIN_ADMINISTRATOR_ROW_MAPPER);
        return Optional.ofNullable(loginAdministrator);
    }
}   
