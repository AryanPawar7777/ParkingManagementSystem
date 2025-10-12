package com.parking.users.service;

import com.parking.users.dto.UserDto;
import com.parking.users.irepository.IUserRepository;
import com.parking.users.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(IUserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    // Get all users
    public List<UserDto> getAllUsers() {
        return userRepository.getAllUsers()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    // Get user by ID
    public UserDto getUserById(Long userId) {
        User user = userRepository.getUserById(userId);
        return user != null ? modelMapper.map(user, UserDto.class) : null;
    }

    // Get user by vehicle number
    public UserDto getUserByVehicleNumber(String vehicleNumber) {
        User user = userRepository.getUserByVehicleNumber(vehicleNumber);
        return user != null ? modelMapper.map(user, UserDto.class) : null;
    }

    // Add a new user
    public void addUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userRepository.addUser(user);
    }

    // ✅ Get all users assigned to a parking slot
    public List<UserDto> getUsersBySlotId(Long slotId) {
        List<User> users = userRepository.findBySlotId(slotId);
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
