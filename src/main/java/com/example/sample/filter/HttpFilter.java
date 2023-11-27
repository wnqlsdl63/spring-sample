package com.example.sample.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        log.info("Request [{}] {}", httpRequest.getMethod(), httpRequest.getRequestURL().toString());

        chain.doFilter(request, response);

        log.info("Response {} [{}] {}", httpResponse.getStatus(), httpRequest.getMethod(), httpRequest.getRequestURL().toString());
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
