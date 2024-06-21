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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*"); // Allow all origins
        config.addAllowedHeader("*"); // Allow all headers
        config.addAllowedMethod("*"); // Allow all methods (GET, POST, etc.)
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

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
                .requestMatchers(HttpMethod.POST, "/api/promotions").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/promotions/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/promotions/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/wishlists").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.GET, "/api/wishlists/**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.PUT, "/api/wishlists/**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.DELETE, "/api/wishlists/**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.GET, "/api/carts").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.GET, "/api/carts/**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.POST, "/api/carts").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.PUT, "/api/carts/**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.DELETE, "/api/carts/**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.GET, "/api/reviews/**").permitAll() // Allow everyone to view reviews
                .requestMatchers(HttpMethod.GET, "/api/colors/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/colors/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/colors/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/colors/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/orders").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/orders/user").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.GET, "/api/api/orders/user/**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.POST, "/api/orders/**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.PUT, "/api/orders/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/orders/**").hasRole("ADMIN")
                .requestMatchers("/api/products/**").permitAll()
                .requestMatchers("/api/promotions/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
