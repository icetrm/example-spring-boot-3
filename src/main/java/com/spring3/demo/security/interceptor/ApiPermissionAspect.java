package com.spring3.demo.security.interceptor;

import com.spring3.demo.exception.ResourceNotFoundException;
import com.spring3.demo.security.domain.RoleGrantedAuthority;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class ApiPermissionAspect {
	
	@Before("@annotation(allowRoles)")
	public void verifyRoles(JoinPoint joinPoint, AllowRoles allowRoles) {
	
		// Get user authority roles from security context
		List<RoleGrantedAuthority> authorityRoles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(authority -> (RoleGrantedAuthority)authority).toList();
		
		authorityRoles.stream().filter(authorityRole -> Arrays.asList(allowRoles.roles()).contains(authorityRole.getAuthority()))
		.findFirst().orElseThrow(() -> new ResourceNotFoundException(SecurityContextHolder.getContext().getAuthentication().getName() + " is Permission denied"));
	}
}