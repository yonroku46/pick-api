package com.pick.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pick.security.handler.JsonAuthenticationSuccessHandler;
import com.pick.security.handler.JsonLogoutSuccessHandler;
import com.pick.security.repository.SecurityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationProvider authenticationProvider;
    private final JsonAuthenticationSuccessHandler jsonAuthenticationSuccessHandler;
    private final JsonLogoutSuccessHandler jsonLogoutSuccessHandler;
    private final SecurityRepository securityRepository;
    private final ObjectMapper objectMapper;
    private final String JSON_LOGIN_PROCESSING_URL = "/api/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.formLogin().disable();
        http.csrf().disable();

        http.authorizeRequests()
                .anyRequest().permitAll();

        http.logout()
                .logoutUrl("/api/logout")
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .logoutSuccessHandler(jsonLogoutSuccessHandler);

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .maximumSessions(2)
                .maxSessionsPreventsLogin(false);

        addJsonConfigurer(http);
    }

    private void addJsonConfigurer(HttpSecurity http) throws Exception {
        http.apply(new JsonLoginConfigurer<>(JSON_LOGIN_PROCESSING_URL))
                .addAuthenticationManager(authenticationManager())
                .loginSuccessHandler(jsonAuthenticationSuccessHandler)
                .securityRepository(securityRepository)
                .objectMapper(objectMapper);
    }

    @Override
    // 시큐리티에 provider등록
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    // auth매니저 생성
    protected AuthenticationManager authenticationManager() throws Exception {
        List<AuthenticationProvider> authProviderList = new ArrayList<>();
        authProviderList.add(authenticationProvider);
        ProviderManager providerManager = new ProviderManager(authProviderList);
        return providerManager;
    }
}
