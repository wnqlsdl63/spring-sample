package com.example.sample.config;

import com.example.sample.filter.HttpFilter;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public Filter httpFilter() {
        return new HttpFilter();
    }
}
