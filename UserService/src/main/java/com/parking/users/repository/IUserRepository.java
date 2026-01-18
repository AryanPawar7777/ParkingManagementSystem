package com.parking.users.repository;

import com.parking.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {

   User findByVehicleNumber(String vehicleNumber);

    List<User> findBySlotId(Long slotId);
}
