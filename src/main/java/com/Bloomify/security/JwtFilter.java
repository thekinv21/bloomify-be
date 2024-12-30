package com.Bloomify.security;


import com.Bloomify.service.JwtDecoderService;
import com.Bloomify.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    private final JwtDecoderService jwtDecoderService;
    private final UserDetailsSecurityService userDetailsService;
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = header.substring(7);

        try {

            String username = tokenService.validateTokenAndReturnUsername(jwt);
            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

            CustomUsernamePasswordAuthenticationToken authToken =
                    new CustomUsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities(), jwt);
            SecurityContextHolder.getContext().setAuthentication(authToken);
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        } catch (Exception e) {
            sendError(response, e, request);
            return;
        }

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
