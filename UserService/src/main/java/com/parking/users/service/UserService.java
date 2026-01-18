package com.parking.users.service;
import com.parking.users.dto.PaginatedResponse;
import com.parking.users.dto.UserDto;
import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();
    UserDto getUserById(Long userId);
    UserDto getUserByVehicleNumber(String vehicleNumber);
    void addUser(UserDto userDto);
    List<UserDto> getUsersBySlotId(Long slotId);

    PaginatedResponse<UserDto> getPaginatedUsers(int page, Integer size);
}

