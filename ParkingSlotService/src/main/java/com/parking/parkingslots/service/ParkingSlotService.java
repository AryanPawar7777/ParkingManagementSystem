package com.parking.parkingslots.service;

import com.parking.parkingslots.dto.ParkingSlotDto;
import com.parking.parkingslots.dto.PaymentDto;
import com.parking.parkingslots.dto.UserDto;
import com.parking.parkingslots.dto.ReservationDto;
import com.parking.parkingslots.dto.PaginatedResponse;
import com.parking.parkingslots.irepository.IParkingSlotRepository;
import com.parking.parkingslots.model.ParkingSlot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private final String RESERVATION_SERVICE_URL = "http://localhost:9004/reservations";

    @Autowired
    public ParkingSlotService(IParkingSlotRepository parkingSlotRepository,
                              ModelMapper modelMapper,
                              RestTemplate restTemplate) {
        this.parkingSlotRepository = parkingSlotRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
    }

    // ====================== BASIC CRUD ===============================

    public List<ParkingSlotDto> getAllParkingSlots() {
        List<ParkingSlot> slots = parkingSlotRepository.getAllParkingSlots();
        List<ParkingSlotDto> dtos = new ArrayList<>();

        for (ParkingSlot slot : slots) {
            dtos.add(modelMapper.map(slot, ParkingSlotDto.class));
        }
        return dtos;
    }

    public ParkingSlotDto getSlotById(Long slotId) {
        ParkingSlot slot = parkingSlotRepository.getParkingSlotById(slotId);
        return (slot == null) ? null : modelMapper.map(slot, ParkingSlotDto.class);
    }

    public boolean checkAvailability(String slotNumber) {
        return parkingSlotRepository.isSlotAvailable(slotNumber);
    }

    public void addSlot(ParkingSlotDto slotDto) {
        parkingSlotRepository.addParkingSlot(modelMapper.map(slotDto, ParkingSlot.class));
    }

    public void updateAvailability(Long slotId, boolean isAvailable) {
        parkingSlotRepository.updateSlotAvailability(slotId, isAvailable);
    }

    public void deleteSlot(Long slotId) {
        parkingSlotRepository.deleteParkingSlot(slotId);
        System.out.println("Deleted slot with ID: " + slotId);
    }

    // ====================== USER SERVICE CALL ===============================

   public List<UserDto> getUsersBySlotId(Long slotId) {
    try {
        String url = USER_SERVICE_URL + "/slot/" + slotId;

        // Add required header
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-GATEWAY-SECRET", "parkingsecret@123");

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<List<UserDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<UserDto>>() {}
        );

        return response.getBody() != null ? response.getBody() : Collections.emptyList();

    } catch (Exception e) {
        System.out.println("Error fetching users: " + e.getMessage());
        return Collections.emptyList();
    }
}

    // ====================== PAYMENT SERVICE CALL (Correct Logic) ===============================

    public List<PaymentDto> getPaymentsByUserId(Long userId) {
        try {
            // 1️⃣ Fetch reservations of the user
            String url1 = RESERVATION_SERVICE_URL + "/user/" + userId;

            ResponseEntity<List<ReservationDto>> reservationResponse =
                    restTemplate.exchange(
                            url1,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<ReservationDto>>() {}
                    );

            List<ReservationDto> reservations = reservationResponse.getBody();

            if (reservations == null || reservations.isEmpty()) {
                return Collections.emptyList();
            }

            List<PaymentDto> allPayments = new ArrayList<>();

            // 2️⃣ Fetch payments for each reservation
            for (ReservationDto reservation : reservations) {
                Long reservationId = reservation.getReservationId();

                String url2 = PAYMENT_SERVICE_URL + "/reservation/" + reservationId;

                ResponseEntity<List<PaymentDto>> paymentResponse =
                        restTemplate.exchange(
                                url2,
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<List<PaymentDto>>() {}
                        );

                if (paymentResponse.getBody() != null) {
                    allPayments.addAll(paymentResponse.getBody());
                }
            }

            return allPayments;

        } catch (Exception e) {
            System.out.println("Error fetching payments: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // ====================== PAGINATION ===============================

    @Value("${com.parking.parkingslots.pagination.size:10}")
    private int defaultPageSize;

    public PaginatedResponse<ParkingSlotDto> getPaginatedSlots(int page, Integer size) {

        int pageSize = (size != null && size > 0) ? size : defaultPageSize;
        int offset = page * pageSize;

        int totalElements = parkingSlotRepository.getTotalParkingSlotsCount();
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);

        List<ParkingSlot> slots = parkingSlotRepository.getParkingSlotsByPage(offset, pageSize);

        List<ParkingSlotDto> dtos = new ArrayList<>();
        for (ParkingSlot slot : slots) {
            dtos.add(modelMapper.map(slot, ParkingSlotDto.class));
        }

        return new PaginatedResponse<>(page, pageSize, totalPages, totalElements, dtos);
    }
}
