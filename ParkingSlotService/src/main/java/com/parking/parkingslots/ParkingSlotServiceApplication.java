package com.parking.parkingslots; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@ComponentScan(basePackages = "com.parking.parkingslots")
@EnableDiscoveryClient
public class ParkingSlotServiceApplication {
public static void main(String[] args) {
    SpringApplication.run(ParkingSlotServiceApplication.class, args);
}
}
