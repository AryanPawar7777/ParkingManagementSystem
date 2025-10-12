package com.parking.payments.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PaymentDto {

    private Long paymentId;
    private Long reservationId;
    private Long userId;
    private BigDecimal amount;
    private String status;      // e.g., PAID, PENDING, FAILED
    private String method;      // e.g., UPI, CARD, CASH, etc.
    private Timestamp timestamp;

    // No-arg constructor
    public PaymentDto() {
    }

    // All-arg constructor
    public PaymentDto(Long paymentId, Long reservationId, BigDecimal amount, String status, String method, Timestamp timestamp) {
        this.paymentId = paymentId;
        this.reservationId = reservationId;
         this.userId = userId;
        this.amount = amount;
        this.status = status;
        this.method = method;
        this.timestamp = timestamp;
    }

    // Getters and Setters

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

     public Long getUserId() { 
        return userId; 
    }
    public void setUserId(Long userId) {
         this.userId = userId; 
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
