package com.parking.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("parkingslotservice", r -> r.path("/slots/**")
                .uri("lb://parkingslotservice"))
            .route("paymentservice", r -> r.path("/payments/**")
                .uri("lb://paymentservice"))
            .route("reservationservice", r -> r.path("/reservations/**")
                .uri("lb://reservationservice"))
            .route("userservice", r -> r.path("/users/**")
                .uri("lb://userservice"))
            .build();
    }
}
