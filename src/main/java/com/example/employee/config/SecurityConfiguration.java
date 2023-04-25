package com.example.employee.config;

import com.example.employee.config.security.JwtAuthenticationExceptionHandling;
import com.example.employee.config.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration {
    private final static String H2_PATH = "/h2-console/**";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationExceptionHandling jwtAuthenticationExceptionHandling) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers(new AntPathRequestMatcher(H2_PATH)).permitAll()
                .anyRequest().authenticated());

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.cors().disable();

        // to exempt /h2-console from security
        http.headers().frameOptions().disable();
        http.csrf(csrf -> csrf
                .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher(H2_PATH)));

        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationExceptionHandling);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$10$PXtZ7IkhiF9vMHg8NQfv4e1zrDAwHZdURvLWzu5x87YOQTIm9MFVC")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$10$PXtZ7IkhiF9vMHg8NQfv4e1zrDAwHZdURvLWzu5x87YOQTIm9MFVC")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(users());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}
