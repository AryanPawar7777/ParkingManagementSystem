package com.parking.reservations.repository;

import com.parking.reservations.model.Reservation;
import java.util.List;

public interface IReservationRepository {
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long reservationId);
    int addReservation(Reservation reservation);
    int deleteReservation(Long reservationId);
    List<Reservation> getReservationsByUserId(Long userId);

    List<Reservation> getReservationsByPage(int offset, int limit);
int getTotalReservationsCount();
}
