package com.example.FinalExamProject.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalAuthentication
public class SecurityConfiguration {
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity security) throws  Exception
    {
        return  security.getSharedObject(AuthenticationManagerBuilder.class).build();
    }


    @Bean
    public PasswordEncoder encoder(){
        return  new BCryptPasswordEncoder(); // encrypts our code
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(HttpMethod.GET, "/products/search").permitAll();
                    authorize.requestMatchers(HttpMethod.GET, "/products").permitAll();

                    authorize.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/createNewUser").permitAll();

                    authorize.anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthentication(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    JwtAuthenticationFilter jwtAuthentication()
    {
        return new JwtAuthenticationFilter();
    }








}
