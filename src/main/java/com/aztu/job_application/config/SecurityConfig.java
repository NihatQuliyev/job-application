package com.aztu.job_application.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.aztu.job_application.model.enums.RoleType.ADMIN;
import static com.aztu.job_application.model.enums.RoleType.USER;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        String SWAGGER_UI = "/swagger-ui/**";
        String API_DOCS = "/v3/api-docs/**";

        String LOGIN = "api/v1/users/authentication";
        String LOGOUT = "api/v1/users/logout";
        String USERS_REGISTRATION = "api/v1/users/registration";
        String ADMIN_REGISTRATION = "api/v1/admins/registration";
        String USER_CONFIRMATION = "api/v1/users/confirmation";
        String ADMIN_CONFIRMATION = "api/v1/admins/confirmation";

        String RESET_PASSWORD = "api/v1/users/reset-password";
        String RENEW_PASSWORD = "api/v1/users/renew-password/{username}";
        String CHANGE_PASSWORD = "api/v1/users/change-password";

        String ROLE = "api/v1/admins/roles";

        http
                .csrf(CsrfConfigurer::disable)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurer()))
                .sessionManagement(sessionConfigure -> sessionConfigure.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> {

//                    request.requestMatchers(SWAGGER_UI).permitAll();
//                    request.requestMatchers(API_DOCS).permitAll();
//
//                    request.requestMatchers(HttpMethod.POST, LOGIN).permitAll();
//                    request.requestMatchers(HttpMethod.POST, LOGOUT).authenticated();
//
//                    request.requestMatchers(HttpMethod.POST, ADMIN_REGISTRATION).hasRole(ADMIN.name());
//                    request.requestMatchers(HttpMethod.GET, ADMIN_CONFIRMATION).permitAll();
//                    request.requestMatchers(HttpMethod.POST, ROLE).hasRole(ADMIN.name());
//                    request.requestMatchers(HttpMethod.PATCH, ROLE).hasRole(ADMIN.name());
//                    request.requestMatchers(HttpMethod.GET, ROLE).hasRole(ADMIN.name());
//
//                    request.requestMatchers(HttpMethod.PUT, RESET_PASSWORD).permitAll();
//                    request.requestMatchers(HttpMethod.POST, RENEW_PASSWORD).permitAll();
//                    request.requestMatchers(HttpMethod.POST, USERS_REGISTRATION).permitAll();
//                    request.requestMatchers(HttpMethod.GET, USER_CONFIRMATION).permitAll();
//                    request.requestMatchers(HttpMethod.PATCH, CHANGE_PASSWORD).hasRole(USER.name()  );

                    request.anyRequest().permitAll();

                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    private UrlBasedCorsConfigurationSource corsConfigurer() {
        CorsConfiguration corsConfigure = new CorsConfiguration();
        corsConfigure.addAllowedOrigin("*");
        corsConfigure.addAllowedHeader("*");
        corsConfigure.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfigure);

        return corsConfigurationSource;
    }
}