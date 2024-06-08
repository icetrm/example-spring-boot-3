package com.spring3.demo.config;

import com.spring3.demo.security.filter.JwtAuthenticationProvider;
import com.spring3.demo.security.filter.JwtTokenAuthenticationProcessingFilter;
import com.spring3.demo.security.filter.UsernamePasswordAuthenticationProvider;
import com.spring3.demo.security.util.JwtTokenUtil;
import com.spring3.demo.security.util.SkipPathRequestMatcher;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
@AllArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtTokenUtil jwtTokenUtil;
    private final UsernamePasswordAuthenticationProvider userAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.authenticationProvider(userAuthenticationProvider);
        authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
        //authenticationManagerBuilder.userDetailsService(jwtUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());

        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/auth/jwt/**", "/api/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                // don't create session
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS));

        // Custom JWT based security filter
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(authenticationManager(httpSecurity)), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        //httpSecurity.headers(header -> header.cacheControl(cache -> cache.disable()));

        return httpSecurity.build();
    }

    protected JwtTokenAuthenticationProcessingFilter authenticationTokenFilterBean(AuthenticationManager authenticationManager) throws Exception {
        List<String> pathsToSkip = List.of("/api/service2/**");
        List<String> processingPath = Arrays.asList("/me/**", "/api/**");
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, processingPath);
        JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(matcher, jwtTokenUtil, pathsToSkip);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }


}
