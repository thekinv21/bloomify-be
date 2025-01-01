package com.Bloomify.security;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor

public class Security {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods(CorsConfiguration.ALL)
                        .allowedHeaders(CorsConfiguration.ALL)
                        .allowedOriginPatterns(CorsConfiguration.ALL);
            }
        };
    }


    // IGNORING PUBLIC REQUESTS ENDPOINTS

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->
                web.ignoring()
                        .requestMatchers(
                                "/api/auth/login/**",
                                "/api/auth/register/**",
                                "/swagger-resources/**",
                                "/swagger-ui.html/**",
                                "/swagger-resources/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        );
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                // DISABLE HEADERS
                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                // DISABLE CSRF
                .csrf(AbstractHttpConfigurer::disable)

                // DISABLE CORS
                .cors(Customizer.withDefaults())

                // FILTER HTTP REQUEST
                .exceptionHandling(handlingConfigurer -> {
                    handlingConfigurer.accessDeniedHandler(jwtAccessDeniedHandler);
                    handlingConfigurer.authenticationEntryPoint(jwtAuthenticationEntryPoint);
                })

                // DISABLE FORM LOGIN
                .formLogin(AbstractHttpConfigurer::disable)

                // FILTER REQUEST PUBLİC OR PRIVATE
                .authorizeHttpRequests(request -> {
                    request.anyRequest().authenticated();
                })

                // CONFIGURER HTTP BASICS
                .httpBasic(Customizer.withDefaults())

                // ADD JWT FILTER
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }



}
