package com.parking.parkingslots.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO representing payment details for a reservation.
 */
public class PaymentDto {

    private Long paymentId;
    private Long reservationId;
    private BigDecimal amount;
    private String status;   // e.g., PAID, PENDING, FAILED
    private String method;   // e.g., UPI, CARD, CASH
    private LocalDateTime timestamp;

    public PaymentDto() {
        // default constructor
    }

    public PaymentDto(Long paymentId, Long reservationId, BigDecimal amount, 
                      String status, String method, LocalDateTime timestamp) {
        this.paymentId = paymentId;
        this.reservationId = reservationId;
        this.amount = amount;
        this.status = status;
        this.method = method;
        this.timestamp = timestamp;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
