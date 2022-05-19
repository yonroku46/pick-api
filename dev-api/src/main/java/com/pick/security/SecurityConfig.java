package com.pick.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pick.repository.UserRepository;
import com.pick.security.handler.JsonAuthenticationFailureHandler;
import com.pick.security.handler.JsonAuthenticationSuccessHandler;
import com.pick.security.handler.JsonLogoutSuccessHandler;
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
    private final JsonAuthenticationFailureHandler jsonAuthenticationFailureHandler;
    private final JsonLogoutSuccessHandler jsonLogoutSuccessHandler;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final String JSON_LOGIN_PROCESSING_URL = "/api/login";
    private final String JSON_LOGOUT_PROCESSING_URL = "/api/logout";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.formLogin().disable();
        http.csrf().disable();

        http.authorizeRequests()
                .anyRequest().permitAll();

        http.logout()
                .logoutUrl(JSON_LOGOUT_PROCESSING_URL)
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
                .loginFailureHandler(jsonAuthenticationFailureHandler)
                .securityRepository(userRepository)
                .objectMapper(objectMapper);
    }

    // 시큐리티에 provider등록
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    // auth매니저 생성
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        List<AuthenticationProvider> authProviderList = new ArrayList<>();
        authProviderList.add(authenticationProvider);
        ProviderManager providerManager = new ProviderManager(authProviderList);
        return providerManager;
    }
}
