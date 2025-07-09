package com.ems.TasksManagementSystem.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Autowired
    private AuthenticationProvider  authenticationProvider;
    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;
    private String[] WHITELIST={"/api/v1/auth/**",//"/api/users","/api/users/**","/api/books","/api/books/**",
            "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html","/api-docs"
                };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
         http.csrf(csrf-> csrf.disable())
                 .authorizeHttpRequests(auth->
                         auth.requestMatchers(WHITELIST).permitAll()
                                 .requestMatchers("/api/v1/dept/**", "/api/v1/project/**", "/api/v1/task/**", "/api/v1/employee/**")
                                 .hasRole("ADMIN")

                                 // MANAGER: CRUD projects, tasks
                                 .requestMatchers("/api/v1/project/**", "/api/v1/task/**")
                                 .hasAnyRole("MANAGER", "ADMIN")
                                 // MANAGER: read employees and departments
                                 .requestMatchers("/api/v1/employee/**", "/api/v1/dept/**")
                                 .hasAnyRole("MANAGER", "ADMIN")

                                 // EMPLOYEE: read all, update status of their own tasks (method-level for update)
                                 .requestMatchers("/api/v1/employee/**", "/api/v1/dept/**", "/api/v1/project/**", "/api/v1/task/**")
                                 .hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                                 .anyRequest().authenticated()
                         )
                 .sessionManagement(session->
                         session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .authenticationProvider(authenticationProvider)
                 .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



}
