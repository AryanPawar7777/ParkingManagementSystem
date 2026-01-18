package com.parking.reservations.service;

import com.parking.reservations.dto.PaginatedResponse;
import org.springframework.beans.factory.annotation.Value;

import com.parking.reservations.model.Reservation;
import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsByUserId(Long userId);
    Reservation getReservationById(Long reservationId);
    int addReservation(Reservation reservation);
    int deleteReservation(Long reservationId);


     PaginatedResponse<Reservation> getPaginatedReservations(int page, Integer size);
}
    

