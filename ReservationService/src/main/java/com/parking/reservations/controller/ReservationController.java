package com.parking.reservations.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/reservations")
@RestController
public class ReservationController {

    /*
     * http://localhost:9004/reservations/allreservations
     */
    @GetMapping(path = "/allreservations")
    public String getAllReservations() {
        System.out.println("Hello from Reservation Controller!");
        // _ReservationService.getAllReservations();
        return "Hello from Reservation Controller!";
    }

    /*
     * http://localhost:9004/reservations/reservation/{reservationId}
     */
    @GetMapping(path = "/reservation/{reservationId}")
    public String getReservationById(@PathVariable("reservationId") String reservationId) {
        System.out.println("Fetching details for Reservation ID = " + reservationId);
        // _ReservationService.getReservationById(reservationId);
        return "Here are the details for Reservation ID = " + reservationId;
    }

    /*
     * http://localhost:9004/reservations/reservation?reservationId=R101&status=confirmed
     */
    @GetMapping(path = "/reservation")
    public String getReservationByRequest(@RequestParam("reservationId") String reservationId,
                                          @RequestParam("status") String status) {
        System.out.println("Fetching Reservation with ID = " + reservationId + " and status = " + status);
        // _ReservationService.getReservationByRequest(reservationId, status);
        return "Reservation info: ID = " + reservationId + ", Status = " + status;
    }
}
