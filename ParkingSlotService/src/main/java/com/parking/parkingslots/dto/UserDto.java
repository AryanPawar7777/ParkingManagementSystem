package com.parking.parkingslots.dto;

public class UserDto {

    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String vehicleNumber;

    public UserDto() {}

    public UserDto(Long userId, String name, String email, String phone, String vehicleNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.vehicleNumber = vehicleNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
