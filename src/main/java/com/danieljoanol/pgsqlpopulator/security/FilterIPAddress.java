package com.danieljoanol.pgsqlpopulator.security;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FilterIPAddress implements Filter {
    
    private List<String> allowedIpRange = List.of("127.0.0.1");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String clientIpAddress = request.getRemoteAddr();

        log.info("New http request from IP: " + clientIpAddress);
        
        Boolean matched = allowedIpRange.contains(clientIpAddress);

        if (matched) {
            chain.doFilter(request, response);
            log.info("IP " + clientIpAddress + " allowed");
        
        } else {
            httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            log.info("IP " + clientIpAddress + " not allowed");
            return;
        }
        
    }
    
}
