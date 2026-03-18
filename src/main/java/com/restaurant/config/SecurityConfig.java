package com.restaurant.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // PUBLIC PAGES
//            		.requestMatchers(
//            		        "/",
//            		        "/login",
//            		        
//            		        "/register",
//            		        "/admin/**",
//            		        "/staff/**",
//            		        "/css/**",
//            		        "/js/**",
//            		        "/images/**"
//            		).permitAll()

                // API AUTH
                .requestMatchers("/api/auth/**").permitAll()

                //MenuItem without securiety
                
                .requestMatchers("/api/menu/**").permitAll()
                
                //Table 
                .requestMatchers("/api/tables/**").permitAll()
                
                
                //order
                .requestMatchers("/api/orders/**").permitAll()
                
                //invoice
              //order
                .requestMatchers("/api/invoice/**").permitAll()
                
                //Billing
                .requestMatchers("/api/billing/**").permitAll()
                
                //Customer
                .requestMatchers("/api/customers/**").permitAll()
                
                // ROLE BASED
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                
                .requestMatchers("/api/admin/**").hasAnyRole("ADMIN","STAFF")

                .anyRequest().authenticated()
            )

            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

            .httpBasic(basic -> basic.disable());

        return http.build();
    }
}