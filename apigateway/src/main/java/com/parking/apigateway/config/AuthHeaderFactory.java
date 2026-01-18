package com.parking.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AuthHeaderFactory is responsible for creating Basic Auth headers
 * for inter-service communication between Parking microservices.
 */
@Component
public class AuthHeaderFactory {

    @Value("${parkingslotservice.auth.username}")
    String parkingSlotUsername;

    @Value("${parkingslotservice.auth.password}")
    String parkingSlotPassword;

    @Value("${paymentservice.auth.username}")
     String paymentUsername;

    @Value("${paymentservice.auth.password}")
     String paymentPassword;

    @Value("${reservationservice.auth.username}")
    String reservationUsername;

    @Value("${reservationservice.auth.password}")
    String reservationPassword;

    @Value("${userservice.auth.username}")
     String userUsername;

    @Value("${userservice.auth.password}")
    String userPassword;
  
    @Value("${apigateway.shared.secret}")
    String _SharedSecret;
    
    public String buildAuthHeader(String serviceName) {
        String username = "";
        String password = "";

        // Use .equals() for string comparison (not ==)
        if ("parkingslotservice".equalsIgnoreCase(serviceName)) {
            username = parkingSlotUsername;
            password = parkingSlotPassword;
        } else if ("paymentservice".equalsIgnoreCase(serviceName)) {
            username = paymentUsername;
            password = paymentPassword;
        } else if ("reservationservice".equalsIgnoreCase(serviceName)) {
            username = reservationUsername;
            password = reservationPassword;
        }
        else if ("userservice".equalsIgnoreCase(serviceName)) {
            username = userUsername;
            password = userPassword;
        }
        
        else {
            throw new IllegalArgumentException("Unknown service name: " + serviceName);
        }

        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder()
                .encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
    }
     String getSharedSecret()
    {
        return _SharedSecret;
    }
}
