package com.pblgllgs.todobackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 *
 * @author pblgl
 * Created on 27-02-2024
 *
 */
@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

    private final String baseUrl = "/api/1.0/**";
    private UserDetailsService userDetailsService;

    public SpringSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                                auth
                                        .requestMatchers(HttpMethod.POST,"/api/1.0/auth/**").permitAll()
                                        .anyRequest().authenticated()
//                                .requestMatchers(HttpMethod.POST,baseUrl).hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.PUT,baseUrl).hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.DELETE,baseUrl).hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.PATCH,baseUrl).hasAnyRole("ADMIN","USER")
//                                .requestMatchers(HttpMethod.GET,baseUrl).hasAnyRole("ADMIN","USER")
                ).httpBasic(Customizer.withDefaults())
                .build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails pbl = User.builder()
//                .username("pbl")
//                .password(passwordEncoder().encode("pass"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("pass"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(pbl, admin);
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
