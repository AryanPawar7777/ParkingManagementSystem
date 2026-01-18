package com.parking.reservations.service;

import com.parking.reservations.model.Reservation;
import com.parking.reservations.repository.IReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.parking.reservations.dto.PaginatedResponse; 


@Service
public class ReservationServiceImpl implements ReservationService {

    // FIX 3: Define the missing constant variable
    private static final int defaultPageSize = 10; 

    private final IReservationRepository repository;

    @Autowired
    public ReservationServiceImpl(IReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return repository.getAllReservations();
    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        return repository.getReservationById(reservationId);
    }

    @Override
    public int addReservation(Reservation reservation) {
        return repository.addReservation(reservation);
    }

    @Override
    public int deleteReservation(Long reservationId) {
        return repository.deleteReservation(reservationId);
    }

       @Override
    public List<Reservation> getReservationsByUserId(Long userId) {
        return repository.getReservationsByUserId(userId);
    }

    @Override
    public PaginatedResponse<Reservation> getPaginatedReservations(int page, Integer size) {
        int pageSize = (size != null && size > 0) ? size : defaultPageSize;
        int offset = page * pageSize;

        // FIX 4: Use the correctly injected variable 'repository', not 'reservationRepository'
        int totalElements = repository.getTotalReservationsCount(); 
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);

        // FIX 4: Use the correctly injected variable 'repository', not 'reservationRepository'
        List<Reservation> reservations = repository.getReservationsByPage(offset, pageSize);

        return new PaginatedResponse<>(page, pageSize, totalPages, totalElements, reservations);
    }
}