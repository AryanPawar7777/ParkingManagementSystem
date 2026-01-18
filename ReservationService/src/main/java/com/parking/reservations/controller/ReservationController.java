package com.parking.reservations.controller;

import com.parking.reservations.model.Reservation;
import com.parking.reservations.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.parking.reservations.dto.PaginatedResponse;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /*
     * ✅ GET ALL RESERVATIONS
     * Example: GET http://localhost:9004/reservations/allreservations
     */
    @GetMapping("/allreservations")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    /*
     * ✅ GET RESERVATION BY ID
     * Example: GET http://localhost:9004/reservations/reservation/1
     */
    @GetMapping("/reservation/{reservationId}")
    public Reservation getReservationById(@PathVariable("reservationId") Long reservationId) {
        return reservationService.getReservationById(reservationId);
    }

    /*
     * ✅ ADD NEW RESERVATION
     * Example: POST http://localhost:9004/reservations/add
     * Body (JSON):
     * {
     *   "userId": 1,
     *   "slotId": 5,
     *   "startTime": "2025-11-06T08:00:00",
     *   "endTime": "2025-11-06T10:00:00",
     *   "vehicleNumber": "MH12AB1234",
     *   "status": "ACTIVE",
     *   "totalAmount": 100.00
     * }
     */
    @PostMapping("/add")
    public String addReservation(@RequestBody Reservation reservation) {
        int rows = reservationService.addReservation(reservation);
        return rows > 0 ? "Reservation added successfully!" : "Failed to add reservation!";
    }

    /*
     * ✅ DELETE RESERVATION
     * Example: DELETE http://localhost:9004/reservations/delete/1
     */

       @GetMapping("/user/{userId}")
    public List<Reservation> getReservationsByUserId(@PathVariable Long userId) {
        return reservationService.getReservationsByUserId(userId);
    }
    
    @DeleteMapping("/delete/{reservationId}")
    public String deleteReservation(@PathVariable("reservationId") Long reservationId) {
        int rows = reservationService.deleteReservation(reservationId);
        return rows > 0 ? "Reservation deleted successfully!" : "Reservation not found!";
    }

    @GetMapping("/nextpage")
public ResponseEntity<PaginatedResponse<Reservation>> getPaginatedReservations(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(required = false) Integer size
) {
    PaginatedResponse<Reservation> response = reservationService.getPaginatedReservations(page, size);
    return ResponseEntity.ok(response);
}
}
