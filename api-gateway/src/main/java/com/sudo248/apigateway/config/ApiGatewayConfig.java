package com.sudo248.apigateway.config;

import com.sudo248.apigateway.filter.ApiFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ApiGatewayConfig {
    private final ApiFilter filter;

    public ApiGatewayConfig(@Lazy ApiFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("AUTH-SERVICE", r -> r.path("/api/v1/auth/**").filters(f -> f.filter(filter)).uri("lb://AUTH-SERVICE"))
                .build();
    }
}
