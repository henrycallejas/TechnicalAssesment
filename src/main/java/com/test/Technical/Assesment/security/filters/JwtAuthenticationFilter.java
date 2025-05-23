package com.test.Technical.Assesment.security.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.Technical.Assesment.enums.ResponseMessage;
import com.test.Technical.Assesment.model.Client;
import com.test.Technical.Assesment.security.exception.InvalidLoginRequestException;
import com.test.Technical.Assesment.security.jwt.JWTUtils;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private JWTUtils jwtUtils;
    public JwtAuthenticationFilter(JWTUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        Client client = new Client();
        String username = "";
        String password = "";

        try {
            client = new ObjectMapper().readValue(request.getInputStream(), Client.class);
            username = client.getUsername();
            password = client.getPassword();
        }catch (IOException e) {
            throw new InvalidLoginRequestException("Error parsing login request body", e);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            (username), password);
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String token = jwtUtils.generateAccessToken(user.getUsername());
        response.addHeader("Authorization", token);
        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("message", ResponseMessage.AUTORIZED.getMessage());
        httpResponse.put("Username", user.getUsername());
        response.getWriter().write(
            new ObjectMapper().writeValueAsString(httpResponse)
        );
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
    
}
