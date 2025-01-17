package com.spring3.demo.security.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Date;
import java.util.List;
@Data
public class UserSecurityContext implements UserDetails {

	@Serial
	private static final long serialVersionUID = -7069679814644899509L;

	private final String id;
	private final String username;
	private final String password;
	private final Date lastLogin;
	private final List<GrantedAuthority> roles;

	public UserSecurityContext(String id, String username, String password, Date lastLogin, List<GrantedAuthority> roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastLogin = lastLogin;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
