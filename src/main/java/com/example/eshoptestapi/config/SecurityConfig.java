package com.example.eshoptestapi.config;

import com.example.eshoptestapi.security.JwtRequestFilter;
import com.example.eshoptestapi.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/authenticate", "/api/users/register", "/api/categories", "/api/categories/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/brands/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/brands").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/brands/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/brands/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/products/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/reviews").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.PUT, "/api/reviews/**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.DELETE, "/api/reviews/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/promotions").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/promotions/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/promotions/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/reviews/**").permitAll() // Allow everyone to view reviews
                .requestMatchers("/api/products/**").permitAll()
                .requestMatchers("/api/promotions/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
