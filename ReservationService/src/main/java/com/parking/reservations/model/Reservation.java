package com.parking.reservations.model;

import java.sql.Timestamp;

public class Reservation {
    private Long reservationId;
    private Long userId;
    private Long slotId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String vehicleNumber;
    private String status; // ACTIVE, COMPLETED, CANCELLED
    private Double totalAmount;


       public Reservation() {}

    public Reservation(Long reservationId, Long userId, Long slotId,
                       Timestamp startTime, Timestamp endTime,
                       String vehicleNumber, String status, Double totalAmount) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.slotId = slotId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.vehicleNumber = vehicleNumber;
        this.status = status;
        this.totalAmount = totalAmount;
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

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }


}
