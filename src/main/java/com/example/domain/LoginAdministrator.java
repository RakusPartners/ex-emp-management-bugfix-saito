package com.example.domain;

import java.util.List;

public record LoginAdministrator(Integer id, String mailAddress, String name, String password, List<String> roleList) {
    
}
