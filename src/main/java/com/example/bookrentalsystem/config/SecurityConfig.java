package com.example.bookrentalsystem.config;

import com.example.bookrentalsystem.util.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // Define users manually
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles(Role.USER.name())
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("password"))
                .roles(Role.ADMIN.name())
                .build();

        manager.createUser(user);
        manager.createUser(admin);

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authz -> authz
                        .requestMatchers("/api/public/**").permitAll() // Public endpoints
                        .requestMatchers("/api/register").authenticated() // Secure registration endpoint
                        .requestMatchers("/api/books/**").hasRole(Role.ADMIN.name()) // Admin-only endpoints
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .httpBasic(withDefaults -> withDefaults.realmName("BookRentalSystem")); // HTTP Basic authentication configuration

        return http.build();
    }
}
