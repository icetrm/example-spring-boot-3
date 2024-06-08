package com.spring3.demo.domain.response;

import lombok.Data;

@Data
public class UserResponse {
    private String name;

    public UserResponse(String name) {
        this.name = name;
    }

}

