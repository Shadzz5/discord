package fr.discord.discord.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtHeaderFilter jwtHeaderFilter) throws Exception {
        http.authorizeHttpRequests(authorization -> {
            authorization.requestMatchers("/api/user").permitAll();
            authorization.requestMatchers("/api/subscribe").permitAll();
            authorization.requestMatchers("/api/login").permitAll();
            authorization.requestMatchers("/chatMessage").permitAll();
            // authorization.requestMatchers("/api/fournisseur/**").hasRole("ADMIN");
            authorization.requestMatchers("/**").authenticated();
        });
        // http.formLogin();
        // http.httpBasic();
        http.csrf(c -> c.disable());

        // On désactive les Cookies
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtHeaderFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173"));

        corsSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsSource;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}