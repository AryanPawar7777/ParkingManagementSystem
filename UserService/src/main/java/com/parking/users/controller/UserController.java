package com.parking.users.controller;

import com.parking.users.service.UserService; // Import your service class
import com.parking.users.dto.UserDto;          // Import your DTO class
import java.util.List; 

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/vehicle")
    public UserDto getUserByVehicleNumber(@RequestParam String vehicleNumber) {
        return userService.getUserByVehicleNumber(vehicleNumber);
    }

    // ✅ NEW ENDPOINT: get users by slot ID
    @GetMapping("/slot/{slotId}")
    public List<UserDto> getUsersBySlotId(@PathVariable Long slotId) {
        return userService.getUsersBySlotId(slotId);
    }

    @PostMapping("/add")
    public UserDto addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return userDto;
    }
}
