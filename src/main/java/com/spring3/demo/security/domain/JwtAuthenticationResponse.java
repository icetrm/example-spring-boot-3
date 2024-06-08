package com.spring3.demo.security.domain;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
	
	private String token;

	private String refreshToken;

    public JwtAuthenticationResponse() {
		super();
	}

    public JwtAuthenticationResponse(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

}
