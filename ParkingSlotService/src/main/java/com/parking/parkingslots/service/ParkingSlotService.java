package com.parking.parkingslots.service;

import com.parking.parkingslots.dto.ParkingSlotDto;
import com.parking.parkingslots.dto.PaymentDto;
import com.parking.parkingslots.dto.UserDto;
import com.parking.parkingslots.irepository.IParkingSlotRepository;
import com.parking.parkingslots.model.ParkingSlot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class ParkingSlotService {

    private final IParkingSlotRepository parkingSlotRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    private final String USER_SERVICE_URL = "http://localhost:9005/users";
    private final String PAYMENT_SERVICE_URL = "http://localhost:9003/payments";

    @Autowired
    public ParkingSlotService(IParkingSlotRepository parkingSlotRepository,
                              ModelMapper modelMapper,
                              RestTemplate restTemplate) {
        this.parkingSlotRepository = parkingSlotRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
    }

    // Fetch all parking slots
    public List<ParkingSlotDto> getAllParkingSlots() {
        List<ParkingSlot> slots = parkingSlotRepository.getAllParkingSlots();
        List<ParkingSlotDto> slotDtos = new ArrayList<>();
        for (ParkingSlot slot : slots) {
            slotDtos.add(modelMapper.map(slot, ParkingSlotDto.class));
        }
        return slotDtos;
    }

    // Fetch a parking slot by ID
    public ParkingSlotDto getSlotById(Long slotId) {
        ParkingSlot slot = parkingSlotRepository.getParkingSlotById(slotId);
        if (slot == null) return null;
        return modelMapper.map(slot, ParkingSlotDto.class);
    }

    // Check slot availability
    public boolean checkAvailability(String slotNumber) {
        return parkingSlotRepository.isSlotAvailable(slotNumber);
    }

    // Add a new parking slot
    public void addSlot(ParkingSlotDto slotDto) {
        ParkingSlot slot = modelMapper.map(slotDto, ParkingSlot.class);
        parkingSlotRepository.addParkingSlot(slot);
    }

    // Update slot availability
    public void updateAvailability(Long slotId, boolean isAvailable) {
        parkingSlotRepository.updateSlotAvailability(slotId, isAvailable);
    }

    // =================== REST METHODS ===================

    public List<UserDto> getUsersBySlotId(Long slotId) {
        try {
            ResponseEntity<List<UserDto>> response = restTemplate.exchange(
                    USER_SERVICE_URL + "/slot/{slotId}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<UserDto>>() {},
                    slotId
            );
            return response.getBody();
        } catch (Exception e) {
            System.out.println("Error fetching users: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<PaymentDto> getPaymentsByUserId(Long userId) {
        try {
            ResponseEntity<List<PaymentDto>> response = restTemplate.exchange(
                    PAYMENT_SERVICE_URL + "/user/{userId}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<PaymentDto>>() {},
                    userId
            );
            return response.getBody();
        } catch (Exception e) {
            System.out.println("Error fetching payments: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
