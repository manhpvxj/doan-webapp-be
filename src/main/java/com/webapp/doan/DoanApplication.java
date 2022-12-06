package com.webapp.doan;

import com.webapp.doan.middleware.AuthMiddleware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class DoanApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoanApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        registrationBean.setFilter(new CorsFilter(source));
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }


    @Bean
    public FilterRegistrationBean<AuthMiddleware> filterRegistrationBean() {
        FilterRegistrationBean<AuthMiddleware> registrationBean = new FilterRegistrationBean<>();
        AuthMiddleware authMiddleware = new AuthMiddleware();
        registrationBean.setFilter(authMiddleware);
        registrationBean.addUrlPatterns("/api/employees/*");
        registrationBean.addUrlPatterns("/api/suppliers/*");
        registrationBean.addUrlPatterns("/api/invoices/*");
        registrationBean.addUrlPatterns("/api/products/*");
        registrationBean.addUrlPatterns("/api/users/*");
        return registrationBean;
    }
}
