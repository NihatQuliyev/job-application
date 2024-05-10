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
    private final CommonConfig commonConfig;

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        String SWAGGER_UI = "/swagger-ui/**";
        String API_DOCS = "/v3/api-docs/**";

        String LOGIN = "api/v1/users/authentication";
        String LOGOUT = "api/v1/users/logout";
        String USERS_REGISTRATION = "api/v1/users/registration";
        String FIND_ALL_USERS = "api/v1/users/find-all-users";
        String ADMIN_REGISTRATION = "api/v1/admins/registration";
        String USER_CONFIRMATION = "api/v1/users/confirmation";
        String ADMIN_CONFIRMATION = "api/v1/admins/confirmation";

        String RESET_PASSWORD = "api/v1/users/reset-password";
        String RENEW_PASSWORD = "api/v1/users/renew-password/{username}";
        String CHANGE_PASSWORD = "api/v1/users/change-password";
        String JOB_APPLY = "api/v1/users/job-apply";
        String ROLE = "api/v1/admins/roles";
        String CATEGORIES = "api/v1/admins/categories";
        String ID = "{id}";

        String COMPANIES = "api/v1/admins/companies";

        String USER_INFORMATION = "/api/v1/user-informations";
        String SEARCH_BY_NAME_OR_SURNAME = "/search-by-name-or-surname";
        String SEARCH_BY_EDUCATION_LEVEL = "/search-by-education-level";
        String SEARCH_BY_ADDRESS = "/search-by-address";
        String SEARCH_BY_MILITARY_QUALIFACTION = "/search-by-military-qualification";
        String GENDERS = "/genders";
        String MARITAL_STATUS = "/marital-status";
        String EDUCATION_LEVEL = "/education-level";
        String EMPLOYMENT_STATUS = "/employment-status";
        String LANGUAGES = "/languages";
        String LANGUAGES_LEVELS = "/languages-levels";
        String SOFT_SKILLS = "/soft-skills";
        String MILITARY_QUALIFACTION = "/military-qualifications";

        String VACANCY = "api/v1/admins/vacancy";
        String CATEGORY_ID = "/category/{category-id}";
        String CITY = "/city";
        String POSITION = "/position";
        String SALARY = "/salary";
        String COMPANY_NAME = "/company-name";

        http
                .csrf(CsrfConfigurer::disable)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurer()))
                .sessionManagement(sessionConfigure -> sessionConfigure.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> {

                    request.requestMatchers(SWAGGER_UI).permitAll();
                    request.requestMatchers(API_DOCS).permitAll();
                    request.requestMatchers(HttpMethod.POST, LOGIN).permitAll();
                    request.requestMatchers(HttpMethod.POST, LOGOUT).authenticated();

                    request.requestMatchers(HttpMethod.POST, ADMIN_REGISTRATION).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET, ADMIN_CONFIRMATION).permitAll();
                    request.requestMatchers(HttpMethod.POST, ROLE).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.PATCH, ROLE).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET, ROLE).hasRole(ADMIN.name());
//
                    request.requestMatchers(HttpMethod.PUT, RESET_PASSWORD).permitAll();
                    request.requestMatchers(HttpMethod.POST, RENEW_PASSWORD).permitAll();
                    request.requestMatchers(HttpMethod.POST, USERS_REGISTRATION).permitAll();
                    request.requestMatchers(HttpMethod.GET, USER_CONFIRMATION).permitAll();
                    request.requestMatchers(HttpMethod.PATCH, CHANGE_PASSWORD).hasAnyRole(USER.name(),ADMIN.name());


                    request.requestMatchers(HttpMethod.POST,CATEGORIES).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET,CATEGORIES).hasAnyRole(ADMIN.name(),USER.name());
                    request.requestMatchers(HttpMethod.GET,CATEGORIES + ID).hasAnyRole(ADMIN.name(),USER.name());

                    request.requestMatchers(HttpMethod.POST,COMPANIES).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET,COMPANIES).hasAnyRole(ADMIN.name(),USER.name());
                    request.requestMatchers(HttpMethod.GET,COMPANIES + ID).hasAnyRole(ADMIN.name(),USER.name());

                    request.requestMatchers(HttpMethod.POST, JOB_APPLY).hasRole(USER.name());
                    request.requestMatchers(HttpMethod.GET, JOB_APPLY).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET, FIND_ALL_USERS).hasRole(ADMIN.name());

                    request.requestMatchers(HttpMethod.GET,USER_INFORMATION + SEARCH_BY_NAME_OR_SURNAME).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET,USER_INFORMATION + SEARCH_BY_EDUCATION_LEVEL).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET,USER_INFORMATION + SEARCH_BY_ADDRESS).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET,USER_INFORMATION + SEARCH_BY_MILITARY_QUALIFACTION).hasRole(ADMIN.name());

                    request.requestMatchers(HttpMethod.GET,USER_INFORMATION + GENDERS).permitAll();
                    request.requestMatchers(HttpMethod.GET,USER_INFORMATION + GENDERS).permitAll();
                    request.requestMatchers(HttpMethod.GET,USER_INFORMATION + MARITAL_STATUS).permitAll();
                    request.requestMatchers(HttpMethod.GET,USER_INFORMATION + EDUCATION_LEVEL).permitAll();
                    request.requestMatchers(HttpMethod.GET,USER_INFORMATION + EMPLOYMENT_STATUS).permitAll();
                    request.requestMatchers(HttpMethod.GET,USER_INFORMATION + LANGUAGES).permitAll();
                    request.requestMatchers(HttpMethod.GET,USER_INFORMATION + LANGUAGES_LEVELS).permitAll();
                    request.requestMatchers(HttpMethod.GET,USER_INFORMATION + SOFT_SKILLS).permitAll();
                    request.requestMatchers(HttpMethod.GET,USER_INFORMATION + MILITARY_QUALIFACTION).permitAll();

                    request.requestMatchers(HttpMethod.POST,VACANCY).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET,VACANCY).hasAnyRole(ADMIN.name(),USER.name());
                    request.requestMatchers(HttpMethod.GET,VACANCY + CATEGORY_ID).hasAnyRole(ADMIN.name(),USER.name());
                    request.requestMatchers(HttpMethod.GET,VACANCY + CITY).hasAnyRole(ADMIN.name(),USER.name());
                    request.requestMatchers(HttpMethod.GET,VACANCY + POSITION).hasAnyRole(ADMIN.name(),USER.name());
                    request.requestMatchers(HttpMethod.GET,VACANCY + SALARY).hasAnyRole(ADMIN.name(),USER.name());
                    request.requestMatchers(HttpMethod.GET,VACANCY + COMPANY_NAME).hasAnyRole(ADMIN.name(),USER.name());

                    request.anyRequest().denyAll();
                })
                .exceptionHandling(handling-> handling
                        .authenticationEntryPoint(commonConfig.authenticationEntryPoint())
                        .accessDeniedHandler(commonConfig.accessDeniedHandler()))
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
