package com.spring3.demo.security.domain;

import lombok.Data;

@Data
public class JwtAuthenticationRequest {

	private String username;
	private String password;

}
