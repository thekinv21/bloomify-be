package com.Bloomify.security;


import com.Bloomify.service.TokenService;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;


@Component
@RequiredArgsConstructor

public class JwtFilter extends OncePerRequestFilter {


    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        // ACCESS TOKEN VARIABLE
        final String jwtToken;

        // GET HEADER FROM REQUEST
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // CHECK HEADER IS NULL OR NOT
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // EXTRACT TOKEN FROM HEADER
        jwtToken = authHeader.substring(7);

        // DECODE JWT and GET user details with username
        DecodedJWT decodedJWT;
        String username;

        try {
            decodedJWT = tokenService.verifyAccessToken(jwtToken);
            username = decodedJWT.getSubject();
        } catch (Exception e) {
            sendError(response, new Exception("Access Token is Not Valid!"),request);
            return;
        }

        if (username == null) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);


        // GENERATE NEW AUTH TOKEN
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        // SET CREATED TOKEN TO SECURITY CONTEXT
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // SET DETAİLS
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // FILTER AGAIN
        filterChain.doFilter(request, response);
    }


    // SEND ERROR FUNCTION

    private void sendError(
            HttpServletResponse response,
            Exception exception,
            HttpServletRequest request)
            throws IOException {
        // SET ERROR STATUS
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // SET ERROR CONTENT TYPE
        response.setContentType("application/json");

        // GET RESPONSE WRITER
        PrintWriter out = response.getWriter();

        // CREATE NEW OBJECT MAPPER FOR RESPONSE
        ObjectMapper mapper = new ObjectMapper();

        // RETURN NEW CODE AND ERROR MESSAGE IF ERRORS
        out.println(mapper.writeValueAsString(Map.of(
                "message", exception.getMessage(),
                "method", request.getMethod(),
                "status", response.getStatus(),
                "timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))));
        out.flush();
    }
}
