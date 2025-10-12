package com.parking.users.irepository;

import com.parking.users.model.User;
import java.util.List;

public interface IUserRepository {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User getUserByVehicleNumber(String vehicleNumber);
    void addUser(User user);
    List<User> findBySlotId(Long slotId);
}
