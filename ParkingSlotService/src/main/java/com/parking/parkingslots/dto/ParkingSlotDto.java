package com.parking.parkingslots.dto;

public class ParkingSlotDto {
    
    private Long slotId;
    private String slotNumber;
    private boolean isAvailable;
    private String vehicleType; // e.g., Car, Bike, EV, etc.


    public ParkingSlotDto() {}

    public ParkingSlotDto(Long slotId, String slotNumber, boolean isAvailable, String vehicleType) {
        this.slotId = slotId;
        this.slotNumber = slotNumber;
        this.isAvailable = isAvailable;
        this.vehicleType = vehicleType;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
