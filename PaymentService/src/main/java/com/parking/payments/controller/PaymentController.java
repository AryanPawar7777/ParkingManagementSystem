package com.parking.payments.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;
import com.parking.payments.dto.PaymentDto;
import com.parking.payments.service.PaymentService;
import com.parking.payments.dto.PaginatedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/all")
    public List<PaymentDto> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{paymentId}")
    public PaymentDto getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @GetMapping("/byReservation/{reservationId}")
    public List<PaymentDto> getPaymentsByReservation(@PathVariable Long reservationId) {
        return paymentService.getPaymentsByReservationId(reservationId);
    }


    @GetMapping("/user/{userId}")
    public List<PaymentDto> getPaymentsByUser(@PathVariable Long userId) {
        return paymentService.getPaymentsByUserId(userId);
    }

    @PostMapping("/make")
    public String makePayment(@RequestBody PaymentDto dto) {
        paymentService.makePayment(dto);
        return "Payment added successfully.";
    }
    @GetMapping("/nextpage")
public ResponseEntity<PaginatedResponse<PaymentDto>> getPaginatedPayments(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(required = false) Integer size
) {
    PaginatedResponse<PaymentDto> response = paymentService.getPaginatedPayments(page, size);
    return ResponseEntity.ok(response);
}

}
