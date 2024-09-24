package com.demo.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        CachedBodyHttpServletRequest cachedRequest = new CachedBodyHttpServletRequest(httpRequest);
        CachedBodyHttpServletResponse cachedResponse = new CachedBodyHttpServletResponse(httpResponse);

        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("Incoming request: method={}, uri={}", httpRequest.getMethod(), httpRequest.getRequestURI());
        String requestBody = new String(cachedRequest.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        logger.info("Request body = {}", requestBody);
        
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
//        chain.doFilter(request, response);
        chain.doFilter(cachedRequest, cachedResponse);
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("Outgoing response: status={}", httpResponse.getStatus());

        String responseBody = new String(cachedResponse.getCachedBody(), StandardCharsets.UTF_8);
        logger.info("Response body = {}", responseBody);
        
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
        logger.info("==================+++++++++++++++++++================================");
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}

